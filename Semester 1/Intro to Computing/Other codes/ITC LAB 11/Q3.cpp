#include<iostream>
#include<conio.h>
#include <stdio.h>
using namespace std;
void InputArray(int array[10])
{
	cout<<"Enter 10 numbers in array."<<endl;
	for (int i=0;i<10;i++)
	{
		cin>>array[i];
	}
	getch();
	system("cls");
}
void SelectHigher(int array1[10],int array2[10],int array3[10])
{
	cout<<"The first array is:"<<endl;
	for (int i=0;i<10;i++)
	{
		cout<<array1[i]<<" ";
	}
	cout<<endl<<"The second array is:"<<endl;
	for (int i=0;i<10;i++)
	{
		cout<<array2[i]<<" ";
	}
	cout<<endl<<"The resultant array with the biggest elements of both arrays is;"<<endl;
	for (int i=0;i<10;i++)
	{
		if (array1[i]<array2[i])
		{
			array3[i]=array2[i];
		}
		else
			array3[i]=array1[i];
	}
	for (int i=0;i<10;i++)
	{
		cout<<array3[i]<<" ";
	}
	cout<<endl;
}
int main()
{
	int FirstArray[10], SecondArray[10], Result[10];
	InputArray(FirstArray);
	InputArray(SecondArray);
	SelectHigher(FirstArray,SecondArray,Result);
	system("pause");
	return 0;
}