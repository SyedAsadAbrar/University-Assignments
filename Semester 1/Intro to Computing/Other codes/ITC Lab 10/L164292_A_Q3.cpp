#include<iostream>
using namespace std;
void input(int A[], int num)
{
	cout<<"Enter 10 numbers in array."<<endl;
	for (int i=0;i<num;i++)
	{
		cin>>A[i];
	}
	cout<<"The input array is:"<<endl;
	for (int i=0;i<num;i++)
	{
		cout<<A[i]<<" ";
	}
}
void alt_arrng(int A[], int num)
{
	int j=0;
	for (int i=0;i<num && j<=num;i++)
	{
		if (A[i]<0)
		{
			swap(A[i],A[j]);
			j++;
			if (A[j]>=0)
			{
				for (int k=j;k<i;k++)
				{
					swap(A[k],A[i]);
				}
				j++;
			}
		}
	}
	cout<<"Sorted array is:"<<endl;
	for (int i=0;i<num;i++)
	{
		cout<<A[i]<<" ";
	}
}
int main()
{
	const int size=6;
	int array[size];
	input(array,size);
	cout<<endl;
	alt_arrng(array,size);
	system("pause");
	return 0;
}