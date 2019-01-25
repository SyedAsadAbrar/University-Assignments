;Suppose we have a 2D array that is stored as 1 D array in memory under 
;the label matrix. Using row major order to access the elements of this
;2D array, write an assembly program that finds the transpose and places
;it in another 2D array named transpose. You would need two more variables
;in memory for storing total number of rows and columns of matrix 

[org 0x100]

mov ax, 0						;temp variable
mov si, 0						;for placing in transpose
mov di, 0						;for adressing transpose
mov cx, 0						;current number of elements
mov dl, [matrix_columns]		;total number of elements
mov bx, 0						;current number of row(s) of transpose

l2:
	add dx, [matrix_rows]
	inc ax
	cmp ax, [matrix_rows]
	jl l2
	
mov ax, 0
	
l1:
	mov al, [matrix+si]
	mov [transpose+di], al
	inc cx
	inc bx
	
	inc di
	cmp bx, [matrix_rows]
	jle inc_column
	back:
		cmp bx, [matrix_rows]
		je inc_row
		back1:
			cmp cx, dx
			jle l1

exit:
	mov ax, 0x4c00
	int 0x21
	
;Variables	

matrix: db 1,2,3,4,5,6,7,8
matrix_rows: dw 2
matrix_columns: dw 4

transpose: db 0

si_iterator: db 0

;Function labels
inc_column:
			add si, [matrix_columns]
			jmp back

inc_row:
		inc bx
		;mov si, bx
		jmp back1