#include <iostream>
using namespace std;

int main()
{
	int org[6] = { 1,1,2,2,2,8 };
	int test[6];
	for (int i = 0; i < 6; i++)
	{
		cin >> test[i];
	}
	for (int i = 0; i < 6; i++)
	{
		test[i] = org[i] - test[i];
	}
	for (int i = 0; i < 6; i++)
	{
		cout << test[i] << " ";
	}
    return 0;
}

