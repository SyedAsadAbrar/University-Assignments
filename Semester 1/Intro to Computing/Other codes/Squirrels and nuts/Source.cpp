#include<iostream>
using namespace std;
int main()
{
	int nuts;
	char weekend;
	cout<<"Input the number of nuts consumed at party."<<endl;
	cin>>nuts;
	cout<<"Input Y for weekend and N for weekdays."<<endl;
	cin>>weekend;
	if (weekend=='Y')
	{
		if (nuts>=40)
		{
			cout<<"True"<<endl;
		}
		else
		{
			cout<<"False"<<endl;
		}
	}
	if (weekend=='N')
	{
		if (nuts>=40 && nuts<=60)
		{
			cout<<"True"<<endl;
		}
		else
		{
			cout<<"False"<<endl;
		}
	}
	system("pause");
	return 0;
}