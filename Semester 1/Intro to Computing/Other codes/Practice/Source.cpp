#include<iostream>
using namespace std;
int main()
{
	int const size = 10;
	int ordercorrector=0;
	int swapper=0;
	int array[size] = { 1 , 2 , 3 , -4 , -5 , -6 , 7 , 8 , 9 , -10};
	for (int i = 0 ; i < size ; i++)
	{
		for (int j = 0 ; j < size ; j++)
			{
				if (array[i] < 0)
				{
					swap(array[j], array[i]);
					ordercorrector=i;
					for (int k = j + 1 ; k <i; k++)
					{
						swap(array[k], array[swapper]);
					}
				}
			}
	}
	for (int i = 0 ; i < size ; i++)
	{
		cout<<array[i]<<endl;
	}
	system("pause");
	return 0;
}