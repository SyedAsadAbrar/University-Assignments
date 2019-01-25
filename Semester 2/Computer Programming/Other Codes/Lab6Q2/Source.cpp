#include<iostream>
#include"bag.h"
using namespace std;
int main()
{
	Bag b1;
	//Call setName and check for name is set or not?

	b1.show();
	Bag b2;
	b2.add(3);
	b2.add(5);
	b2.add(8);
	b2.show();
	b2.add(4);
	b2.add(5);
	b2.add(7);
	b2.add(9);
	b2.show();
	cout<<"Element at index 3:"<<b2.elementAt(3)<<endl;
	cout<<"Position of Element 5:"<<b2.indexOf(5)<<endl;
	cout<<"Position of Element 5:"<<b2.indexOf(5,b2.indexOf(5)+1);
	cout<<"\nPosition of Element 1:"<<b2.indexOf(1)<<endl;
	b2.remove(5,b2.indexOf(5)+1);
	b2.show();
	b2.remove(5);
	b2.show();
	int i;
	int *el=b2.elements(2,4);
	for (i=0;i<2;i++)
	{
		cout<<el[i]<<" ";
	}
	cout<<endl;
	cout<<"Is B1 equal to B2:";
	if (b1.isEqual(b2))
	{
		cout<<"Yes"<<endl;
	}
	else
	{
		cout<<"No"<<endl;
	}
	Bag b3;
	cout<<"Is B2 equal to B3:";
	if (b3.isEqual(b2))
	{
		cout<<"Yes"<<endl;
	}
	else
	{
		cout<<"No"<<endl;
	}
	return 0;
}