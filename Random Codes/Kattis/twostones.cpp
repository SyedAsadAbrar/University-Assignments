#include<iostream>
using namespace std;
int main()
{
	int stone;
	cin >> stone;

	while (stone > 1)
	{
		stone -= 2;
	}
	if (stone == 1)
	{
		cout << "Alice" << endl;
	}
	else
		cout << "Bob" << endl;
	system("pause");
	return 0;
}

