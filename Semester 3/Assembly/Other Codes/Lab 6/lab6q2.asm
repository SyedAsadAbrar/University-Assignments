[org 0x100]

r1:
	mov ax, [msk]
	xor [key], ax
	xor [key+2], ax
	clc
	rcl word[key], 1
	rcl word[key+2], 1
	jc carryr1
	backr1:
			mov ax, [text]
			add ax, [key]
			mov [text], ax
			jc exaddr1
			ex_addr1:
					mov ax, [text+2]
					add ax, [key+2]
					mov [text+2], ax
					jmp r2
				
	carryr1:
			or word[key], 1
			jmp backr1
			
	exaddr1:
		add word[text+2], 1
		jmp ex_addr1
			
r2:
	shl word[msk], 1
	mov ax, [msk]
	xor [key], ax
	inc ax
	xor [key+2], ax
	clc
	rcr word[key], 1
	rcr word[key+2], 1
	jc carryr2
	backr2:
			mov ax, [text]
			add ax, [key]
			mov [text], ax
			jc exaddr2
			ex_addr2:
					mov ax, [text+2]
					add ax, [key+2]
					mov [text+2], ax
					jmp r3
					
	carryr2:
		or word[key], 0x8000
		jmp backr2
		
	exaddr2:
		add word[text+2], 1
		jmp ex_addr2
					
r3:
	shl word[msk], 1
	mov ax, [msk]
	xor [key], ax
	inc ax
	inc ax
	xor [key+2], ax
	clc
	rcr word[key], 1
	rcr word[key+2], 1
	jc carryr3
	backr3:
			mov ax, [text]
			add ax, [key]
			mov [text], ax
			jc exaddr3
			ex_addr3:
					mov ax, [text+2]
					add ax, [key+2]
					mov [text+2], ax
					jmp r4
					
	carryr3:
		or word[key], 1
		jmp backr3

	exaddr3:
		add word[text+2], 1
		jmp ex_addr3
					
r4:
	shl word[msk], 1
	mov ax, [msk]
	xor [key], ax
	add ax, 5
	xor [key+2], ax
	clc
	rcr word[key], 1
	rcr word[key+2], 1
	jc carryr4
	backr4:
			mov ax, [text]
			add ax, [key]
			mov [text], ax
			jc exaddr4
			ex_addr4:
					mov ax, [text+2]
					add ax, [key+2]
					mov [text+2], ax
					jmp exit
					
	carryr4:
			or word[key], 0x8000
			jmp backr4
			
	exaddr4:
			add word[text+2], 1
			jmp ex_addr4
		
exit:
	mov ax, 0x4c00
	int 0x21
	
msk: dw 0xAAAA
text: dd 0x15D3C257
key: dd 0x23CDE689
bit_cpl: dd 0
