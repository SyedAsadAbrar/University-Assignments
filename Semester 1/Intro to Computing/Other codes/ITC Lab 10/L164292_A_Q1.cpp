#include<iostream>
using namespace std;

void inputarray(int array[], int size)
{
	cout<<"Enter 10 numbers of array."<<endl;
	for (int i=0;i<size;i++)
	{
		cin>>array[i];
	}
}
void sortascndng(int A[], int size)
{
	int largestindex;
	int largest;
	int size1=0;
	for (int size1=size; size1>1; size1--)
	{
		largestindex=0;
		largest=A[0];
		for (int j=0;j<size1;j++)
		{
			if (largest<A[j])
			{
				largest=A[j];
				largestindex=j;
			}
		}
		swap(A[largestindex],A[size1-1]);
	}
	cout<<"The sorted array is"<<endl;
	for (int i=0;i<size;i++)
	{
		cout<<A[i]<<" ";
	}
}
void mergearrays(int A[], int B[], int C[], int size1, int size2, int size3)
{
	int i=0, j=0, k=0;
	while (i<size1 && j<size2)
	{
		if (A[i]<B[j])
		{
			C[k]=A[i];
			i++;
			k++;
		}
		else
		{
			C[k]=B[j];
			j++;
			k++;
		}
	}
	if (i<size1)
	{
		while (i<size1)
		{
			C[k]=A[i];
			k++;
			i++;
		}
	}
	else
	{
		while(j<size2)
		{
			C[k]=B[j];
			j++;
			k++;
		}
	}
	cout<<"The merged array is"<<endl;
	for (int i=0;i<size3;i++)
	{
		cout<<C[i]<<" ";
	}
}

int main()
{
	const int a = 10;
	int array1[a], array2[a], array3[a+a];
	inputarray(array1,a);
	sortascndng(array1,a);
	cout<<endl;
	inputarray(array2,a);
	sortascndng(array2,a);
	cout<<endl;
	mergearrays(array1, array2, array3, a, a, (a+a));
	system("pause");
	return 0;
}