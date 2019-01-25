#include<iostream>
#include<conio.h>
using namespace std;

int main()
{
int i=0;
float firstnum=1;
float secondnum=1;
char input=0;
cout<<"*************************************MY CALCULATOR********************************";
cout<<"\n\t1.\tAdd\n\t2.\tDivide\n\t3.\tMagnitude\n\t4.\tPower\n\t5.\tExit\n";
while(input!= '5')
{
	input = _getch();
	if(input == '1')
	{
		cout<<"You selected ADD"<<endl;
		cout<<"Please enter first number:"<<endl;
		cin>>firstnum;
		cout<<"Please enter second number:"<<endl;
		cin>>secondnum;
		cout<<"The result is: "<<(firstnum+secondnum);
		cout<<endl<<"Press any key to return to menu.......";
		getch();
		system("cls");
		cout<<"*************************************MY CALCULATOR********************************";
		cout<<"\n\t1.\tAdd\n\t2.\tDivide\n\t3.\tMagnitude\n\t4.\tPower\n\t5.\tExit\n";
	}
	if(input == '2')
	{
		cout<<"You selected DIVIDE"<<endl;
		cout<<"Please enter first number:"<<endl;
		cin>>firstnum;
		cout<<"Please enter second number:"<<endl;
		cin>>secondnum;
		cout<<"The result is: "<<(firstnum/secondnum);
		cout<<endl<<"Press any key to return to menu.......";
		getch();
		system("cls");
		cout<<"*************************************MY CALCULATOR********************************";
		cout<<"\n\t1.\tAdd\n\t2.\tDivide\n\t3.\tMagnitude\n\t4.\tPower\n\t5.\tExit\n";
	}
	if(input == '3')
	{
		cout<<"You selected MAGNITUDE"<<endl;
		cout<<"Please enter number:"<<endl;
		cin>>firstnum;
		if(firstnum<0)
		{
			firstnum=-firstnum;
		}
		else if(firstnum>0)
		{
			firstnum=firstnum;
		}
		cout<<"The result is: "<<firstnum;
		cout<<endl<<"Press any key to return to menu.......";
		getch();
		system("cls");
		cout<<"*************************************MY CALCULATOR********************************";
		cout<<"\n\t1.\tAdd\n\t2.\tDivide\n\t3.\tMagnitude\n\t4.\tPower\n\t5.\tExit\n";
	}
	if(input == '4')
	{
		cout<<"You selected POWER"<<endl;
		cout<<"Please enter exponent:"<<endl;
		cin>>firstnum;
		if(firstnum<0)
		{
			while(i>firstnum)
			{
				secondnum=secondnum/2;
				i--;
			}
		}
		if(firstnum>0)
		{
			while(i<firstnum)
			{
				secondnum=secondnum*2;
				i++;
			}
		}
		cout<<"The result is: "<<secondnum;
		cout<<endl<<"Press any key to return to menu.......";
		getch();
		system("cls");
		cout<<"*************************************MY CALCULATOR********************************";
		cout<<"\n\t1.\tAdd\n\t2.\tDivide\n\t3.\tMagnitude\n\t4.\tPower\n\t5.\tExit\n";
	}
}
system("pause");
return 0;
}