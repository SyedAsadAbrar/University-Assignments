[org 0x100]
jmp start

oldisr: dd 0

;display
display_text: db 'Brick Breaker'
start_text: db 'Press space to start or any other key to exit...'

;brick printing;
brick: db '         '
brick_row: dw 0x03, 0x05, 0x07
brick_col: dw 0x03, 0x10, 0x1D, 0x2A, 0x37, 0x44
brick_end: dw 0x0C, 0x19, 0x26, 0x33, 0x40, 0x4D
row_found: dw 0
col_found: dw 0
brick_count: db 6
brick_lines: db 3
brick_color: dw 64
hit_before: db 0

;text;
leveltext: db 'Level:   '
livestext: db '   Remaining Lives:   '
tlivestext: db '    Total Lives:  3 '
scoretext: db '   Score:   '
level: dw 1
remaininglives: dw 3
scorenum: dw 0
ver_border: db '|'
border_row: dw 0x03
border_col: dw 0x02
border_count: db 2

;bar
bar: db '         '
bar_row: db 0x17
bar_col: db 0x24
bar_color: db 0x30

;ball
ball: db 'O'
ball_color: db 0x07
ball_row: dw 0x16
ball_col: dw 0x28
life_loss: db 0

;brick collision
DRL: db 0	;ball coming from right and below onto brick
DLR: db 0	;ball coming from left and below onto brick
DU: db 0	;ball coming from below onto brick/wall
UD: db 0	;ball coming from above onto brick/wall

;bar collision
ball_l_edge: db 0x18
ball_middle: db 0x1C
ball_r_edge: db 0x21

;wall collision
ver: 	db 0 		;if 1 then ball is going up, -1 if down
hor: 	db 0		;if 1 ball coming towards right boundary, if -1 towards left boundary

printstr: 
	push bp
	mov bp, sp
	push es
	push ax
	push bx
	push cx
	push dx
	push si
	push di

	mov ax, 0xb800
	mov es, ax ; point es to video base

	mov di, 80 ; load di with columns per row
	mov ax, [bp+10] ; load ax with row number
	mul di ; multiply with columns per row
	mov di, ax ; save result in di
	add di, [bp+8] ; add column number
	shl di, 1 ; turn into byte count

	mov si, [bp+6] ; string to be printed
	mov cx, [bp+4] ; length of string
	mov ah, [bp+12] ; normal attribute is fixed

	nextchar: 
	mov al, [si] ; load next char of string
	mov [es:di], ax ; show next char on screen
	add di, 2 ; move to next screen location
	add si, 1 ; move to next char
	loop nextchar ; repeat the operation cx times

	pop di
	pop si
	pop dx
	pop cx
	pop bx
	pop ax
	pop es
	pop bp
	ret 10

printnum: 
	push bp
	mov bp, sp
	push es
	push ax
	push bx
	push cx
	push dx
	push di
	
	mov ax, 0xb800
	mov es, ax ; point es to video base
	mov ax, [bp+4] ; load number in ax
	mov bx, 10 ; use base 10 for division
	mov cx, 0 ; initialize count of digits

	nextdigit: 
		mov dx, 0 ; zero upper half of dividend
		div bx ; divide by 10
		add dl, 0x30 ; convert digit into ascii value
		push dx ; save ascii value on stack
		inc cx ; increment count of values
		cmp ax, 0 ; is the quotient zero
		jnz nextdigit ; if no divide it again
		
		mov di, [bp+6] ; point di to top left column	
	
	nextpos: 
		pop dx ; remove a digit from the stack
		mov dh, 0x07 ; use normal attribute
		mov [es:di], dx ; print char on screen
		add di, 2 ; move to next screen location
		loop nextpos ; repeat for all digits on stack

	pop di
	pop dx
	pop cx
	pop bx
	pop ax
	pop es
	pop bp
	ret 4
		
brickcollide:
	push ax
	push bx
	push cx
	push dx
	push di
	push si
	mov di, 0		;for col
	mov si, 0		;for row
	mov ax, [ball_row]
	mov cx, 3
	l4:
		mov bx, [brick_row+si]
		cmp ax, bx
		jne skip
		mov word[row_found], ax
		jmp outerl5
		skip:
			add si, 2
		loop l4
		
	outerl5:
		cmp word[row_found], 0
		je exitbrick
		mov cx, 7
		mov ax, [ball_col]
		l5:
			cmp ax, [brick_col+di]
			jl skip2
			cmp ax, [brick_end+di]
			jg skip2
			mov bx, [brick_col+di]
			mov word[col_found], bx
			jmp delbrick
			skip2:
				add di, 2
			loop l5
	
	delbrick:
		cmp word[col_found], 0
		je exitbrick
		
		mov bx, 80 ; load bx with columns per row
		mov ax, [row_found] ; load ax with row number
		mul bx ; multiply with columns per row
		mov bx, ax ; save result in bx
		add bx, [col_found] ; add column number
		shl bx, 1 ; turn into byte count
		
		mov ax, 0xb800
		mov es, ax
		
		cmp word[es:bx], 0x0720
		je exitbrick
		
		del:
			mov ax, 0x07
			push ax
			push word[row_found]
			push word[col_found]
			push brick
			mov ax, 9
			push ax
			call printstr
			add word[scorenum], 20
			call print_bg
	
	exitbrick:
		mov word[row_found], 0
		mov word[col_found], 0
		pop si
		pop di
		pop dx
		pop cx
		pop bx
		pop ax
		ret
		
barcollide:
	push ax
	mov ax, 0
	cmp byte[ball_row], 0x16
	jne exitbar
	mov al, [ball_col]
	cmp al, [ball_l_edge]
	jl exitbar
	cmp al, [ball_r_edge]
	jg exitbar
	cmp al, [ball_middle]
	je hitmiddle
	jl hitleft
	
	hitright:
		mov byte[hor], 1
		mov byte[ver], -1
		jmp exitbar
		
	hitmiddle:
		mov byte[hor], 0
		mov byte[ver], -1
		jmp exitbar
		
	hitleft:
		mov byte[hor], -1
		mov byte[ver], -1
	
	exitbar:
		pop ax
		ret
	
wallcollide:
	;upper boundary
	cmp byte[ball_row], 0x03
	jne checkright
	je chng_up
	
	;right boundary
	checkright:
		cmp byte[ball_col], 0x4C
		jne checkleft
		cmp byte[ver], 1
		je chng_rt
		cmp byte[ver], -1
		je chng_rt
		
	;left boundary
	checkleft:
		cmp byte[ball_col], 0x03
		jne nowallcollision
		cmp byte[ver], 1
		je chng_lt
		cmp byte[ver], -1
		je chng_lt
			
	chng_up:
		mov byte[ver], 1
		jmp nowallcollision
			
	chng_rt:
		mov byte[hor], -1
		jmp nowallcollision
		
	chng_lt:
		mov byte[hor], 1
		jmp nowallcollision
		
	nowallcollision:
		ret
		
movement:
	push ax
	push si
	mainloop:
		call wallcollide
		call delay
		mov byte[ball], ' '
		call print_ball
		mov al, [ver]
		add byte[ball_row], al
		mov al, [hor]
		add byte[ball_col], al
		call brickcollide
		mov byte[ball], 'O'
		call print_ball
		call barcollide
		cmp byte[ball_row], 0x18
		jne mainloop
	sub byte[remaininglives], 1
	mov byte[ball], ' '
	call print_ball
	mov byte[ball], 'O'
	call print_bg
	pop si
	pop ax
	ret

in_movement:
	push ax
	mov ax, 0
	mov byte[ver], -1
	mov byte[hor], 1
	loopmov:
		call delay
		mov byte[ball], ' '
		call print_ball
		mov al, [ver]
		add byte[ball_row], al
		add byte[ball_col], 0x1
		call brickcollide
		mov byte[ball], 'O'
		call print_ball
		cmp byte[ball_row], 0x03
		jne loopmov
	pop ax
	ret

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
	;clloop2:
	;	sub cl, 1
	;	mov ch, 255
	;	chloop2:
	;		sub ch, 1
	;		cmp ch, 0
	;		jnz chloop2
	;	cmp cl, 0
	;	jnz clloop2
	exitdelay:
		pop bp
		ret

clrscr: 
	push bp
	mov bp, sp
	
	push es
	push ax
	push cx
	push di
	
	mov ax, 0xb800
	mov es, ax ; point es to video base
	mov di, [bp+4] ; point di to top left column
	mov ax, 0x0720 ; space char in normal attribute
	mov cx, 2000 ; number of screen locations
	sub cx, di
		
	continue:
		cld ; auto increment mode
		rep stosw ; clear the whole screen
		
		pop di
		pop cx
		pop ax
		pop es
		pop bp
		ret 2
		
print_bg:
	push ax
	push bx
	push cx
	push dx
	mov ah, 0x13
	mov al, 0
	mov bh, 0
	mov bl, 7
	mov dx, 0x0108
	mov cx, 9
	push cs
	pop es
	mov bp, leveltext
	int 0x10
	mov dx, 0x0112
	mov cx, 22
	mov bp, livestext
	int 0x10
	mov dx, 0x0128
	mov cx, 20
	mov bp, tlivestext
	int 0x10
	mov dx, 0x013C
	mov cx, 12
	mov bp, scoretext
	int 0x10
	mov ax, 192
	push ax
	push word[level]
	call printnum
	mov ax, 238
	push ax
	push word[remaininglives]
	call printnum
	mov ax, 302
	push ax
	push word[scorenum]
	call printnum
	push word[border_col]
	push word[border_row]
	l2:
		mov bx, [border_col]
		mov cx, 2
		l3:
			mov ax, 4
			push ax
			push word[border_row]
			push bx
			add bx, 0x4B
			push ver_border
			mov ax, 1
			push ax
			call printstr
			loop l3
		add word[border_row], 1
		cmp word[border_row], 0x18
		jne l2
	pop ax
	mov [border_row], ax
	pop ax
	mov [border_col], ax
	pop dx
	pop cx
	pop bx
	pop ax
	ret
		
print_display:
	push ax
	push cx
	push bx
	push si
	mov bx, 0
	main_print:
		mov si, 0
		print_loop:			
			push word[brick_color]
			push word[brick_row+bx]
			push word[brick_col+si]
			add si, 2
			push brick
			mov cx, 9
			push cx
			call printstr
			sub byte[brick_count], 1
			cmp byte[brick_count], 0
			jne print_loop
		shr byte[brick_color], 1
		mov byte[brick_count], 6
		sub byte[brick_lines], 1
		add bx, 0x02
		cmp byte[brick_lines], 0
		jne main_print	
	pop si
	pop bx
	pop cx
	pop ax
	ret
	
print_bar:
	mov ah, 0x13
	mov al, 0
	mov bh, 0
	mov bl, [bar_color]
	mov dh, [bar_row]
	mov dl, [bar_col]
	mov cx,	9
	push cs
	pop es
	mov bp, bar
	int 0x10
	ret
	
print_ball:
	mov ah, 0x13
	mov al, 0
	mov bh, 0
	mov bl, [ball_color]
	mov dh, [ball_row]
	mov dl, [ball_col]
	mov cx, 1
	push cs
	pop es
	mov bp, ball
	int 0x10
	ret
		
kbisr:
	push ax
	push es
	
	mov ax, 0xb800
	mov es, ax
	
	in al, 0x60
	cmp al, 0x4B		;left arrow
	jne nextcmp
	
	mov byte[bar_color], 0
	call print_bar
	mov byte[bar_color], 0x30
	sub byte[bar_col], 1
	cmp byte[bar_col], 0x02
	jne no_lfix
	mov byte[bar_col], 0x44
	no_lfix:
	call print_bar
	
	nextcmp:
		cmp al, 0x4D	;right arrow
		jne nomatch
		
		mov byte[bar_color], 0
		call print_bar
		mov byte[bar_color], 0x30
		add byte[bar_col], 1
		cmp byte[bar_col], 0x45
		jne no_rfix
		mov byte[bar_col], 0x03
		no_rfix:
		call print_bar
		
	nomatch:
		call bardimensions
		pop es
		pop ax
		jmp far [oldisr]
		
bardimensions:
	push ax
	mov ax, 0
	mov al, [bar_col]
	mov [ball_l_edge], al
	add al, 0x4
	mov [ball_middle], al
	add al, 0x5
	mov [ball_r_edge], al
	pop ax
	ret

start:
	;hooking keyboard
	xor ax, ax
	mov es, ax
	mov ax, [es:9*4]
	mov [oldisr], ax
	mov ax, [es:9*4+2]
	mov [oldisr+2], ax
	cli
	mov word[es:9*4], kbisr
	mov [es:9*4+2], cs
	sti
	
	;initial prompt
	mov ax, 0
	push ax
	call clrscr
	
	mov ah, 0x13
	mov al, 0
	mov bh, 0
	mov bl, 0x07
	mov dx, 0xA20
	mov cx, 13
	push cs
	pop es
	mov bp, display_text
	int 0x10
	
	mov ah, 0x13
	mov al, 0
	mov bh, 0
	mov bl, 0x07
	mov dx, 0xD10
	mov cx, 48
	push cs
	pop es
	mov bp, start_text
	
	int 0x10
	
	mov ah, 0
	int 0x16
	
	cmp al, 32
	jne exitgame
	
	;initial display
	mov ax, 0
	push ax
	call clrscr
	call print_bg
	call print_display
	
	;gamestart	
	gameloop:
		mov word[ball_row], 0x16
		mov word[ball_col], 0x28
		call print_bar
		call print_ball
		call in_movement
		call movement
		cmp word[remaininglives], 0
		jne gameloop
		
	mov ax, 0
	push ax
	call clrscr
	
	l1:
		mov ah, 0
		int 0x16
		
		cmp al, 27
		jne l1
	
exitgame:
	;unhooking keyboard
	xor ax, ax
	mov es, ax
	mov ax, [oldisr]
	mov bx, [oldisr+2]
	cli
	mov [es:9*4], ax
	mov [es:9*4+2], bx
	sti

	mov ax, 0x4c00
	int 0x21