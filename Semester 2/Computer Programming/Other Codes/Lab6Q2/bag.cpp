#include<iostream>
#include<stdlib.h>
#include"bag.h"
using namespace std;
Bag::Bag()
{
	int arr[9];
	size=9;
	cSize=0;
	for (int i=0;i<9;i++)
	{
		el[i]=-1;
	}
	bagName[0]=NULL;
}
void Bag::setName(char* name)
{
	strcpy(bagName,name);
}
char* Bag::getBagName()
{
	if(bagName!=NULL)
	{
		return bagName;
	}
}
void Bag::show()
{
	for (int i=0;i<9;i++)
	{
		cout<<el[i]<<" ";
	}
}
void Bag::add(int no)
{
	if (cSize<size)
	{
		el[cSize]=no;
		cSize++;
	}
}
int Bag::indexOf(int no)
{
	int index=-1;
	for (int i=0;i<size;i++)
	{
		if (el[i]==no)
		{
			index=i;
		}
	}
	return index;
}
int Bag::indexOf(int no, int index)
{
	int index=-1;
	for (int i=index;i<size;i++)
	{
		if(el[i]==no)
		{
			index=i;
		}
	}
	return index;
}
void Bag::remove(int no)
{
	for (int i=0;i<size;i++)
	{
		if (el[i]==no)
		{
			for (int j=i;j<size-1;j++)
			{
				el[j]=el[j+1];
			}
		}
		el[size-1]=-1;
	}
	cSize--;
}
void Bag::remove(int index,int no)
{
	for (int i=index;i<size;i++)
	{
		if (el[i]==no)
		{
			for (int j=i;j<size-1;j++)
			{
				el[j]=el[j+1];
			}
		}
		el[size-1]=-1;
	}
	cSize--;
}
int Bag::elementAt(int index)
{
	if(index<cSize)
	{
		return el[index];
	}
	return -1;
}
int* Bag::elements(int start, int end)
{
	if(start>=end)
	{
		return NULL;
	}
	if(end>cSize)
	{
		end=cSize;
		int*arr=new int[end-start];
		for (int i=start;i<end;i++)
		{
			arr[i]=el[i];
		}
		return arr;
	}
}
bool Bag::isEqual(Bag b)
{
	if (cSize!=b.cSize)
	{
		return false;
	}
	else
	{
		for (int i=0;i<size;i++)
		{
			if(el[i]!=b.el[i])
			{
				return false;
			}
		}
	}
	return true;
}