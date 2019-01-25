#include <iostream>
#include <math.h> 
using namespace std;
int binarySearch(int arr[], int n, int x)
{
	int l, r, m;
	l = 0;
	r = n - 1;
	int count = 0;
	double log = log2(n);
	while (l <= r)
	{
		count++;
		m = (l+r) / 2;

		// Check if x is present at mid
		if (arr[m] == x)
			return m;

		if (arr[m] > x)
		{
			r = m - 1;
		}

		// If x greater, ignore left half  
		if (arr[m] < x)
			l = m + 1;

		// If x is smaller, ignore right half 
	}
	cout << "While condition was executed " << ++count << " times when n = " << n << "." << endl;
	cout << "Time complexity of n = " << n << " is " << log + 1.0 << "." << endl;
	// if we reach here, then element was not present
	return -1;
}
int main()
{
	const int size = 24;
	int arr[size];
	for (int i = 0; i < size; i++)
	{
		arr[i] = i + 1;
	}
	binarySearch(arr, size, 0);
	system("pause");
	return 0;
}