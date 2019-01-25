#include<iostream>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <windows.h>
#include "Header.h"
using namespace std;
int main()
{
	int num;
	int m;
	node * temp;
	srand(time(NULL));
	cout << "------------------------Pass the Bucket------------------------" << endl;
	cout << "Enter number of players." << endl;
	cin >> num;
	while (num <= 0)
	{
		cout << "Wrong number entered. Please re-enter." << endl;
		cin >> num;
	}
	CirList a(num);
	temp = a.getHead();
	Sleep(1000);
	system("cls");
	cout << "------------------------Pass the Bucket------------------------" << endl;
	for (int i = 0; i < (num-1); i++)
	{
		a.printList();
		Sleep(5000);
		m = rand() % 10 + 1;
		srand(time(NULL));
		//cout << m<<endl;
		temp = a.removeNode(temp, m);
		system("cls");
		cout << "------------------------Pass the Bucket------------------------" << endl;
	}

	cout << "The winner is:" << endl;
	a.printList();
	system("pause");
	return 0;
}