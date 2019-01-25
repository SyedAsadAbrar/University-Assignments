;Convert the following C++ code into assembly language.
;Consider the numbers are declared in memory rather than
;taking input. For taking mod, use the code that you have
;developed in the above question and embed it as nested loop
;#include <iostream>
;using namespace std;
;
;int main() {
;    int n1, n2, hcf;
;    cout << "Enter two numbers: ";
;    cin >> n1 >> n2;
;
;    // Swapping variables n1 and n2 if n2 is greater than n1.
;    if ( n2 > n1) {   
;        int temp = n2;
;        n2 = n1;
;        n1 = temp;
;    }
;    
;    for (int i = 1; i <=  n2; ++i) {
;        if (n1 % i == 0 && n2 % i ==0) {
;            hcf = i;
;        }
;    }
;
;    cout << "HCF = " << hcf;
;    return 0;
;}

[org 0x100]

	mov cx, 0
	mov ax, [n1]
	cmp [n2], ax
	jg swap
	
back:
	mov ax, 1
	l1:
		mov bx, [n1]
		jmp modfunc
		back1:
				cmp dx, 0
				je checkn2
		back2:
				mov cx, 0
				inc ax
				cmp ax, [n2]
				jle l1
exit:
	mov ax, 0x4c00
	int 0x21
	
swap:
	mov bx, [n2]
	mov [n1], bx
	mov [n2], ax
	mov ax, 0
	mov bx, 0
	jmp back
	
modfunc:
		mov dx, 0
		modl1:					;bx=dividend, ax=divisor
			mov dx, bx
			cmp bx, ax
			jge modl2	
			cmp cx, 1			;to avoid infinite loop
			je back_check_n2
			jmp back1

		modl2:
			sub bx, ax
			jmp modl1
			
checkn2:
		mov bx, [n2]
		mov cx, 1				;to avoid infinite loop
		jmp modfunc
		back_check_n2:
					cmp dx, 0
					je hcf_assignment
					back_from_assignment:
										jmp back2
		
hcf_assignment:
			mov [hcf], ax
			jmp back_from_assignment
			
hcf: dw		0
n1: dw		2
n2: dw		2