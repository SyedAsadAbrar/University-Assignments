#include<iostream>
using namespace std;
template<typename T>
class Pair
{
	T a[2];
public:
	Pair(T param1, T param2);

};
template<typename T, typename U>
T GetMax(T first, U second)
{
	if (first>second)
		return first;
	else if (second>first)
		return second;
}
template<typename T, typename U>
T GetMin(T first, U second)
{
	if (first<second)
		return first;
	else if (second<first)
		return second;
}
int main () 
{
	int i=5, j=6, k;
	long l=10, m=5, n;
	k=GetMax<int>(i,j);
	n=GetMin<long>(l,m);
	cout<< k <<endl;
	cout<< n <<endl;

	char o='Z';
	int  p=6, q;
	long r=10, s=5, t;
	q=GetMax<int,long>(p,r);
	t=GetMin<int,char>(s,o);
	cout<< q <<endl;
	cout<< t <<endl;

	return 0;
}