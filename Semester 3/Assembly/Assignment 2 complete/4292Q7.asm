;AX contains a non-zero number. Count the number of ones in it
;and store the result back in AX. Repeat the process on the 
;result (AX) until AX contains one. Calculate in BX the 
;number of iterations it took to make AX one.

[org 0x0100]
	
mov ax, 1100010110100011b

l2:
	mov cx, 1
	mov dx,	0
	l1:
		test cx, ax
		jz skip
		inc dx
		skip:
			shl cx, 1
			cmp cx, 0
			jne l1			
	mov ax, dx
	inc bx
	cmp ax, 1
	jne l2
	
exit:
	mov ax, 0x4c00
	INT 21h