#include<iostream>
using namespace std;
void OutputArray(int array[],int size)
{
	cout<<"The array passed to function is:"<<endl;
	for (int i=0;i<size;i++)
	{
		cout<<array[i]<<endl;
	}
}
int main()
{
	int a[5]={1,2,3,4,5};
	OutputArray(a,5);
	system("pause");
	return 0;
}