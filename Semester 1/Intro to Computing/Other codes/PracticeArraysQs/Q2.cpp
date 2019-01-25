#include<iostream>
using namespace std;
void MatchingIndexes(int array1[],int array2[],int size)
{
	for (int i=0;i<size;i++)		//Assuming both arrays to be of the same size. A check function can also be implemented to check if they're of different sizes.
	{
		cout<<array1[i]<<"\t"<<array2[i]<<endl;
	}
}
int main()
{
	int a1[5]={1,2,3,4,6};
	int a2[5]={1,2,3,4,5};
	cout<<"Array1\tArray2"<<endl;
	MatchingIndexes(a1,a2,5);
	system("pause");
	return 0;
}