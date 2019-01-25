[org 0x100]

jmp start

string1: db 'Hello world'
string2: db 'wor',0
row: dw 1
col: dw 0
len: dw 11

; subroutine to clear the screen
clrscr: 
	push es
	push ax
	push cx
	push di
	
	mov ax, 0xb800
	mov es, ax 			; point es to video base
	xor di, di 			; point di to top left column
	mov ax, 0x0720 		; space char in normal attribute
	mov cx, 2000 		; number of screen locations
	
	cld 				; auto increment mode
	rep stosw 			; clear the whole screen
	
	pop di
	pop cx
	pop ax
	pop es
	ret

; subroutine to print a string
; takes the x position, y position, attribute, address of string and
; its length as parameters
printstr: 
	push bp
	mov bp, sp
	push es
	push ax
	push cx
	push si
	push di
	
	mov ax, 0xb800
	mov es, ax 			; point es to video base
	mov ax, 0
	mov al, 80 			; load al with columns per row
	mul byte [bp+10] 	; multiply with y position
	add ax, [bp+12] 	; add x position
	shl ax, 1 			; turn into byte offset
	mov di,ax 			; point di to required location
	mov si, [bp+6] 		; point si to string
	mov cx, [bp+4] 		; load length of string in cx
	mov ah, [bp+8] 		; load attribute in ah
	cld 				; auto increment mode
	
	nextchar: 
		lodsb			; load next char in al
		stosw 			; print char/attribute pair
		loop nextchar 	; repeat for the whole string
		
	pop di
	pop si
	pop cx
	pop ax
	pop es
	pop bp
	ret 10
	
strlen: 
	push bp
	mov bp,sp
	push es
	push cx
	push di

	les di, [bp+4] ; point es:di to string
	mov cx, 0xffff ; load maximum number in cx
	xor al, al ; load a zero in al
	repne scasb ; find zero in the string
	mov ax, 0xffff ; load maximum number in ax
	sub ax, cx ; find change in cx
	dec ax ; exclude null from length

	pop di
	pop cx
	pop es
	pop bp
	ret 4
	
trim:
	push bp
	mov bp, sp
	sub sp, 2	; space for storing address of position where to end string
	sub sp, 2	; starting index of str 1 on display
	push ax
	push bx
	push cx
	push dx
	
	mov ax, 0xb800
	mov es, ax
	mov al, 80
	mul byte [bp+8]
	add ax, [bp+10]
	shl ax, 1
	mov di, ax		; moving location of string1 on screen to di
	push di
	mov [bp-4], di
	
	push ds 		; push segment of string2
	mov ax, [bp+4]
	push ax 		; push offset of string
	call strlen
	mov dx, ax		; length of str2
	push dx
		
	mov ah, 0
	mov cx, [bp+6]	; length of str1
	
	main:
		mov si, [bp+4]
		
		cld
		
		mov bx, 0
		
		l1:
			cmp cx, 0
			je exittrim
			lodsb
			dec dx
			cmp al, [es:di]
			jne startagain
			je checkdx
			backl1:
					add di, 2
					dec cx
					jmp l1
			
		startagain:
					sub si, 1
					pop dx
					push dx
					
		checkdx:
				cmp dx, 0
				jne backl1
		
		inc ah
		pop dx				; length of str2
		mov bx, dx			; bx-len(str2)
		push bx
		add di, 2	
		mov si, di
					
		startindex:
			sub si, 2
			dec bx
			cmp bx, 0
			jne startindex
					
		push si 			; to restart trimming again 
		mov al, 0
		push ax				; count of occurrences
		mov ax, 0
		mov bx, [bp-4]
		
		presenttrim:			
				push cx
				mov cx, [bp+6]
				endindex:
						add bx, 2
						loop endindex
				mov cx, dx			; length of str2
				trimloop:
					mov ax, [es:di]
					mov [es:si], ax
					add si, 2
					add di, 2
					cmp di, bx
					je trimexit
					cmp ax, 0
					je trimexit
					mov [bp-2], si
					loop trimloop
				trimexit:
				pop cx
					
		pop ax
		pop si
		pop dx
		mov di, si
		mov si, [bp+4]
		loop main
	
	exittrim:
					mov cx, [bp+6]
					pop di
					endcalc:
						add di, 2
						loop endcalc
					mov ax, 0x0720
					mov si, [bp-2]
					add si, 2
					mov bx, si
					mov si, di
					mov di, bx
					cmp si, di
					jne addspaces
					exitroutine:
						pop dx
						pop cx
						pop bx
						pop ax
						pop ax
						pop bp
						ret 8
						
	addspaces:
			stosw
			cmp si, di
			jne addspaces
			je exitroutine

start:
	;call clrscr
	mov ax, [col]		;x position
	push ax
	mov ax, [row]		;y position
	push ax
	mov ax, 0x07 		; blue on black attribute
	push ax 			; push attribute
	mov ax, string1
	push ax
	push word [len]
	call printstr
	
	mov ax, [col]
	push ax
	mov ax, [row]
	push ax
	push word [len]
	mov ax, string2
	push ax
	call trim

exit:
mov ax,0x4c00
int 0x21