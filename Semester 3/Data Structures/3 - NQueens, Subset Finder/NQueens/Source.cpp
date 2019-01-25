#include"Header.h"
#include<iostream>
using namespace std;
int main()
{
	int n;
	cout << "NQueens Problem Solution Generator" << endl;
	cout << endl << "Enter n." << endl;
	cin >> n;
	while (n <= 0)
	{
		cout << "Invalid number entered. Please enter number greater or equal to 1." << endl;
		cin >> n;
	}
	nQueens(n);
	system("pause");
	return 0;
}