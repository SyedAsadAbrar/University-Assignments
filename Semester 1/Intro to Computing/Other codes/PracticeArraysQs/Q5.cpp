#include<iostream>
using namespace std;
void OnesInArray(int array[], int size)
{
	for (int i=0;i<size;i++)
	{
		if (array[i]==1)
		{
			cout<<"*";
		}
		else 
		{
			cout<<" ";
		}
	}
	cout<<endl;
}
int main()
{
	int a[9]={1,2,1,4,1,7,6,1,1};
	OnesInArray(a,9);
	system("pause");
	return 0;
}