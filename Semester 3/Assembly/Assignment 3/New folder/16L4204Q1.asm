[org 0x100]
jmp start
string1: db 'Why SO Serious ? ',0
string2: db ' ',0
xpos: dw  20
ypos: dw  20

clrscr:
	push ax
	push cx
	push es
	push di
	
	mov ax,0xb800
	mov es,ax
	mov di,0
	mov cx,2000
	mov ax,0x0720
	
	cld
	rep stosw

	
	
	pop di
	pop es
	pop cx
	pop ax
	
	ret
	
strlen:
	push bp
	mov bp,sp
	
	push es
	push di
	push cx
	push ax
	
	push ds
	pop es
	
	mov di,[bp+4]
	mov cx,0xffff
	mov al,0
	
	repne scasb
	
	mov ax,0xffff
	sub ax,cx
	dec ax
	
	mov [bp+6],ax
	
	pop ax
	pop cx
	pop di
	pop es
	
	pop bp
	
	ret 2

getPosition:
	push bp
	mov bp,sp
	
	push ax

	mov al, 80 					; load al with columns per row
	mul byte [bp + 4] 			; multiply with y position
	add ax,  [bp + 6] 			; add x position
	shl ax, 1 					; turn into byte offset
	
	mov [bp+8],ax
	
	pop ax
	pop bp
	
	ret 4
printstr:
	push bp
	mov bp, sp
	push es
	push ax
	push cx
	push si
	push di
	
	mov ax,[bp+4]
	push 0
	push ax
	call strlen
	pop ax
	
	add ax,0
	
	jz exit 					; no printing if string is empty
	mov cx, ax					 ; load string length in cx
	
	
	mov ax, 0xb800
	mov es, ax 		; point es to video base
	
	
	push 0
	mov ax,[bp+10]
	push ax
	mov ax,[bp+8]
	push ax
	call getPosition
	pop di					; point di to required location
	
	mov si, [bp+4] 				; point si to string
	mov ah, [bp+6] 				; load attribute in ah
	cld 						; auto increment mode

nextchar:
 
	lodsb 						; load next char in al
	stosw 						; print char/attribute pair
	loop nextchar 				; repeat for the whole string

exit: 
	pop di
	pop si
	pop cx
	pop ax
	pop es
	pop bp
	ret 8
	

shift:
	push bp
	mov bp,sp
	
	push ax
	push bx
	push cx
	push dx
	push si
	push di
	push es
	
	mov ax,0xb800
	mov es,ax
	
	mov bx,[bp+4]
	mov di,[bp+6]
	mov cx,[bp+8]
	sub cx,bx
	
	shl bx,1
	mov si,di
	add si,bx
	
	work:
		mov ax,[es:si]
		mov [es:di],ax
		add di,2
		add si,2
		
	loop work
	
	pop es
	
	pop di
	pop si
	
	pop dx
	pop cx
	pop bx
	pop ax
	
	pop bp
	ret 6
	
trimmer:
	push bp
	mov bp,sp
	
	push ax
	push bx
	push cx
	push dx
	push si
	push di
	push es
	
	mov ax,0xb800
	mov es,ax
	
	
	mov cx,[bp + 6]
	
	push 0
	mov ax,[bp+10]
	push ax
	mov ax,[bp+8]
	push ax
	call getPosition
	pop di
	
	
	mov ax,[bp+4]
	push 0
	push ax
	call strlen
	pop dx
	
	mov ah,1
	cld
traverse:
	
	push cx
	push di
	mov cx,dx
	mov si,[bp+4]
	mov bx,0
	check:
		lodsb
		cmp ax,[es:di]
		jne notequal
		add di,2
	loop check
		mov bx,1
	notequal:

	pop di
	pop cx
	
	
	cmp bx,0
	jne shiftneeded
	jmp loopexit
	shiftneeded:
			push cx
			push di
			push dx
			call shift
	
	loopexit 
	
	add di,2
	loop traverse 
	
	
	pop es
	pop di
	pop si
	pop dx
	pop cx
	pop bx
	pop ax
	
	pop bp

	ret 8
	
start:

	call clrscr 	; call the clrscr subroutine
	
	mov ax,[xpos]
	push ax 		; push x position
	mov ax,[ypos]
	push ax 		; push y position
	mov ax, 1 		; blue on black attribute
	push ax 		; push attribute
	mov ax, string1
	push ax 		; push address of message
	call printstr 	; call the printstr subroutine
	
	
	mov ax,string1
	push 0
	push ax
	call strlen
	pop dx
	
	
	mov ax,[xpos]	
	push ax			;push column 
	mov ax,[ypos]
	push ax			;push row
	push dx			;push length of string 1
	mov ax,string2	;push address of string 2
	push ax
	call trimmer
	
	


mov ax,0x4c
int 0x21