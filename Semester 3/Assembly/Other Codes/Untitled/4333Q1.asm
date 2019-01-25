[org 0x0100]
jmp start
string1:    db 'round and round the rugged rock the ragged rascal ran', 0
string2:    db 'r', 0

start:
            mov bx, string1
            mov cx, 0                           ; position where the string is to be printed
            push cx
            push bx
            call print

            mov cx, 0                           ; row number of string1
            push cx
            mov cx, 0                           ; col number
            push cx

            mov si, 0  
            loop2:                              ; calculation of length of string1
                    mov al, [string1 + si]
                    add si, 1
                    cmp al, 0
            jne loop2

            sub si, 1
            push si                             ; string length
            push string2                        ; offset of string2

            call trim

exit:
            mov ax, 0x4c00
            int 0x21

; BELOW IS A FUNCTION TO TRIM OCCURANCES OF STRING2 ON THE SCREEN

trim:
            push bp
            mov bp, sp
            push ax
            push bx
            push di
            push si
            push es
            push ds
            push cx

            mov ax, 0xb800
            mov es, ax                          

            mov si, word[bp + 10]               ; Row number of string1
            mov ax, 160
            mul si
            mov si, ax
            add si, word[bp + 8]                ; col number of string1

            mov cx, [bp + 6]                    ; string1 length
            mov bx, [bp + 4]                    ; string2 offset
            mov dx, 0

            calLength:
                    add dx, 1                   ; string2 length
                    add bx, 1
                    cmp byte[bx], 00
            jne calLength
            push dx
            mov ah, 0x07
            mov di, si

            loop5:
                    mov dx, word[bp-16]         ; moving string2 length in dx
                    mov bx, [bp + 4]            ; moving string2 offset in bx
                    mov ax, [es:di]             ; copying value on video memory
                    mov si, di                  ; keeping value of starting index, where searching starts
                    add di, 2                   
                    push di
                    compare:
                            cmp al, byte[bx]    ; searching if first element matches the string2's first element
                            jne outloop         ; if not, start the loop again
                            mov ax, [es:di]     
                            add bx, 1
                            add di, 2
                            sub dx, 1
                            jnz compare         ; if word is not found, keep the compare loop working until whole word is found
                            sub di, 2
                            loop6:              ; this loops trims the part where string2 is found
                                    mov ax, [es:di]            
                                    mov word[es:di], 0x0720
                                    cmp al, 0x00
                                    je addSpaces        ; this prints spaces at the end of string1 where null is found
                                    mov [es:si], ax
                                    add di, 2
                                    add si, 2
                                    ;cmp si, bx
                            jmp loop6
            outloop:
            pop di
            loop loop5


exitFunc:
            pop cx
            pop cx
            pop ds
            pop es
            pop si
            pop di
            pop bx
            pop ax
            pop bp
            ret 8

; BELOW IS A FUNCTION TO PRINT STRING ON VIDEO MEMORY

print:
            push bp
            mov bp, sp
            push ax
            push bx
            push di
            push si
            push es

            mov ax, 0xb800
            mov es, ax
            mov di, [bp + 6]
            mov bx, [bp + 4]

            mov si, 0
            mov ah, 7
            loop1:
                    mov al, byte[bx + si]
                    stosw
                    add si, 1
                    cmp al, 0
            jne loop1

            pop es
            pop si
            pop di
            pop bx
            pop ax
            pop bp
            ret 4

addSpaces:
        mov word[es:si], 0x0720
        add si, 2
        cmp si, di
        jng addSpaces
        jmp outloop