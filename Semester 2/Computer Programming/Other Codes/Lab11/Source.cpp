#include<iostream>
#include"bankAccount.h"
using namespace std;
int main()
{
	bankAccount a;
	bankAccount b(600);					//retrieve account num
	b.depositMoney(12312309123);
	b.printAcc;
	system("pause");
	return 0;
}