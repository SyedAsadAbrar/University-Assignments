;Convert c++ code(in manual) to find most frequent element in assembly language

[org 0x100]

mov si, 0			;i
mov di, 0			;j

outerloop:
		mov word[count], 1
		mov ax, si
		inc ax
		mov di, ax
		innerloop:
				mov bx, [a+di]
				cmp [a+si], bx
				je countinc
				back:
					add di, 1
					cmp di, [asize]
					jl innerloop
		mov bx, [max_count]
		cmp word[count], bx
		jg countgreater
		back1:
			add si, 1
			cmp si, [asize]
			jl outerloop

mov bx, [index]
mov ax, 0
mov al, [a+bx]
mov [most_frequent], al			

exit:
	mov ax, 0x4c00
	int 0x21
	
a: db 1,1,1,2,2
asize: dw 5
max_count: dw 0
count: dw 0
most_frequent: dw 0
index: dw 0

countinc:
		add word[count], 1
		jmp back
		
countgreater:
				mov [index], si
				mov cx, [count]
				mov [max_count], cx
				jmp back1