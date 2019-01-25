;Declare a 32byte buffer containing random data. Consider 
;for this problem that the bits in these 32 bytes are 
;numbered from 0 to 255. Declare another byte that contains
;the starting bit number. Write a program to copy the byte
;starting at this starting bit number in the AX register.
;Be careful that the starting bit number may not be a multiple
;of 8 and therefore the bits of the desired byte will be split into two bytes.
[org 0x0100]

mov cl, [start]
l1:
	cmp cl, 8
	jl l2
	sub cl, 8
	inc si
	jmp l1
	
l2:
	mov al, [buffer+si]
	cmp cl, 0
	je exit
	mov bl, cl
	l2shifting:
			cmp bl, 1
			je  l3
			shl ax, 1
			dec bl
			jmp l2shifting

l3:
	inc si
	mov dl, [buffer+si]
	mov bl, 8
	sub bl, cl
	inc bl
	l3shifting:
				shr dx, 1
				dec bl
				cmp bl, 0
				jg l3shifting
	or ax, dx	;12 should be in ax now
	
exit:
	mov ax, 0x4c00
	INT 21h
	
buffer: db 0xA,3,4,5,6,1,7,0xA,0xF,0xD,0xE,8,9,0,0xE,0xD,3,3,4,5,1,6,2,7,2,3,4,0xB,1,3,14,15
start: db 11