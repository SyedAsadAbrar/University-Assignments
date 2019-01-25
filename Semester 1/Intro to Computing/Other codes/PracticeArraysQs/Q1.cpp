#include<iostream>
using namespace std;
bool IsIdenticalArrays(int arrayA[],int arrayB[],int size)
{
	for (int i=0;i<size;i++)		//Assuming both arrays to be of the same size. A check function can also be implemented to check if they're of different sizes.
	{
		if (arrayA[i]!=arrayB[i])
		{
			return false;
		}
	}
	return true;
}
int main()
{
	int a1[5]={1,2,3,4,6};
	int a2[5]={1,2,3,4,5};
	IsIdenticalArrays(a1,a2,5);
	if (IsIdenticalArrays(a1,a2,5) == 1)
	{
		cout<<"Arrays are equal"<<endl;
	}
	else
	{
		cout<<"Arrays are not equal"<<endl;
	}
	system("pause");
	return 0;
}