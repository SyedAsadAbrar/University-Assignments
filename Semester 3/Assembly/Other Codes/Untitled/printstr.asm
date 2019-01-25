[org 0x100]

jmp start

brick: db '        '
brick_row: dw 0x03
brick_col: dw 0x03
brick_count: db 7
brick_lines: db 3
brick_color: dw 76

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

start:
	push word[brick_color]
	push word[brick_row]
	push word[brick_col]
	push brick
	mov ax, 8
	push ax
	call printstr


mov ax, 0x4c00
int 0x21