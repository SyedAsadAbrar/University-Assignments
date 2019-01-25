#include<iostream>
using namespace std;

int main()
{
	int a;
	int b;
	int c;
	int sum=0;
	cout<<"Please enter first number."<<endl;
	cin>>a;
	cout<<"Please enter second number."<<endl;
	cin>>b;
	cout<<"Please enter third number."<<endl;
	cin>>c;
	if (a!=b && a!=c && b!=c)
	{
		sum=a+b+c;
	}
	if (a==b && a!=c && b!=c)
	{
		sum=c;
	}
	if (a!=b && a==c && b!=c)
	{
		sum=b;
	}
	if (a!=b && a!=c && b==c)
	{
		sum=a;
	}
	cout<<"The sum of the three numbers is "<<sum<<endl;
	system("pause");
	return 0;
}