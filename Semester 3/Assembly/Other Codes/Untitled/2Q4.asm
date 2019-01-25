;Calculate the number of one bits in BX and complement an equal 
;number of least significant bits in AX. HINT: Use the XOR instruction
[org 0x0100]

mov ax, 0110101101110111b
mov bx, 0101111010111011b
mov cx, 16	;loop counter
mov dx, 0	;1 bits counter

clc
l1:
	rcr bx, 1
	jc one_count
	back:
		dec cx
		cmp cx, 0
		jg l1

mov cx, 16
cpl:
	xor ax, 1
	ror ax, 1
	dec cx
	dec dx
	cmp dx, 0
	jg cpl

l2:
	ror ax, 1
	dec cx
	cmp cx, 0
	jg l2
		
exit:
	mov ax, 0x4c00
	INT 21h
	
one_count:
			inc dx
			jmp back
		