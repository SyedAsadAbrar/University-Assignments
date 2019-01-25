

[ORG 0x0100]	
mov ax,[x];move answer in a
mov bx,[x];move answer in b
mov cx,1
mov dx,1
mov si,[y]
cmp si,1
je Exit
cmp si,0
je cond

cmp cx,[y];if i<b,then i++
jne l1


l1:
	cmp dx,[x];if j<a
	jne l2
	mov dx,1
	mov bx,ax
	inc cx
	cmp cx,[y]
	jne l1
	jmp Exit

l2:
	add ax,bx;answer= answer+increment
	inc dx
	cmp dx,[x]
	jne l2
	cmp cx,[y]
	jne l1


	
Exit:
	;mov bx,ax
	mov [z],ax;put value of bx at Z (which is the answer)
	MOV  ax, 0x4C00	;
	INT 0x21	; Terminate Program	


cond:;for when y=0
	mov bx,1
	MOV  ax, 0x4C00	;
	INT 0x21
	
x: dw 5;value
y: dw 3;power 
z: dw 0;memory label reserved for storing value

