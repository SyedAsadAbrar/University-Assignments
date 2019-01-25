;Write a program to store this date in one register in following format
;Day/month/year
[org 0x0100]

mov ax, [Day]
sal ax, 4
or ax, [Month]
sal ax, 7
or ax, [Year]


exit:
	mov ax, 0x4c00
	INT 21h

Month: dw 7
Day: dw 20
Year: dw 88 ; year can be from 0 to 99 i.e., 2017 will be mentioned as 17
