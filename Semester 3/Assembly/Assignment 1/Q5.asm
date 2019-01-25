;Suppose we have a 2D array that is stored as 1 D array in memory under 
;the label matrix. Using row major order to access the elements of this
;2D array, write an assembly program that finds the transpose and places
;it in another 2D array named transpose. You would need two more variables
;in memory for storing total number of rows and columns of matrix 

[org 0x100]

mov ax, 0						;temp variable
mov si, 0						;for taking value from original matrix
mov di, 0						;for adressing transpose
mov cx, 0						;current number of elements
mov dl, [matrix_columns]		;total number of elements
mov bx, 0						;current number of column(s) of transpose

l2:
	add dx, [matrix_rows]
	inc ax
	cmp ax, [matrix_rows]
	jl l2
	
mov ax, 0

l1:
	mov al, [matrix+si]
	mov [transpose+di], al
	add si,[matrix_columns]
	inc bx
	inc cx
	inc di
	cmp bx, [matrix_rows]
	je si_reset
	backl1:
			cmp cx, dx
			jl l1
	
mov ax, [matrix_columns]
mov [tranpose_rows], ax
mov ax, [matrix_rows]
mov [transpose_columns], ax	

exit:
	mov ax, 0x4c00
	int 0x21
	
;Function labels
si_reset:
		add byte[si_iterator], 1
		mov si, [si_iterator]
		mov bx, 0
		jmp backl1
	
;Variables	
matrix: db 1,2,3,4,5,6,7,8
matrix_rows: dw 2
matrix_columns: dw 4

si_iterator: dw 0

transpose: db 0
transpose_rows: dw 0
transpose_columns: dw 0