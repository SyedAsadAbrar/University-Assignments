#include<iostream>
using namespace std;
int main()
{
	int nums[10];
	cout<<"Enter 10 numbers in array."<<endl;
	for (int i=0;i<10;i++)
	{
		cin>>nums[i];
	}
	cout<<"The adresses of numbers in array are:"<<endl;
	for (int i=0;i<10;i++)
	{
		cout<<&nums[i]<<endl;
	}
	cout<<"The numbers are stored consecutively in memory, each value in array is stored on 4 bytes so there is a consecutive difference of 4 bytes in memory. They are stored in hexadecimal."<<endl;
	system("pause");
	return 0;
}