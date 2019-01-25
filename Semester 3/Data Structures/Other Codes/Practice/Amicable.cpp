#include<iostream>
using namespace std;
int amicable_pair(int n1, int n2)
{
	int sum1=0;
	int sum2=0;
	int indicator=0;
	for (int i=1;i<=(n1/2);i++)
	{
		if (n1%i==0)
		{
			sum1=sum1+i;
		}
	}
	for (int i=1;i<=(n2/2);i++)
	{
		if (n2%i==0)
		{
			sum2=sum2+i;
		}
	}
	if (sum1==n2 && sum2==n1)
	{
		indicator=1;
	}
	return indicator;
}
int main()
{
	cout<<amicable_pair(285,220);
	system("pause");
	return 0;
}