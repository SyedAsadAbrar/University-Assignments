#include<iostream>
#include<fstream>
#include<cmath>
#include<vector>
using namespace std;
double stock(int p, int a, int b, int c, int d, int k)
{
	return p*(sin(a*k+b)+cos(c*k+d)+2);
}
double ComputeMaxDec(int p, int a, int b, int c, int d, int n)
{
	
}
int main()
{
	char buff[500];
	int p,a,b,c,d,n;
	char * token;
	ifstream input;
	input.open("testdata.txt");
	vector <int> stock_prices;
	input.getline(buff,500);
	token=strtok(buff," ");
	p=atoi(token);
	token=strtok(NULL," ");
	a=atoi(token);
	token=strtok(NULL," ");
	b=atoi(token);
	token=strtok(NULL," ");
	c=atoi(token);
	token=strtok(NULL," ");
	d=atoi(token);
	token=strtok(NULL," ");
	n=atoi(token);
	for (int k=1;k<=n;k++)
	{
		stock_prices.push_back(stock(p,a,b,c,d,k));
	}
	system("pause");
	return 0;
}