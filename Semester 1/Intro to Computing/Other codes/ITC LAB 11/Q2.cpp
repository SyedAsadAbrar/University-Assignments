#include<iostream>
using namespace std;
void MatchingIndexes(int Array1[],int Array2[])
{
	cout<<"Elements of both arrays with matching index values having same numerical value are:"<<endl;
	for (int i=0;i<10;i++)
	{
		if (Array1[i]==Array2[i])
		{
			cout<<"~ "<<i<<endl;
		}
	}
}
int main()
{
	int FirstArray[10]={1,2,3,5,7,9,11,13,15,17};
	int SecondArray[10]={7,9,3,4,6,8,11,13,18,19};
	MatchingIndexes(FirstArray,SecondArray);
	system("pause");
}