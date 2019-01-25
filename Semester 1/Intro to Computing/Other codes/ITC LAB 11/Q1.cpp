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
void RemoveDuplicates(int array[10])
{
	int reduced[10];
	int value=0;
	int count=-1;
	bool duplicate=false;
	for (int i=0;i<10;i++)
	{
		for (int j=i;j<10;j++)
		{
			if (array[i]==array[j])
			{
				count++;
			}
		}
		if (count>=0)
		{
			for (int k=0;k<10;k++)
			{
				if (array[i]==reduced[k])
				{
					duplicate=true;
				}
			}
			if (duplicate==false)
			{
				reduced[value]=array[i];
				value++;
			}
		}
		count=-1;
		duplicate=false;
	}
	cout<<"The original array is:"<<endl;
	for (int i=0;i<10;i++)
	{
		cout<<array[i]<<" ";
	}
	cout<<endl<<endl<<"The reduced array after removing multiple instances of numbers is:"<<endl;
	for (int i=0;i<value;i++)
	{
		cout<<reduced[i]<<" ";
	}
	cout<<endl;
}

int main()
{
	int original[10];
	InputArray(original);
	RemoveDuplicates(original);
	system("pause");
	return 0;
}