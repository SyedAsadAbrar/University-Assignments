#include<iostream>
using namespace std;
void SortAscending(int num[], int Length)
{
	int i, j, first, temp;	
	first = 0;  
	for ( i = Length  - 1; i > 0; i--) 
	{
		for ( j = 0; j < i; j++) 
		{
			if ( num[j] > num[first] )
			first = j;
		}
	temp = num[first]; 
	num[first] = num[i];
	num[i] = temp;
	}
}
int main()
{
	int nums[8] = {8,3,1,2,4,7,5,6};
	int len = 8;
	SortAscending(nums,len);
	for (int i=0;i<8;i++)
	{
		cout<<nums[i]<<" ";
	}
	system("pause");
	return 0;
}