#include<iostream>
using namespace std;

void input(int A[],int size, int&num)
{
	cout<<"Enter 10 numbers in array."<<endl;
	for(int i=0;i<size;i++)
	{
		cin>>A[i];
	}
	cout<<"Enter number for comparison."<<endl;
	cin>>num;
}
void check_sum(int A[],int size, int num)
{
	int sum;
	for (int i=0; i<size;i++)
	{
		for (int j=0; j<size; j++)
		{
			sum=i+j;
			if (sum==num)
			{
				cout<<i<<" "<<j<<"| ";
			}
		}
	}
}
int main()
{
	const int num=10;
	int a;
	int array[num];
	input(array,num,a);
	check_sum(array,num,a);
	system("pause");
	return 0;
}