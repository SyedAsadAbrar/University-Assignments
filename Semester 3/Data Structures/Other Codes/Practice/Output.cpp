#include<iostream>
using namespace std;
bool check(int&number)
{
	int count=0;  
	bool answer=false;
	for(int a=1;a<=number;a++)
	{
		if(number%a==0)
		{
			count++;
		}
	}
	if(count==2)
	{
		answer=true;
	}
	else
	{
		answer=false;
	}
	number++;
	return answer;
}
void printSomething(int number)
{
	for(int i=number; i>=1; i--)
	{
		for (int k= number;k>i; k--)
		{
			cout<<"O"; 
		}
		int j = 1;
		while(j<=i)
		{
			if(check(j))	
				cout<<"A";
			else
				cout<<"H";
		}
		cout<<endl;
	}
}

int main()
{
	int size=5;
	printSomething(size);
	system("pause");
	return 0;
}
