#include<iostream>
using namespace std;
int main()
{
	const int size=50;
	int num[size];
	int repeat=0;
	int counter=0;
	cout<<"Please enter "<<size<<" numbers."<<endl;
	for (int i=0;i<size;i++)
	{
		cin>>num[i];
	}
	cout<<"The numbers entered are:"<<endl;
	for (int i=0;i<size;i++)
	{
		cout<<num[i]<<" ";
	}
	cout<<endl<<"Frequency of numbers is given below."<<endl;
	for (int i=0;i<size;i++)
	{
		for (int j=0;j<size;j++)
		{
			if (num[i]==num[j])
			{
			counter=counter+1;
			}
		}
		if (i!=0)
		{
			for (int k=0;k<=i-1;k++)
			{
				if (num[k]==num[i])
				{
					repeat=1;
				}

			}
		}
		if (repeat==0)
		{
			cout<<num [i]<<" appears - ";
			cout<<counter<<" times."<<endl;
		}
		counter=0;
		repeat=0;
	}
	system("pause");
	return 0;
}