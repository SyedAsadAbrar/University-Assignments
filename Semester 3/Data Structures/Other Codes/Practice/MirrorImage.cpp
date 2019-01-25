#include<iostream>
using namespace std;
void MirrorImage(int a[7][9], int b[7][9])
{
	for (int i=0;i<7;i++)
	{
		for(int j=0;j<9;j++)
		{
			cout<<a[i][j]<<" ";
		}
		cout<<endl;
	}
	cout<<endl;
	for (int i=0;i<7;i++)
	{
		for(int j=8;j>=0;j--)
		{
			b[i][j]=a[i][j];
			cout<<b[i][j]<<" ";
		}
		cout<<endl;
	}
}
int main()
{
	int original[7][9]={{0,0,1,1,1,1,1,0,0},{0,0,1,0,0,0,0,0,0},{0,0,1,0,0,0,0,0,0},{0,0,1,1,1,1,1,0,0},{0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,1,0,0},{0,0,1,1,1,1,1,0,0}};
	int mirror[7][9];
	MirrorImage(original, mirror);
	system("pause");
	return 0;
}