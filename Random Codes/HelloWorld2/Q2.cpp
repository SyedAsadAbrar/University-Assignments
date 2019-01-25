#include <stdio.h>
#include <iostream>
#include <stdlib.h>
using namespace std;

void swap(int *xp, int *yp)
{
	int temp = *xp;
	*xp = *yp;
	*yp = temp;
}

// A function to implement bubble sort
void Sort(int arr[], int n)
{
	int i, j;
	for (i = 0; i < n - 1; i++)

		// Last i elements are already in place   
		for (j = 0; j < n - i - 1; j++)
			if (arr[j] > arr[j + 1])
				swap(&arr[j], &arr[j + 1]);
}

// Driver program to test above functions
int main(int argv, char * arg[])
{
	int * arr = new int[argv - 1];
	for (int i = 0; i < argv - 1; i++)
	{
		arr[i] = atoi(arg[i + 1]);
	}
	Sort(arr, argv - 1);
	for (int i = 0; i < argv - 1; i++)
	{
		cout << arr[i] << endl;
	}
	//cout<<argv;
	return 0;
}