#include<iostream>
using namespace std;
int main()
{
	int **array;
	int n,num;
	int iterator=1;
	int count=0;
	cout<<"Enter number of rows."<<endl;
	cin>>n;
	array=new int*[n];
	for (int i=0;i<n;i++)
	{
		cout<<"Enter Number between 6 and 100: ";
		cin>>num;
		while(num<=6 && num>=100)
		{
			cout<<"Wrong input. Please re-enter the number"<<endl;
			cout<<"Enter Number between 6 and 100: ";
			cin>>num;
		}
		for (int j=1;j<=num;j++)
		{
			if (num%j==0)
			{
				count++;
			}
		}
		array[i]=new int[count+1];
		array[i][0]=num;
		for (int j=1;j<=num;j++)
		{
			if (num%j==0)
			{
				array[i][iterator]=j;
				iterator++;
			}
		}
		cout<<"Factors of "<<array[i][0]<<" are ";
		for (int j=1;j<(count+1);j++)
		{
			cout<<array[i][j];
			if(j!=count)
			{
				cout<<", ";
			}
		}
		cout<<endl;
		count=0;
		iterator=1;
	}
	system("pause");
	return 0;
}