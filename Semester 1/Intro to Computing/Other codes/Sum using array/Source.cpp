#include<iostream>
using namespace std;
int main()
{
	const int size=10;
	int num [size];
	int sum=0;
	int i=0; 
	int j=0;
	cout<<"Enter 10 numbers and press enter after every entry."<<endl;
	while (i<size)
	{
		cin>>num[i];
		i++;
	}
	i=0;
	cout<<"The numbers input are ";
	while (i<size)
	{
		cout<<num[i];
		cout<<", ";
		i++;
	}
	i=0;
	while(j<size)
	{
		sum=sum+num[i];
		i++;
		j++;
	}
	cout<<endl<<"The sum of the entered numbers is "<<sum<<endl;
	system("pause");
	return 0;
}