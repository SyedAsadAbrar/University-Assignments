[org 0x100]

mov si, 0		;index for start
mov di, [arrsize]
dec di			;index for end
mov cx, 1		;Initially cx is 1, it will be 0 when given array is not a palindrome
call checkpal	;main function
jmp exit

checkpal:
	start:
		cmp si, di			;condition to terminate loop
		jge return_start	;if start>=end, this implies given array is a palindrome
		sub sp, 2			;space for return val
		mov dl,[arr+si]		;to push
		push dx
		mov dl,[arr+di]
		push dx
		call palindrome		;indirect recursion
		pop bx				;return val
		AND cx, bx			;ANDing return val with cx for final answer
		ret
		jmp exit
	palindrome:
		push bp
		mov bp, sp
		main:
			mov al, [bp+6]
			cmp al, [bp+4]
			jne return0		;if characters at corresponding indexes from start and end do not match
			add si, 1		;otherwise proceed
			sub di, 1
			return1:
				mov word[bp+8], 1
				jmp skip
			return0:
				mov word[bp+8],0
				jmp return
			skip:
				call start	;indirect recursion
			return:
				pop bp
				ret 4
	return_start:
				ret
				
exit:
	mov ax, 0x4c00
	int 0x21

arr: db 0,1,4,3,2,1,0
arrsize: dw 7