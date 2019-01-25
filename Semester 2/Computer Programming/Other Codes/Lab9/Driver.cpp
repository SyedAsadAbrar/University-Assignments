#include<iostream>
#include<ostream>
#include<istream>
#include"myClass.h"
using namespace std;
int main()
{
	int arr1[5] = {1,2,3,4,5};
	int arr2[5] = {6,7,8,9,10};
	myClass a(arr1);
	myClass b(arr2);
	myClass c = a + b;
	/*cout<<c;*/
	system("pause");
	return 0;
}