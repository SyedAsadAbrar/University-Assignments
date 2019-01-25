#include<iostream>
#include<conio.h>
using namespace std;
int main()
{
cout<<"*************************MY CALCULATOR**********************************\n";
int Add,Divide,Magnitude,Power,Exit,response,num1,num2,x,result,power=1,i=1;
cout<<"1. Add"<<endl<<"2. Divide"<<endl<<"3. Magnitude"<<endl<<"4. Power"<<endl<<"5. Exit"<<endl;

cin>>response;
while(response!=5)
{
	if(response==1)
	{
		cout<<"You selected ADD\n";
		cout<<"Enter your first number  ";
		cin>>num1;
		cout<<"Enter your second number ";
		cin>>num2;
		Add=num1+num2;
		cout<<"Your addition of two numbers is\t"<<Add<<endl;
		cout<<"Press any key to return to menu....";
		system("pause");
		system("cls");
		cout<<"*************************MY CALCULATOR**********************************\n";
		cout<<"1. Add"<<endl<<"2. Divide"<<endl<<"3. Magnitude"<<endl<<"4. Power"<<endl<<"5. Exit"<<endl;
		cin>>response;
		
		

	}
	else if(response==2)
	{
		cout<<"You selected DIVISION\n ";
		cout<<"enter your first number  ";
		cin>>num1;
		cout<<"enter your second number ";
		cin>>num2;
		Divide=num1/num2;
		cout<<"your division of two numbers is "<<Divide<<endl ;
		cout<<"Press any key to return to menu....";
		system("pause");
		system("cls");
		cout<<"*************************MY CALCULATOR**********************************\n";
		cout<<"1. Add"<<endl<<"2. Divide"<<endl<<"3. Magnitude"<<endl<<"4. Power"<<endl<<"5. Exit"<<endl;
		cin>>response;
		

	}
	else if(response==3)
	{
		cout<<"You selected MAGNITUDE\n";
		cout<<"Enter number ";
		cin>>num1;
		if(num1<0)
		{
			num1=-(num1);
		cout<<"Magnitude of the number is "<<num1<<endl;
		cout<<"Press any key to return to menu....";
		system("pause");
		system("cls");
		cout<<"*************************MY CALCULATOR**********************************\n";
		cout<<"1. Add"<<endl<<"2. Divide"<<endl<<"3. Magnitude"<<endl<<"4. Power"<<endl<<"5. Exit"<<endl;
		cin>>response;
		
		}
		
	else if(num1>0)
	{
			cout<<"Magnitude of number is "<<num1<<endl;
		
		cout<<"Press any key to return to menu....";
		system("pause");
		system("cls");
		cout<<"*************************MY CALCULATOR**********************************\n";
		cout<<"1. Add"<<endl<<"2. Divide"<<endl<<"3. Magnitude"<<endl<<"4. Power"<<endl<<"5. Exit"<<endl;
		cin>>response;
		
		}

	}
else if(response==4)
	{
		cout<<"You selected POWER\n";
		cout<<"Enter power ";
		cin>>x;
		while(i<=x)
		{
		result=power*2;
		i=i+1;
		}
		cout<<"Power is "<<result<<endl;
		cout<<"Press any key to return to menu....";
		system("pause");
		system("cls");
		cout<<"*************************MY CALCULATOR**********************************\n";
		cout<<"1. Add"<<endl<<"2. Divide"<<endl<<"3. Magnitude"<<endl<<"4. Power"<<endl<<"5. Exit"<<endl;
		cin>>response;
	}
else 
	{
		cout<<"Invalid key entered.";
		cout<<"Press any key to return to menu....";
		system("pause");
		system("cls");
		cout<<"*************************MY CALCULATOR**********************************\n";
		cout<<"1. Add"<<endl<<"2. Divide"<<endl<<"3. Magnitude"<<endl<<"4. Power"<<endl<<"5. Exit"<<endl;
		cin>>response;
	}
}

system("pause");
return 0;
}