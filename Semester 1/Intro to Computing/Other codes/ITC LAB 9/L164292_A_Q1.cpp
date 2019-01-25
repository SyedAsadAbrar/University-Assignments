#include<iostream>
using namespace std;

int main()
{
	int num[15];
	int oddcount=0;
	int evencount=0;
	int negcount=0;
	int poscount=0;
	cout<<"Please enter 15 numbers in array"<<endl;
	for (int i=0;i<15;i++)
	{
		cin>>num[i];
	}
	for (int i=0;i<15;i++)
	{
		if (num[i]%2==0)
			evencount++;
		else if (num[i]%2!=0)
			oddcount++;
	}
	for (int i=0;i<15;i++)
	{
		if (num[i]<0)
			negcount++;
		else if (num[i]>0)
			poscount++;
	}
	cout<<"The number of even numbers in array is "<<evencount<<endl;
	cout<<"The number of odd numbers in array is "<<oddcount<<endl;
	cout<<"The number of negative numbers in array is "<<negcount<<endl;
	cout<<"The number of positive numbers in array is "<<poscount<<endl;
	system("pause");
	return 0;
}