#include<iostream>
#include"Header.h"
using namespace std;
int main()
{
	AdjList a;
	a.input();
	a.output();
	int * arr;	// to return path
	cout <<MinHamiltonianCycle(a, arr);
	system("pause");
	return 0;
}