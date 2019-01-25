[org 0x100]

jmp start

character: db '*'
base: db 9

delay:
		push bp 
		mov bp, sp
		push cx
		mov cl, 255
		clloop:
			sub cl, 1
			mov ch, 255
			chloop:
				sub ch, 1
				cmp ch, 0
				jnz chloop
			cmp cl, 0
			jnz clloop
		mov cl, 255
		clloop1:
			sub cl, 1
			mov ch, 255
			chloop1:
				sub ch, 1
				cmp ch, 0
				jnz chloop1
			cmp cl, 0
			jnz clloop1
		mov cl, 255
		clloop2:
			sub cl, 1
			mov ch, 255
			chloop2:
				sub ch, 1
				cmp ch, 0
				jnz chloop2
			cmp cl, 0
			jnz clloop2
		exitdelay:
			pop cx
			pop bp
			ret

clrscr: 
		push ax
		push cx
		push di
		push si
		mov ax, 0xb800
		mov es, ax ; point es to video base
		mov ax, 0x0720 ; space char in normal attribute
		mov cl, dl; number of screen locations
		cld ; auto increment mode
		rep stosw ; clear the whole screen
		pop si
		pop di
		pop cx
		pop ax
		ret
		
drawtri:
		push bp
		mov bp, sp
		push ax
		push bx
		push cx
		push dx
		push di
		push si
		mov dl, [base]
		mov dh, [character]
		mov cx, 0
		mov al, 25
		l2:
			mov byte[bp+4], 0
			mov bx, 0
			push si
			l3:
				call delay
				call clrscr
				call delay
				add si, bx
				mov di, si	
				mov bx, 160
				l4:
					mov [es:si], dh
					add si, 2
					inc cl
					cmp cl, dl
					jne l4
				mov cx, 0
				mov si, di
				add byte[bp+4], 1
				cmp [bp+4], al
				jne l3
			inc ah
			dec al
			mov cx, 0
			sub dl, 2
			pop si
			mov di, si
			add si, 2
			cmp dl, 0
			jg l2
		pop si
		pop di
		pop dx
		pop cx  
		pop bx
		pop ax
		pop bp
		ret 2
		
start:
	mov ax, 0xb800
	mov es, ax
	mov si, 20
	sub sp, 2
	call drawtri

mov ax, 0x4c00
int 0x21