[org 0x100]
mov si, [l]
mov di, [r]
mov cx, 0
mov dx, 0
call main
jmp exit

main:
	start:
			cmp si, di
			jg exitSearch						;exit condition check
			
			sub sp, 2
			
			mov bx, si							;Calculating mid
			mov ax, di
			sub ax, si
			mov cx, 2
			div cx
			add bl, al
			mov [mid], bx
			push bx								;Pushing mid
	
			mov bl, [key]						;Pushing key
			push bx
			
			call binSearch
			pop ax
			cmp ax, -2
			je return
			mov dx, ax					
			return:
					ret
			exitSearch:
					mov word[bp+8], -1
					ret
			
	binSearch:
			push bp
			mov bp, sp
			mov bx, [bp+6]
			mov cl, [arr+bx]					;middle element
			cmp cx, [bp+4]						;comparing middle element with key
			
			je returnMid
			mov word[bp+8], -2
			jg searchLeft
			
			mov bx, [mid]						;search right subarray
			mov [l], bx					
			add byte[l], 1
			mov si, [l]
			jmp skip
			
			returnMid:
					mov cx, [mid]
					mov [bp+8], cx
					pop bp
					ret 4
					
			searchLeft:
						mov bx, [mid]
						mov [r], bx
						sub byte[r], 1
						mov di, [r]
			
			skip:
				call start
				pop bp
				ret 4

exit:
	mov ax,0x4c00
	int 0x21
	
arr: db 1,3,4,6,7,8,10
l: dw 0
r: dw 6
key: db 2
mid: db 0		;middle index