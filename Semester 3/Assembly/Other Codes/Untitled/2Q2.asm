[org 0x0100]

mov ax, 0x21DB
mov cx, 0
mov dx, 0

mov bl, al
shl bl, 7
shr bl, 6
mov dl, al
shl dl, 6
shr dl, 7
or bl, dl	
or cl, bl	;First pair swapped

mov bl, al
shl bl, 5
shr bl, 7
shl bl, 3
mov dl, al
shl dl, 4
shr dl, 7
shl dl, 2
or bl, dl
or cl, bl	;Second pair swapped

mov bl, al
shl bl, 3
shr bl, 7
shl bl, 5
mov dl, al
shl dl, 2
shr dl, 7
shl dl, 4
or bl, dl
or cl, bl 	;Third pair swapped

mov bl, al
shl bl, 1
shr bl, 7
shl bl, 7
mov dl, al
shr dl, 7
shl dl, 6
or bl, dl
or cl, bl 	;Fourth pair swapped

mov bh, ah
shl bh, 7
shr bh, 6
mov dh, ah
shl dh, 6
shr dh, 7
or bh, dh	
or ch, bh	;First pair swapped

mov bh, ah
shl bh, 5
shr bh, 7
shl bh, 3
mov dh, ah
shl dh, 4
shr dh, 7
shl dh, 2
or bh, dh
or ch, bh	;Second pair swapped

mov bh, ah
shl bh, 3
shr bh, 7
shl bh, 5
mov dh, ah
shl dh, 2
shr dh, 7
shl dh, 4
or bh, dh
or ch, bh 	;Third pair swapped

mov bh, ah
shl bh, 1
shr bh, 7
shr bh, 7
mov dh, ah
shr dh, 7
shl dh, 6
or bh, dh
or ch, bh 	;Fourth pair swapped

mov ax, cx

exit:
	mov ax, 0x4c00
	INT 21h