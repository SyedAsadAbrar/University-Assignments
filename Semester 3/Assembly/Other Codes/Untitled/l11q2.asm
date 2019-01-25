[org 0x100]
jmp start
string: db 'test file',0
oldisr: dd 0 
value: db 0
data: dw 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,

scrollup:	 push bp
			mov bp,sp
			push ax
			push bx
			push cx
			push si
			push di
			push es
			push ds
			
			push ds
			push ds
			mov cx,80
			mov si,0
			mov di,data
			pop es
			mov ax,0xb800
			mov ds,ax
			cld
			rep movsw
			
			mov ax, 80 ; load chars per row in ax
			mov bx,1
			mul  bx;byte [bp+4] ; calculate source position
			mov si, ax ; load source position in si
			push si ; save position for later use
			shl si, 1 ; convert to byte offset
			mov cx, 2000 ; number of screen locations
			sub cx, ax ; count of words to move
			mov ax, 0xb800
			mov es, ax ; point es to video base
			mov ds, ax ; point ds to video base
			xor di, di ; point di to top left column
			cld ; set auto increment mode
			rep movsw ; scroll up
			mov ax, 0x0720 ; space in normal attribute
			pop cx ; count of positions to clear
			rep stosw ; clear the scrolled space
			
			
			pop ds
			mov cx,80
			mov si,data
			mov di,3840
			mov ax,0xb800
			mov es,ax
			cld
			rep movsw		
			
			
			
			pop ds
			pop es
			pop di
			pop si
			pop cx
			pop bx
			pop ax
			pop bp
			ret 2

scrolldown: push bp
			mov bp,sp
			push ax
			push bx
			push cx
			push si
			push di
			push es
			push ds
				
			mov dx,ds
			
			mov ax,0xb800
			
			
			mov cx,80
			mov ds,ax
			mov es,dx
			mov di,data
			mov si,3840
			cld
			rep movsw
			
			
			mov cx,1920
			mov es,ax
			mov ds,ax
			mov si,3838
			mov di,3998
			std
			rep movsw
			
				
			mov cx,80
			mov ds,dx
			mov es,ax
			mov si,data
			mov di,0
			cld 
			rep movsw
			
			pop ds
			pop es
			pop di
			pop si
			pop cx
			pop bx
			pop ax
			pop bp
			ret 2			

kbisr: 	push ax
		push bx
		push cx
		push dx
		
		push es		

		in al, 0x60 ; read a char from keyboard port
		
		cmp al, 0x2a ; is the key left shift
		jne cond1
		;mov byte[value],0x2a
		
		mov bx,0
		push bx
		call scrollup

cond1:		
		cmp al,0x36
		jne nomatch
		;mov byte[value],0x36
		
		mov bx,1
		push bx
		call scrolldown
		
		

nomatch: 
		pop es
		pop dx
		pop cx
		pop bx
		pop ax
		
		jmp far [cs:oldisr] ; call the original ISR

		
start:  
		xor ax, ax
		mov es, ax ; point es to IVT base
		
		mov ax, [es:9*4]
		mov [oldisr], ax ; save offset of old routine
		mov ax, [es:9*4+2]
		mov [oldisr+2], ax ; save segment of old routine
		
		cli ; disable interrupts
		mov word [es:9*4], kbisr ; store offset at n*4
		mov [es:9*4+2], cs ; store segment at n*4+2
		sti ; enable interrupts

l1:		 mov ah, 0 ; service 0 – get keystroke
		
		int 0x16 ; call BIOS keyboard service
		cmp al, 27 ; is the Esc key pressed
		
		jne l1 ; if no, check for next key
		
		
		mov ax, 0x4c00 ; terminate program
		int 0x21


mov ax,0x4c
int 0x21