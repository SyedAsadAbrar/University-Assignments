#include <iostream>
using namespace std;
int main()
{
	int year;
	cin>>year;
	if (year%4==0)
	{
		if (year%100!=0)
		{
			cout<<endl<<year<<" is a leap year.";
		}
		if (year%100==0)
		{
			if (year%400==0)
			{
				cout<<endl<<year<<" is a leap year.";
			}
			if (year%400!=0)
			{
				cout<<endl<<year<<" is not a leap year.";
			}
		}
	}
	else 
	{
		cout<<endl<<year<<" is not a leap year.";
	}

	system("pause");
	return 0;
}