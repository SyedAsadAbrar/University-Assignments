[org 0x100]

mov si,0
mov di,0
mov cx,0
mov dx,0
Loop1:
	cmp si,[col];when col>=si
	jae Exit

Loop2:
	cmp cx,[row];when row>=cx
	jae reset
	mov al,[Arr1+si];for writing cols to rows
	mov [Arr2+di],al;putting al at address in array 2
	add di,1;increment in di, (imp: was 1 before)
	add si,[col]
	inc cx
	jmp Loop2
	
reset:
	inc dx
	mov si,dx
	mov cx,0
	jmp Loop1
	
Exit:
mov ax,0x4c00
int 0x21

Arr1: db 1,2,3,4,5,6,7,8,9,10,11,12
Arr2: db 0,0,0,0,0,0,0,0,0,0,0,0
row: dw 3
col: dw 4