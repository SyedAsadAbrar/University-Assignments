;Write a code that reads two integers from memory and divides them and 
;places the quotient in al and remainder in ah. Declare both the dividend 
;and divisor as db (not dw). (you cannot use div instruction)

[org 0x100]

mov bl, [num1]			;bl=dividend
mov cl, [num2]			;cl=divisor
l1:
	mov ah, bl
	cmp bl, cl
	jge l2
	jmp exit

l2:
	sub bl, cl
	add al, 1
	;mov ah, bl
	jmp l1
	
exit:
	mov ax, 0x4c00		;end program
	int 0x21

num1: db 3
num2: db 3