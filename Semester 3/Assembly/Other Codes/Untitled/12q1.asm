; display a tick count on the top right of screen
[org 0x0100]

jmp start

tickcount: dw 0
seccount: dw 0
wordcount: dw 0
oldisr: dd 0
dest: dw 158
	
; timer interrupt service routine
timer: 
	push ax
	push es
	push di
	mov di, [cs:dest]
	cmp word[cs:tickcount], 91
	jne	nextcmp
	mov word[cs:tickcount], 0
	mov word[cs:seccount], 0
	nextcmp:
	cmp word [cs:seccount], 18 ; is the printing flag set
	jne skip
	mov cx, [cs:wordcount]
	mov ax, 0xb800
	mov es, ax
	mov ax, 0x072A
	l2:
		mov word[es:di], ax
		add di, 160
		loop l2
	mov word[cs:wordcount], 0
	mov word[cs:seccount], 0
	
	mov word[cs:dest], di
	
	skip:
	inc word[cs:tickcount]
	inc word[cs:seccount]
	mov al, 0x20
	out 0x20, al ; end of interrupt
	
	pop di
	pop es
	pop ax
	iret ; return from interrupt

kbisr:
	push ax
	in al, 0x60 ; read a char from keyboard port
	shr al, 7
	cmp al, 1 ; has a key been released
	jne exit ; leave interrupt routine
	add word[cs:wordcount], 1
	
exit: 
	mov al, 0x20
	out 0x20, al ; send EOI to PIC
	pop ax
	iret ; return from interrupt
	
start: 
	xor ax, ax
	mov es, ax ; point es to IVT base
	mov ax, [es:9*4]
	mov [oldisr], ax ; save offset of old routine
	mov ax, [es:9*4+2]
	mov [oldisr+2], ax ; save segment of old routine
	cli ; disable interrupts
	mov word [es:9*4], kbisr
	mov [es:9*4+2], cs
	mov word [es:8*4], timer; store offset at n*4
	mov [es:8*4+2], cs ; store segment at n*4+2
	sti ; enable interrupts

	mov dx, start ; end of resident portion
	add dx, 15 ; round up to next para
	mov cl, 4
	shr dx, cl ; number of paras
	
	mov ax, 0x3100 ; terminate and stay resident
	int 0x21