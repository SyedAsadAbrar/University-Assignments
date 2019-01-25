#include<iostream>
#include"Header.h"
using namespace std;
Set::Set()
{
	arr=nullptr;
	size=0;
}
Set::Set(const Set &obj)
{
	cout<<"Copy constructor called"<<endl;
	arr=nullptr;
	*this=obj;	//assignment operator which was created would be called
}
Set::Set(char*str,int n)	//parse, create array, copy
{
	if(n<=0)
	{
		size=0;
		arr=nullptr;
	}
	else
	{
		arr=new int[n];
		size=n; int i=0;
		char * tok=strtok(str,",");
		while(tok!=NULL)
		{
			arr[i++]=atoi(tok);
			tok=strtok(NULL,",");
		}
	}
}
Set::Set(int * ptr, int n)
{
	size=n;
	arr=new int[size];
	for (int i=0;i<n;i++)
	{
		arr[i]=ptr[i];
	}
}
Set::~Set()
{
	if(arr!=nullptr)
	{
		delete[]arr;
	}
}
bool Set::operator==(const Set & obj)
{
	if (size!=obj.size)
	{
		return false;
	}
	for (int i=0;i<size;i++)
	{
		if (arr[i]!=obj.arr[i])
		{
			return false;
		}
	}
	return true;
}
const Set & Set::operator=(const Set & obj)		//copying is expensive so we reference it and since we're not channging it so it is passed as const...
{
	if(arr!=nullptr)
	{
		delete[]arr; //deletion of old array
	}
	arr = new int[obj.size]; //creation of array and for loop to copy members
	size=obj.size;
	for (int i=0;i<size;i++)
	{
		arr[i]=obj.arr[i];
	}
	return *this;	//pointer to object
}
Set Set::operator+(const Set & obj)	//if we use this function, copy constructor is called by default as we return by copy because const and "&" are not used
{
	Set ans;


	return ans;
}
int Set::getSize()
{
	return size;
}
int & Set::operator[](int i) //"&" is written so numbers can be added or subtracted from members of set i.e. they can be changed. Without it, the copy would be changed and not the real value.
{
	return arr[i];	//the subscript operator for int* is called and not the one being created
}