#include<iostream>
using namespace std;
int main()
{
	int first;
	int second;
	int third;
	int fourth;
	int fifth;
	int sum;
	float average;
	int min;
	int max;
	cout<<"Enter 5 separate numbers."<<endl;
	cin>>first;
	cin>>second;
	cin>>third;
	cin>>fourth;
	cin>>fifth;
	sum = first + second + third + fourth + fifth;
	average=sum/5;
	cout<<"The average of the 5 numbers is "<<average<<endl;
	if(first>=max)
	{
		max=first;
		if(second>=max)
		{
			max=second;
		}
		if(third>=max)
		{
			max=third;
		}
		if(fourth>=max)
		{
			max=fourth;
		}
		if(fifth>=max)
		{
			max=fifth;
		}
	}
	if(first<=min)
	{
		min=first;
		if(second<=min)
		{
			min=second;
		}
		if(third<=min)
		{
			min=third;
		}
		if(fourth<=min)
		{
			min=fourth;
		}
		if(fifth<=min)
		{
			min=fifth;
		}
	}
	system("pause");
	return 0;
}