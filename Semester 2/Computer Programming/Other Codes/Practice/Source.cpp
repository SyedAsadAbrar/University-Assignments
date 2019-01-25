#include<iostream>
using namespace std;
void Input(int arr[],int size)
{
	for (int i=0;i<size;i++)
	{
		cin>>arr[i];
	}
}
int main()
{
	/*int a=2,b=7,c=11;
	int * aptr=&a, * bptr=&b, * cptr=&c;
	int x[3]={5,9,11};
	char y[6]={'H','E','L','L','O','\0'}, *sptr=NULL;=6y
	aptr=bptr;
	bptr=cptr;
	cptr=aptr;
	cout<<*bptr<<" "<<*cptr<<endl;*/
	int a[5];
	Input(a,5);
	for (int i=0;i<5;i++)
	{
		cout<<a[i]<<endl;
	}
	system("pause");
	return 0;
}