#include<conio.h>
#include<iostream>
using namespace std;
int main()
{
int newline=1;
int letters=0;
int spaces=0;
int digits=0;
int fstops=0;
int commas=0;
char input;
cout<<"Enter text."<<endl;
input = _getche();
while(input != '-') // while the input is not minus
{
	if (input == 13)
	{
		cout<<endl;
		newline++;
	}
	if ((input>='A' && input<='Z') || (input>='a' && input<='z'))
	{
		letters++;
	}
	if (input == 32)
	{
		spaces++;
	}
	if (input>='0' && input<='9')
	{
		digits++;
	}
	if (input=='.')
	{
		fstops++;
	}
	if (input==',')
	{
		commas++;
	}
	input = _getche();
}
cout<<endl;
cout<<"Number of spaces: "<<spaces<<endl;
cout<<"Letters: "<<letters<<endl;
cout<<"Digits: "<<digits<<endl;
cout<<"Full stops: "<<fstops<<endl;
cout<<"Commas: "<<commas<<endl;
cout<<"Lines: "<<newline<<endl;
system("pause");
return 0;
}