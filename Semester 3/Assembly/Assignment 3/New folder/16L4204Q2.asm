[org 0x100]
jmp start
top		:dw 2
bottom	:dw 10

right 	:dw 15
left 	:dw 0

Ctop	:dw	0
Cbottom	:dw	0
Cright	:dw	0
Cleft	:dw	0

length: dw 0
width: dw 0

value:
	
	stosw 
	loop value
	
	pop es
	pop cx
	pop di
	pop si
	pop ax
	
	ret

;First push x position than push y position 
getPosition:
	push bp
	mov bp,sp
	
	push ax

	mov al, 80 					; load al with columns per row
	mul byte [bp + 4] 			; multiply with y position
	add ax,  [bp + 6] 			; add x position
	shl ax, 1 					; turn into byte offset
	
	mov [bp+8],ax
	
	pop ax
	pop bp
	
	ret 4

;push top
;push bottom
;push left
;push right	
getCenter:
	push bp	
	mov bp,sp
	
	push ax
	push bx
	push cx
	push dx
	push di
	
	mov ax,[bp+8]
	mov bx,[bp+10]
	
	sub ax,bx
	mov cx,ax
	inc ax
	
	mov dx,[bp+4]
	mov bx,[bp+6]
	sub dx,bx
	mov di,dx
	inc dx
	
	
	mov bx,25
	sub bx,ax
	shr bx,1
	mov ax,bx
	inc ax
	mov [Ctop],ax
	
	mov bx,80
	sub bx,dx
	shr bx,1
	mov dx,bx
	inc dx
	mov [Cleft],dx
	
	add cx,ax
	add di,dx
	
	mov [Cright],di
	mov [Cbottom],cx
	
	
	push 0
	push dx
	push ax
	call getPosition
	pop ax
	
		
	mov [bp+12],ax
	
	push 0
	push di
	push cx
	call getPosition
	pop ax
	mov [bp+14],ax
	
	
	pop di
	pop dx
	pop cx
	pop bx
	pop ax
	
	pop bp
	ret 8

;push start of center
;push start of patch
;push height
;push width

Drawtop2bottom:
	push bp
	mov bp,sp
	
	push ax
	push cx
	push dx
	push si
	push di
	
	push ds
	push es

	mov ax,0xb800
	mov es,ax
	
	push es
	pop ds
	
	mov si,[bp+8]
	mov di,[bp+10]
	mov cx,[bp+6]
	mov dx,[bp+4]
	cld
;{	
outer1:
	push si
	push di
	mov dx,[bp+4]
;{
inner1:
	movsw
	sub dx,1
	jnz inner1
;}
	pop di
	pop si
	add si,160
	add di,160
	
	loop outer1
;}	
	pop es
	pop ds
	pop di
	pop si
	pop dx
	pop cx
	pop ax
	
	pop bp
	ret 8



;push start of center
;push start of patch
;push height
;push width

Drawleft2right:
	push bp
	mov bp,sp
	
	push ax
	push cx
	push dx
	push si
	push di
	
	push ds
	push es

	mov ax,0xb800
	mov es,ax
	
	push es
	pop ds
	
	mov si,[bp+8]
	mov di,[bp+10]
	mov cx,[bp+4]
	mov dx,[bp+6]

;{	
outer3:
	push si
	push di
	mov dx,[bp+6]
;{
inner3:
	mov ax,[ds:si]
	mov [es:di],ax
	
	add di,160
	add si,160
	
	sub dx,1
	jnz inner3
;}
	pop di
	pop si
	add si,2
	add di,2
	
	loop outer3
;}	
	pop es
	pop ds
	pop di
	pop si
	pop dx
	pop cx
	pop ax
	
	pop bp
	ret 8



	
;push end of center
;push end of patch
;push height
;push width
	
Drawbottom2top:


	push bp
	mov bp,sp
	
	push ax
	push cx
	push dx
	push si
	push di
	
	push ds
	push es

	mov ax,0xb800
	mov es,ax
	
	push es
	pop ds
	
	mov si,[bp+8]
	mov di,[bp+10]
	mov cx,[bp+6]
	mov dx,[bp+4]
	std
;{	
outer2:
	push si
	push di
	mov dx,[bp+4]
;{
inner2:
	movsw
	sub dx,1
	jnz inner2
;}
	pop di
	pop si
	
	sub si,160
	sub di,160
	
	loop outer2
;}	
	pop es
	pop ds
	pop di
	pop si
	pop dx
	pop cx
	pop ax
	
	pop bp
	ret 8
	

;push end of center
;push end of patch
;push height
;push width
	
Drawright2left:


	push bp
	mov bp,sp
	
	push ax
	push cx
	push dx
	push si
	push di
	
	push ds
	push es

	mov ax,0xb800
	mov es,ax
	
	push es
	pop ds
	
	mov si,[bp+8]
	mov di,[bp+10]
	mov cx,[bp+4]
	mov dx,[bp+6]
	std
;{	
outer4:
	push si
	push di
	mov dx,[bp+6]
;{
inner4:
	mov ax,[ds:si]
	mov [es:di],ax
	
	sub si,160
	sub di,160
	
	sub dx,1
	jnz inner4
;}
	pop di
	pop si
	
	sub si,2
	sub di,2
	
	loop outer4
;}	
	pop es
	pop ds
	pop di
	pop si
	pop dx
	pop cx
	pop ax
	
	pop bp
	ret 8
	


	

start:
	
	
	push 0
	mov ax,[left]
	push ax
	mov ax,[top]
	push ax
	call getPosition
	pop si
	
	push 0
	mov ax,[right]
	push ax
	mov ax,[bottom]
	push ax
	call getPosition
	pop di

	
	push 0
	push 0
	mov ax,[top]
	push ax
	mov ax,[bottom]
	push ax
	mov ax,[left]
	push ax
	mov ax,[right]
	push ax
	call getCenter
	pop dx
	pop bx
	
	
	mov ax,[bottom]
	sub ax,[top]
	inc ax
	mov [length],ax
	
	
	mov cx,[right]
	sub cx,[left]
	inc cx
	mov [width],cx
	
	mov ax,[top]
	cmp ax,[Ctop]
	jb Condition1
	ja Condition2
	je Condition3
	
Condition1:
	mov ax,[length]
	mov cx,[width]
	
	push bx
	push di
	push ax
	push cx
	call Drawbottom2top
	jmp Quit
Condition2:
	mov ax,[length]
	mov cx,[width]
	
	push dx
	push si
	push ax
	push cx
	call Drawtop2bottom
	jmp Quit

Condition3:
	mov ax,[right]
	cmp ax,[Cright]
	jb Condition4
	
	mov ax,[length]
	mov cx,[width]
	
	push dx
	push si
	push ax
	push cx
	call Drawright2left
	jmp Quit
	

Condition4:
	mov ax,[length]
	mov cx,[width]
	
	push bx
	push di
	push ax
	push cx
	call Drawleft2right
	jmp Quit


Quit:


mov ax,0x4c
int 0x21

