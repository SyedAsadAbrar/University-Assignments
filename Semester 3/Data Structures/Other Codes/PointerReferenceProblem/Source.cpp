#include<iostream>
using namespace std;
void changePointer(int *p)
{
	int w = 3;
	int *q = &w;
	p = q;
}
int main()
{
	int pa = 2;
	int *p = &pa;
	cout << p<<endl;
	int w = 3;
	int *q = &w;
	p = q;
	cout << p;
	system("pause");
	return 0;
}