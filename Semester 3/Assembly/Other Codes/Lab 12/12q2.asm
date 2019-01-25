; The assumption is made that the program requires an escape key to start
[org 0x0100]
jmp start

oldkb: dd 0

string1: db '*'
string2: db ' '
prevrow: db 0xC
prevcol: db 0x28
nextrow: db 0xC
nextcol: db 0x28
movement: dw 0

seconds: dw 0

print:
	push bp
	mov bp, sp
	push es
	push ax
	push bx
	push cx
	push dx
	mov ah, 0x13 ; service 13 - print string
	mov al, 0 ; subservice 01 – update cursor
	mov bh, 0 ; output on page 0
	mov bl, 7 ; normal attrib
	mov dx, [bp+6] ; row and column
	mov cx, 1 ; length of string
	push cs
	pop es ; segment of string
	mov bp, [bp+4] ; offset of string
	int 0x10 ; call BIOS video service
	pop dx
	pop cx
	pop bx
	pop ax
	pop es
	pop bp
	ret 4

;;;;; COPY LINES 007-047 FROM EXAMPLE 9.7 (printnum) ;;;;;
; keyboard interrupt service routine
kbisr: 
	push ax
	push bx
	
	mov bh, [cs:prevrow] 
	mov bl, [cs:prevcol]
	mov [cs:nextrow], bh
	mov [cs:nextcol], bl
	
	in al, 0x60 ; read char from keyboard port
	mov word[cs:movement], 1
	
	cmp al, 0x1E ; a pressed
	jne nextcmp ; no, try next comparison
	mov bh, [cs:prevrow]
	mov bl, [cs:prevcol]
	push bx
	push string2
	call print
	cmp bl, 0
	je exit
	sub word [cs:prevcol], 0x01	
	jmp exit ; leave the ISR

	nextcmp: 
		cmp al, 0x20 ; d pressed
		jne nextcmp2 ; no, try next comparison
		mov bh, [cs:prevrow]
		mov bl, [cs:prevcol]
		push bx
		push string2
		call print
		cmp bl, 0x4F
		je exit
		add word [cs:prevcol], 0x01	
		jmp exit ; leave the ISR
		
	nextcmp2: 
		cmp al, 0x11 ; w pressed
		jne nextcmp3 ; no, try next comparison
		mov bh, [cs:prevrow]
		mov bl, [cs:prevcol]
		push bx
		push string2
		call print
		cmp bh, 0
		je exit
		sub word [cs:prevrow], 0x01
		jmp exit ; leave the ISR

	nextcmp3: 
		cmp al, 0x1F ; s pressed
		jne exit ; no, try next comparison
		mov bh, [cs:prevrow]
		mov bl, [cs:prevcol]
		push bx
		push string2
		call print
		cmp bh, 0x18
		je exit
		add word [cs:prevrow], 0x01	
		jmp exit ; leave the ISR	
		
	exit: 
		;mov al, 0x20
		;out 0x20, al ; send EOI to PIC

		pop bx
		pop ax
		jmp far [cs:oldkb] ; call original ISR
		;iret ; return from interrupt

; timer interrupt service routine
timer: 
	push ax
	push bx
	
	cmp word[cs:movement], 0
	je printing
	inc word [cs:seconds] ; increment tick count
	cmp word [cs:seconds], 18 ; is the printing flag set
	jne skipall ; no, leave the ISR
	
	printing:
	
	mov bh, [cs:prevrow]
	mov bl, [cs:prevcol]
	push bx
	push string1
	call print
	mov word[cs:movement], 0
	mov word[cs:seconds], 0
	
	skipall: 
		mov al, 0x20
		out 0x20, al ; send EOI to PIC
		
		pop bx
		pop ax
		iret ; return from interrupt
		
start:
	l1:
		mov ah, 0
		int 0x16
		
		cmp al, 27
		jne l1
	
	xor ax, ax
	mov es, ax ; point es to IVT base
	mov ax, [es:9*4]
	mov [oldkb], ax ; save offset of old routine
	mov ax, [es:9*4+2]
	mov [oldkb+2], ax ; save segment of old routine
	cli ; disable interrupts
	mov word [es:9*4], kbisr ; store offset at n*4
	mov [es:9*4+2], cs ; store segment at n*4+2
	mov word [es:8*4], timer ; store offset at n*4
	mov [es:8*4+2], cs ; store segment at n*4+
	sti ; enable interrupts
		
	mov dx, start ; end of resident portion
	add dx, 15 ; round up to next para
	mov cl, 4
	shr dx, cl ; number of paras
	;mov ax, 0x4c00 ; normal exit
	mov ax, 0x3100 ; terminate and stay resident
	int 0x21