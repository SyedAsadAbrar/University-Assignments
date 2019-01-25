#include<iostream>;
using namespace std;
int main()
{
	float totalspent;
	int no_books;
	float average;
	cout<<"Write total amount spent books."<<endl;
	cin>>totalspent;
	cout<<"Write total no. of books bought."<<endl;
	cin>>no_books;
	average=totalspent/no_books;
	cout<<"The average cost per book is "<<average<<endl;
	system("pause");
	return 0;
}