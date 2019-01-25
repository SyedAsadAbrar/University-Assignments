[org 0x100]

mov ax, [n1]
cmp ax, [n2]
jle A
back:
	mov ax, [n2]
	cmp ax, [n1]
	jle B
	back1:
		mov ax, [n3]
		jmp lastassignment

exit:
	mov ax, 0x4c00
	int 0x21

n1: dw 10
n2: dw 5
n3: dw 2

A:
	cmp ax, [n3]
	jle lastassignment
	jmp back
	
lastassignment:
	mov DX, ax
	jmp exit
	
B:
	cmp ax, [n3]
	jle lastassignment
	jmp back1
	