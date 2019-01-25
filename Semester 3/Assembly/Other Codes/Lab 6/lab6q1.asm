[org 0x100]

start:
	mov cx, [i]
	mov[temp_i], cx
	mov cx, 0

main:
	add word[pwr], 1
	mov cx, [pwr]
	mov [temp_pwr], cx
	jmp power
	back:
		div word[pwr]
		add[series_sum], ax
		sub word[temp_i], 1
		cmp word[temp_i], 0
		jg main

exit:
	mov ax, 0x4c00
	int 0x21

power:
	mov ax, 1
	iloop:
		cmp word[temp_pwr], 0
		je back
		mul word[r]
		sub word[temp_pwr], 1
		jmp iloop
	
r: dw 2
i: dw 8
temp_i: dw 0
pwr: dw 0
temp_pwr: dw 0
series_sum: dw 0