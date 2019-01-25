[org 0x100]

call asterisk
jmp exit

delay:
		push bp 
		mov bp, sp
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
			pop bp
			ret

clrscr: 
		push es
		push ax
		push cx
		push di
		mov ax, 0xb800
		mov es, ax ; point es to video base
		xor di, di ; point di to top left column
		mov ax, 0x0720 ; space char in normal attribute
		mov cx, 2000 ; number of screen locations
		cld ; auto increment mode
		rep stosw ; clear the whole screen
		pop di
		pop cx
		pop ax
		pop es
		ret

asterisk:
	call clrscr
	push bp 
	mov bp, sp
	mov ax, 0xb800
	mov es, ax
	mov si, 30
	mov di, 128
	mov ax, 0x072A
	mov dx, 0x0720
	mov bx, 0
	mov cx, 65535
	l3:
		l1:
			mov word[es:bx+si], ax
			mov word[es:bx+di], ax
			call delay
			mov word[es:bx+si], dx
			mov word[es:bx+di], dx
			add bx, 160
			add si, 4
			sub di, 4
			cmp si, di
			jb l1
	
		sub si, 4
		add di, 4
		
		l2:
			cmp si, 0
			jl exitl2
			mov word[es:bx+si], ax
			mov word[es:bx+di], ax
			call delay
			mov word[es:bx+si], dx
			mov word[es:bx+di], dx
			sub bx, 160
			sub si, 4
			add di, 4
			cmp si, di
			jb l2
		
		exitl2:
				mov si, 30
				mov di, 128
				mov ax, 0x072A
				mov dx, 0x0720
				mov bx, 0
				mov cx, 65535
				jmp l3
	exitast:
				pop bp
				ret
exit:
	mov ax, 0x4c00
	int 0x21