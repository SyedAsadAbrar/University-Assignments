;You are given an array of positive numbers (db type array it means use 8 
;bit registers for retrieving and comparing a value of memory).Write a 
;program that find an element (Key) in given array. If you find that 
;element than replace that element with sum of array. Otherwise append 
;that element at the end of array.

;Example: 3, 5,9,10,99,4,1,3,150,8
;Key = 1
;After My Program Array = 3, 5, 9,10,99,4,29,3,150,8
;Size = 10

[org 0x100]

mov bx, 0

sum:
	add al, [array+bx]
	inc bx
	cmp bx, [arraysize]
	jl sum

mov cl, [key]
mov bx, 0

l1:
	cmp cl, [array+bx]
	je if_found
	inc bx
	cmp bx, [arraysize]
	jl l1

mov al, [key]
mov [array+bx], al
	
exit:
	mov ax, 0x4c00
	int 0x21
	
array: db 3,5,9,10,99,4,1,3,100,8
key: db 11
arraysize: dw 10

if_found:
		mov [array+bx], al
		jmp exit