; multitasking and dynamic thread registration
[org 0x0100]

start:
	mov ax, 1101010101101001b
	mov bx, ax
	mov cx, ax
	and bx, 0xAAAA
	and cx, 0x5555
	shr bx, 1
	shl cx, 1
	mov dx, 0
	mov dx, cx
	or dx, bx
	mov ax, 0x4c00
	int 0x21
	
string: db 11010010b