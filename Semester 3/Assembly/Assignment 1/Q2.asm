;Write a code to calculate x^y without using multiplication. 
;(Hint: use add and loop(s)) Store the result in a memory label. 
;X and Y both should also be declared as memory labels. (db or dw?)

[org 0x0100]

mov ax, 0			;inner loop iterator
mov cx, 0			;outer loop iterator
mov bx, 0			;temp variable
mov word[R], 1
 
cmp word[Y], 0
je exit
 
l1:
	mov bx, [toplus]
	add [R], bx
	inc ax
	cmp ax, [X]
	jl l1

mov bx, [R]
mov [toplus], bx
inc cx
mov ax, 0
cmp cx, [Y]
jl reset

exit:
	mov ax , 0x4c00		;end program
	int 0x21

;Variable declaration
X: dw	2			;byte limits the numbers from 0 to 255, for
Y: dw	0			;greater range we use dw

R: dw	0			;result

toplus: dw 1

;Function
reset:
	mov word[R], 0
	jl l1
