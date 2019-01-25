#include<iostream>
#include<vector>
using namespace std;
int main()
{
	int div1;
	int div2;
	int mod1;
	int mod2;
	int first;
	int second;
	vector <int> firstnum;
	vector <int> secondnum;

	//initial input
	cin >> first;
	cin >> second;
	div1 = first;
	div2 = second;

	//storing in array
	while (div1 != 0)
	{
		mod1 = div1 % 10;
		div1 = div1 / 10;
		firstnum.emplace(firstnum.begin(), mod1);
	}
	while (div2 != 0)
	{
		mod2 = div2 % 10;
		div2 = div2 / 10;
		secondnum.emplace(secondnum.begin(), mod2);
	}



	system("pause");
	return 0;
}