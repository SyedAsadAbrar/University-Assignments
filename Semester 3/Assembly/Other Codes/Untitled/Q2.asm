;Write a program to swap every pair of bits in the AX register
[org 0x0100]

mov ax, 0x2112
mov bh, ah
ror bh, 4
mov ah, bh
mov bl, al
ror bl, 4
mov al, bl

exit:
	mov ax, 0x4c00
	INT 21h