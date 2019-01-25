#include<iostream>
using namespace std;

int main()
{
	const int size=20;
	int nums[size];
	int temp=0;
	cout<<"Please enter 20 numbers in array."<<endl;
	for (int i=0;i<size;i++)
	{
		cin>>nums[i];
	}
	for (int i=0;i<(size/2);i++)
	{
		swap(nums[i],nums[(size-1)-i]);
	}
	cout<<"The reversed array is:\n";
	for (int i=0;i<size;i++)
	{
		cout<<nums[i]<<" ";
	}
	system("pause");
	return 0;
}