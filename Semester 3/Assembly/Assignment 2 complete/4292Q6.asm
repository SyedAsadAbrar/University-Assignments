;AX contains a number between 0-15. Write code to complement the corresponding bit in BX. 
;For example if AX contains 6; complement the 6th bit of BX.

[org 0x0100]

mov ax, 5
mov bx, 0xA342
mov cx, 1
mov dx, 1

cmp ax, 0
je exit
shifting:
	cmp dx, ax
	je cpl
	inc dx
	shl cx, 1
	jmp shifting
	
cpl:
	XOR bx, cx
	
exit:
	mov ax, 0x4c00
	INT 21h