;; A PROGRAM TO ADD TWO NUMBERS 

;; USING REGISTERS 

[ORG 0x0100]	
	
OR AX,1
AND BX,0
OR BX,AX

XOR AX,AX

OR AX,1
AND AX,0

OR AX,1
OR AX,0XFFFF

NOT AX

INT 0x21	
