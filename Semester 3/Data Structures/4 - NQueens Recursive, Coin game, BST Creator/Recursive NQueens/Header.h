#include<iostream>
using namespace std;

struct coor
{
	int r;
	int c;
};

//helper function
//Checks if the tested row-column combination is threatened by another Queen sharing the same diagonal or column
bool attQueen(coor arr[], int size, coor test)
{
	//Underlying rule
	//If any specific position contains 1, and it shares
	//the same row, column or diagonal, it will attack
	//a queen placed on the position which is to be tested

	//mDiag for testing main diagonal(L to R)
	//sDiag for testing secondary diagonal(R to L)
	int mDiag = 0, sDiag = 0;

	for (int i = 0; i < size; i++)
	{
		//MAIN DIAGONAL TEST
		//for two queens to share the same main diagonal, the
		//difference of their respective rows and columns
		//should be equal
		mDiag = arr[i].r - arr[i].c;
		if (test.r - test.c == mDiag)
		{
			if (arr[i].r != -1 && arr[i].c != -1)
			{
				return true;
			}
		}

		//SECONDARY DIAGONAL TEST
		//for two queens to share the same secondary diagonal, the
		//sum of their respective rows and columns should be equal
		sDiag = arr[i].r + arr[i].c;
		if (test.r + test.c == sDiag)
		{
			if (arr[i].r != -1 && arr[i].c != -1)
			{
				return true;
			}
		}

		//SAME COLUMN TEST
		//for two queens to share the same column, the
		//their y coordinates would be equal
		if (arr[i].c == test.c)
		{
			if (arr[i].r != -1 && arr[i].c != -1)
			{
				return true;
			}
		}

		//SAME ROW TEST
		//for two queens to share the same column, the
		//their x coordinates would be equal
		if (arr[i].r == test.r)
		{
			if (arr[i].r != -1 && arr[i].c != -1)
			{
				return true;
			}
		}
	}
	//if all tests are passed that is no such queen exists
	//which can attack a queen in the tested position
	return false;
}

//Display function
void display(coor arr[], int size)
{
	int j = 0;
	int index = 0;
	for (int i = 0; i < size; i++)
	{
		while (j < size)
		{
			if (arr[index].r == i && arr[index].c == j)
			{
				cout << "Q" << "\t";
				index++;
			}
			else
			{
				cout << ".\t";
			}
			j++;
		}
		j = 0;
		cout << endl;
	}
}
void nQueensHelper(coor a[], int & solindex, int n, int & row, int & col, int & solnum)
{
	coor test;

	//First condition is for when it backtracks
	//after printing all possible combinations
	//the index of solution array becomes
	//negative, the second is to avoid column 
	//going out of bounds
	while (solindex>=0 && col<n)
	{
		test.r = row;
		test.c = col;

		//If tested position is valid
		if (!attQueen(a, n, test))
		{
			//Put in constraints/solutions array
			a[solindex++] = test;

			//Column is given a value of zero
			//to start checking again for the next row
			col = 0;
			test.c = -1;
			test.r = -1;
			row++;

			//if constraints/solutions array is filled
			//print solution
			if (solindex == n)
			{
				solnum++;
				if (solnum == 1)
				{
					cout << endl << "Solutions for NQueens problem for n = " << n << " are:" << endl;
				}
				cout << "Solution no. " << solnum << endl;
				display(a, n);
				cout << endl;

				//This is done so each and every solution
				//is checked
				col = a[solindex - 1].c + 1;
			}
			else
			{
				///recursive call if solutions array
				//is not filled completely
				nQueensHelper(a, solindex, n, row, col, solnum);
			}
		}
		//if column becomes equal to size of
		//board or greater, the last entry
		//of solutions array was wrongly placed
		//so it is reset
		if (col >= (n - 1) && solindex != 0)
		{
			col = a[--solindex].c;
			row = a[solindex].r;
			test.c = -1;
			test.r = -1;
			a[solindex] = test;
		}
		//checking next column
		col++;
	}
}
void nQueens(int n)
{
	//Memory allocation for constraints array
	coor * sol = new coor[n];
	for (int i = 0; i < n; i++)
	{
		sol[i].r = -1;
		sol[i].c = -1;
	}
	int row = 0;
	int col = 0;
	int solindex = 0;
	int solnum = 0;
	nQueensHelper(sol, solindex, n, row, col, solnum);
	if (solnum == 0)
	{
		cout << "No solutions found for n = " << n << endl;
	}

	//deallocation
	delete[]sol;
}
