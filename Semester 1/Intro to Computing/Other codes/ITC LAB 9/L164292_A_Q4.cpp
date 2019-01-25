#include<iostream>
using namespace std;
int main()
{
	const int size=10;
	int nums[size];
	int largest=0;
	int largest_index=0;
	cout<<"Enter 10 numbers in array."<<endl;
	for (int i=0;i<10;i++)
	{
		cin>>nums[i];
	}
	largest=nums[0];
	largest_index=0;
	for (int i=10;i>1;i--)
	{
		largest=nums[0];
		largest_index=0;
		for (int j=0;j<i;j++)
		{
			if (largest<nums[j])
			{
				largest=nums[j];
				largest_index=j;
			}
		}
		swap(nums[largest_index],nums[i-1]);
	}
	cout<<"The array in ascending order is:"<<endl;
	for (int i=0;i<10;i++)
	{
		cout<<nums[i]<<endl;
	}
	system("pause");
	return 0;
}