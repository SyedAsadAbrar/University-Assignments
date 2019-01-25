#include<iostream>
using namespace std;
void SortArray(int array[],int size)
{
	for (int i=0;i<size;i++)
	{
		for (int j=i;j<size;j++)
		{
			if (array[i]>array[j])
			{
				swap(array[i],array[j]);
			}
		}
	}
	for (int i=0;i<size;i++)
	{
		cout<<array[i]<<", ";
	}
	cout<<endl;
}
int main()
{
	int a[5]={4,2,9,1000,3};
	SortArray(a,5);
	system("pause");
	return 0;
}