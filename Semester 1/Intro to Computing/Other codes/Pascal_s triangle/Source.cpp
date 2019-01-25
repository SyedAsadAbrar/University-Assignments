#include<iostream>
using namespace std;
void pscltri(int size)
{
	int num=1;
	int k=1;
		for (int i=1;i<=size;i++)
	{
		for (int j=size-i;j>0;j--)
		{
			cout<<"  ";
		}
		while(k==1)
		{
			cout<<"1";
			cout<<"  ";
			k++;
		}
		for (int l=1;l<i;l=l++)
		{
			for(int m=1;m<=l;m++)
			{
				num=num*(i-m+1)/(m);
			}
			cout<<num;
			cout<<"  ";
			num=1;
		}
		k=i;
		while (k==i && i!=1)
		{
			cout<<"1";
			cout<<"  ";
			k++;
		}
		for (int j=size-i;j>0;j--)
		{
			cout<<"  ";
		}
		cout<<endl;
		k=1;
	}
}
int main()
{
	int rows=0;
	cout<<"Enter size(or number of rows) of Pascal's triangle."<<endl;
	cin>>rows;
	pscltri(rows);
	system("pause");
	return 0;
}