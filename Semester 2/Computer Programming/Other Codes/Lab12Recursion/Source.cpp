#include<iostream>
#include<stdio.h>
#include<string.h>
using namespace std;
//listNumbers in ascending order
void AscendingList(int start, int end)
{
	if(start>end)
	{
		return;
	}
	else
	{
		cout<<start<<endl;
		AscendingList(start+1,end);
	}
}

//listNumbers in descending order
void DescendingList(int start, int end)
{
	if(start>end)
	{
		return;
	}
	else
	{
		cout<<end<<endl;
		DescendingList(start,end-1);
	}
}

int min(int *a, int first, int last) 
{
	if (first==last)
	{
		return a[first];
	}
	else if(a[first]>a[last])
	{
		return min(a,first+1,last);
	}
	else if(a[first]<a[last])
	{
		return min(a,first,last-1);
	}
}
int mul(int a, int b)
{
	if (b==1)
	{
		return a;
	}
	else
	{
		return a + mul(a,b-1);
	}
}
int cmp_proc(int *a, int key,  int center)

{
	if (a[center]==key)
	{
		return 0;
	}
	if (a[center]<center)
	{
		return 1;
	}
	if (a[center]>center)
	{
		return 2;
	}
}
int binarySearch(int * a, int left, int right, int key)
{
	if (left==right)
	{
		return NULL;
	}
	else
	{
		int mid=(right+left)/2;
		if(cmp_proc(a, key, mid) == 0)
		{
			return mid;
		}
		else if(cmp_proc(a,key,mid) == 1)
		{
			return binarySearch(a,(mid+1),right,key);
		}
		else if(cmp_proc(a,key,mid) == 2)
		{
			return binarySearch(a,left, mid-1,key);
		}
	}
}
void swap(char * first, char * second)
{
	char temp;
	temp=*first;
	*first=*second;
	*second=temp;
}
void permute(char *a, int start, int end)
{
	int count=start;
	if (start==end)
		cout<<a<<endl;
	else
	{
		while(count<=end)
		{
			swap((a+start), (a+count));
			permute(a, start+1, end);
			swap((a+start), (a+count));
			count++;
		}
		count=start;
	}
}
int main()
{
	int arr[5]={1,9,10,2,6};
	//DescendingList(1,10);
	cout<<min(arr,0,4)<<endl;
	int mult=mul(5,4);
	char array[]="123";
	int size=strlen(array);
	permute(array,0,size-1);
	system("pause");
	return 0;
}
