#include<iostream>
using namespace std;
#include"Header.h"
int main()
{
	int n;
	cout << "SUBSET GENERATOR" << endl;
	cout << endl << "Enter n." << endl;
	cin >> n;
	cout << endl;
	allSubset(n);
	system("pause");
	return 0;
}