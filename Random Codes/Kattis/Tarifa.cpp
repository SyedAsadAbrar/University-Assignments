#include<iostream>
using namespace std;
int main()
{
	int X;
	int N;
	int * arr;

	int res;

	cin >> X;
	cin >> N;
	arr = new int[N];
	for (int i = 0; i < N; i++)
	{
		cin >> arr[i];
	}
	res = X * N;
	for (int i = 0; i < N; i++)
	{
		res -= arr[i];
	}
	res += X;
	cout << res;
	system("pause");	
    return 0;
}

