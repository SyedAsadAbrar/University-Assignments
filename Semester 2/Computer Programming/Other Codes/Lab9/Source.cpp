#include<iostream>
#include<ostream>
#include"myClass.h"
using namespace std;
myClass::myClass()
{
	arr = nullptr;
	currentSize = 0;
}
myClass::myClass(int* arr1)
{
	currentSize = 5;
	(*this).arr = new int[currentSize];
}
myClass::myClass(const myClass & obj)
{
	arr = nullptr;
	*this = obj;
}
myClass::~myClass()
{
	if (arr != nullptr)
	{
		delete[]arr;
	}
}
myClass myClass::operator+(const myClass & obj)
{
	myClass a;
	a.currentSize = currentSize + obj.currentSize;
	a.arr = new int[a.currentSize];
	for (int i = 0; i < currentSize; i++)
	{
		a.arr[i] = arr[i];
	}
	for (int i = currentSize; i < a.currentSize; i++)
	{
		a.arr[i] = arr[i];
	}
	return a;
}
myClass myClass::operator-(const myClass & obj)
{
	myClass a;
	int size=0;
	bool temp=false;
	a.currentSize = currentSize - obj.currentSize;
	a.arr = new int[a.currentSize];
	for (int i = 0; i < currentSize; i++)
	{
		for (int j = 0; j < obj.currentSize; j++)
		{
			if (arr[i] == obj.arr[j])
			{
				temp=true;
			}
		}
		if(temp!=true)
		{
			a.extendBy1();
			a.arr[size]=arr[i];
			size++;
			temp=false;
		}
	}
	return a;
}
const myClass & myClass::operator=(const myClass & obj)
{
	if(arr!=nullptr)
	{
		delete[]arr; //deletion of old array
	}
	arr = new int[obj.currentSize]; //creation of array and for loop to copy members
	currentSize=obj.currentSize;
	for (int i=0;i<currentSize;i++)
	{
		arr[i]=obj.arr[i];
	}
	return *this;	//pointer to object
}
myClass & myClass::operator+(int num)
{
	myClass a;
	a.arr=new int[currentSize+1];
	for (int i=0;i<currentSize;i++)
	{
		a.arr[i]=arr[i];
	}
	delete []arr;
	a.arr[currentSize]=num;
	*this=a;
	return *this;
}
myClass & myClass::operator-(int num)
{
	myClass a;
	int count=0;
	for (int i=0;i<currentSize;i++)
	{
		if (arr[i]==num)
		{
			count++;
		}
	}
	a.arr=new int[currentSize-count];
	int iterator=0;
	for (int i=0;i<currentSize;i++)
	{
		if (arr[i]==num)
		{
			a.arr[iterator]=arr[i];
			iterator++;
		}
	}
	delete []arr;
	*this=a;
	return *this;
}
bool myClass::operator==(const myClass & obj)
{
	if (currentSize!=obj.currentSize)
	{
		return false;
	}
	for (int i=0;i<currentSize;i++)
	{
		if (arr[i]!=obj.arr[i])
		{
			return false;
		}
	}
	return true;
}
myClass & myClass::operator-()
{
	for (int i=0;i<currentSize;i++)
	{
		arr[i]=-arr[i];
	}
	return *this;
}
int myClass::getData(int num)
{
	return arr[num];
}
void myClass::extendBy1()
{
	myClass temp;
	temp = new int[currentSize + 1];
	for (int i = 0; i < currentSize; i++)
	{
		temp.arr[i] = arr[i];
	}
	*this=temp;
}
int myClass::getSize()
{
	return currentSize;
}
ostream &  operator<<(ostream & output,const myClass & obj)
{
	output<<"Array size: "<<obj.getSize;
	output<<"Array Elements:"<<endl;
	for (int i=0;i<obj.getSize;i++)
	{
		output<<obj.getData[i];
		if((i-obj.getSize)>1)
		{
			output<<",";
		}
	}
	return output;
}
istream & operator>>(istream & input,myClass & obj)
{
	//assuming myClass obj exists but isn't initialized
	int size;
	cout<<"Enter size of myClass object."<<endl;
	cin>>size;
	obj.arr=new int[size];
	cout<<"Enter "<<obj.getSize<<" elements."<<endl;
	for (int i=0;i<obj.getSize;i++)
	{
		input>>obj.arr[i];
	}
	return input;
}

