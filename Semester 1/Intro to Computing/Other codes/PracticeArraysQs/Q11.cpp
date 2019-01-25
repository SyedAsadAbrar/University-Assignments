#include<iostream>
using namespace std;
void RemoveMultiples(int array[], int size, int num)
{
	for (int i=0;i<size;i++)
	{
		if (array[i]%num==0)
		{
			for (int j=i;j<size-1;j++)
			{
				swap(array[j],array[j+1]);
			}
			size--;
			i--;
		}
	}
	for (int i=0;i<size;i++)
	{
		cout<<array[i]<<endl;
	}
	cout<<"The size of updated array is "<<size<<endl;
}

int main()
{
	int size=12;
	int num=3;
	int a[12]={1,23,56,5,4,9,11,15,36,20,14,102};
	RemoveMultiples(a,12,3);
	system("pause");
	return 0;
}