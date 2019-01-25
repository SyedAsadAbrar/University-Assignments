#include<iostream>
using namespace std;
int main()
{
	int nums[10];
	int oddindexsum=0;
	int evenindexsum=0;
	cout<<"Enter 10 numbers in array."<<endl;
	for (int i=0;i<10;i++)
	{
		cin>>nums[i];
	}
	for (int i=0;i<10;i++)
	{
		if (i%2==0)
		{
			evenindexsum=nums[i]+evenindexsum;
		}
		else if (i%2!=0)
		{
			oddindexsum=nums[i]+oddindexsum;
		}
	}
	cout<<"The sum of numbers at even indexes is "<<evenindexsum<<endl;
	cout<<"The sum of numbers at odd indexes is "<<oddindexsum<<endl;
	system("pause");
	return 0;
}