; scroll up the screen
[org 0x0100]
jmp start

printstr: 
	push bp
	mov bp, sp
	push es
	push ax
	push cx
	push si
	push di
	mov ax, 0xb800
	mov es, ax ; point es to video base
	mov di,[bp+10] ; point di to required location
	mov si, [bp+6] ; point si to string
	mov cx, [bp+4] ; load length of string in cx
	mov ah, [bp+8] ; load attribute in ah
	cld ; auto increment mode
	
	nextchar: 
		lodsb ; load next char in al
		stosw ; print char/attribute pair
		loop nextchar ; repeat for the whole string
		pop di
		pop si
		pop cx
		pop ax
		pop es
		pop bp
		ret 10

; subroutine to scroll up the screen
; take the number of lines to scroll as parameter
scrollup: 
	push bp
	mov bp,sp
	push ax
	push cx
	push si
	push di
	push es
	push ds
	
	mov ax, 0
	mov si, ax ; load source position in si
	push si ; save position for later use
	shl si, 1 ; convert to byte offset
	push si
	mov cx, 2000 ; number of screen locations
	push cx
	mov ax, 0xb800
	mov es, ax ; point es to video base
	push ds
	mov ds, ax ; point ds to video base
	xor di, di ; point di to top left column
	mov bx, temp
	;l2:
	;	mov ax, [es:si]
	;	mov word[bx], ax
	;	add si, 2
	;	loop l2
	;pop cx
	;pop si
	cld ; set auto increment mode
	rep movsw ; scroll up
	mov ax, 0x0720 ; space in normal attribute
	pop cx ; count of positions to clear
	rep stosw ; clear the scrolled space
	
	pop ds
	;mov si, temp
	;mov ax, 0xb800
	;mov es, ax
	;mov di, 2000
	;sub di, 80
	;mov cx, 80
	;rep movsw
	
	pop ds
	pop es
	pop di
	pop si
	pop cx
	pop ax
	pop bp
	ret 2
	
start: 
	call scrollup ; call the scroll up subroutine
	
	mov ax, 0x4c00 ; terminate program
	int 0x21

shift: db 0
len: db 80	
temp: dw 0