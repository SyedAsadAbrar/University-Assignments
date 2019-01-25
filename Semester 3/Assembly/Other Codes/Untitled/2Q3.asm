;Swap nibbles in each byte of AX register
;Assuming swapping nibbles means to swap first and second nibble and so on
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