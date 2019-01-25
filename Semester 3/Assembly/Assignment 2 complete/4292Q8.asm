[org 0x100]

jmp start

multiplicand: dq 1300
multiplier: dq 500
result: dq 0

start:
		mov cl, 64
		mov dx, [multiplier]
		
checkbit:
		shr dx, 1
		jnc skip
		mov ax, [multiplicand]
		add [result], ax
		mov ax, [multiplicand+2]
		add [result+2], ax
		
skip:
	shl word[multiplicand], 1
	rcl word[multiplicand+2], 1
	dec cl
	jnz checkbit


exit:
mov ax, 0x4c00
int 0x21