[org 0x100]

mov bx, n1
mov cx, [bx]
mov dx, [n2]
add cx, dx

mov bx, [n3]
sub cx,bx
mov n3, cx

add cx, [n4]
sub cx, 10

mov ax, 0x4c00 		; terminate program
int 0x21

n1 :dw  5
n2: dw  10
n3: db  6
n4: db  30
