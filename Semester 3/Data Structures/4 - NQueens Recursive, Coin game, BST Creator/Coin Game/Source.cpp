#pragma once
#include<iostream>
#include"Header.h"
using namespace std;
int main()
{
	int n;
	cout << "Coin game" << endl;
	cout << "Please enter initial number of coins." << endl;
	cin >> n;
	while (n < 1)
	{
		cout << "Incorrect number entered. Please re-enter number greater than 0." << endl;
		cin >> n;
	}
	coingame(n, true, false);
	system("pause");
	return 0;
}