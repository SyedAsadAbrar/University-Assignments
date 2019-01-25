[org 0x0100]
jmp start
msg1: db 'Hello World', 0
msg2: db 'Hello World', 0

strlen: 
	push bp
	mov bp,sp
	
	push es
	push cx
	push di
	
	les di, [bp+4] 		; point es:di to string
	mov cx, 0xffff 		; load maximum number in cx
	xor al, al 			; load a zero in al
	repne scasb 		; find zero in the string
	mov ax, 0xffff 		; load maximum number in ax
	sub ax, cx 			; find change in cx
	dec ax 				; exclude null from length
	
	pop di
	pop cx
	pop es
	
	pop bp
	
	ret 4
; subroutine to print a string

;push ds
;push msg1
;push ds
;push msg2
;push length
cmpASCII:
	push bp
	mov bp,sp
	
	push ax
	push bx
	push cx
	push si
	push di
	push es
	push ds
	
	
	lds si, [bp+6] 
	les di, [bp+10]
	
	
	mov ax,0
	mov dx,0
	mov cx,[bp+4]

sumOf1:
	mov dl,[es:di]
	inc di
	add ax,dx
	loop sumOf1
	
	mov cx,[bp+4]
sumOf2:
	mov dl,[ds:si]
	inc si
	add bx,dx
	loop sumOf2
	
	cmp ax,bx
	
	jb retsmaller 
	ja retgreater
	je	retequal
	
retsmaller:
	mov dx,-1
	mov [bp+14],dx
	jmp exit
retgreater:
	mov dx,1
	mov [bp+14],dx
	jmp exit
retequal:
	mov dx,0
	mov [bp+14],dx
	jmp exit
exit:
	pop ds
	pop es
	pop di
	pop si
	pop cx
	pop bx
	pop ax
	
	pop bp
	ret 10
	
strcmp:
	push bp
	mov bp,sp
	push cx
	push bx
	push dx
	push si
	push di
	push es
	push ds
	
	lds si, [bp+4] ; point ds:si to first string
	les di, [bp+8] ; point es:di to second string
	
	push ds ; push segment of first string
	push si ; push offset of first string
	call strlen ; calculate string length
	
	mov cx, ax ; save length in cx
	
	push es ; push segment of second string
	push di ; push offset of second string
	call strlen ; calculate string length
	mov dx,ax
	
	cmp cx, ax ; compare length of both strings
	ja exitsmaller ; return 0 if they are unequal
	jb exitgreater
	
	mov ax, 0 ; store 1 in ax to be returned
	repe cmpsb ; compare both strings
	jcxz exitsimple ; are they successfully compared
	

	
	push 0
	mov bx,[bp+10]
	push bx
	mov bx,[bp+8]
	push bx
	mov bx,[bp+6]
	push bx
	mov bx,[bp+4]
	push bx
	push dx
	call cmpASCII
	pop ax
	
	jmp exitsimple
exitgreater: 
	
	mov ax, 1 ; store 0 to mark unequal
	
	jmp exitsimple

exitsmaller:
	
	mov ax,-1
	
exitsimple: 
	pop ds
	pop es
	pop di
	pop si
	pop dx
	pop bx
	pop cx
	pop bp
	ret 8

start: 

	push ds 		; push segment of first string
	mov ax, msg1
	push ax 		; push offset of first string
	push ds 		; push segment of second string
	mov ax, msg2
	push ax 		; push offset of second string
	call strcmp 	; call strcmp subroutine
	
	
	
	mov ax, 0x4c00 	; terminate program
	int 0x21
