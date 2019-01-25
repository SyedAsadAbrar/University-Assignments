#include<iostream>
#include <time.h>  //to use time function
using namespace std;
int random(int &m,int &n)
{
	srand (time(NULL)); //rand function requires an initial value to generate random numbers, in a way which makes it generate psuedo-random and not truly random since it is based on mathematical calculations. This initial value is called seed and if same seed is used, rand function will return the same value each time. To randomize seed, srand is used and is given the value of current time using time function(time(NULL)).
	int randomnum = rand() % (m-n+1) + m;
	return randomnum;
}
int main()
{
	int firstnum=0;
	int secondnum=0;	
	cout<<"Enter first number of range."<<endl;
	cin>>firstnum;
	cout<<"Enter last number of range."<<endl;
	cin>>secondnum;
	int randomnum=random(firstnum,secondnum);
	cout<<"The random number is "<<randomnum<<endl;
	system("pause");
	return 0;
}