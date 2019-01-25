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
			while (index < size*size)
			{
				if (arr[index].r == i && arr[index].c == j)
				{
					cout << "Q" << "\t";
					break;
				}
				index++;
			}
			if (index == size*size)
			{
				cout << ".\t";
				index = 0;
			}
			j++;
		}
		j = 0;
		cout << endl;
	}
}

void nQueens(coor a[], int & solindex, int n, int & row, int & col)
{
	coor test;
	test.r = row;
	test.c = col;
	if (solindex == n)
	{
		display(a, n);
		solindex = 0;
		for (int i = 0; i < n; i++)
		{
			a[i].r = -1;
			a[i].c = -1;
		}
		row++;
		col = 0;
	}
	while(col<n)
	{
		if (!attQueen(a, n, test))
		{
			a[solindex++] = test;
			col++;
			row = 0;
			test.c = col;
		}
		else
		{
			while (row == (n-1))
			{
				col = a[--solindex].c;
				row = a[solindex].r;
				test.c = -1;
				test.r = -1;
				a[solindex] = test;
			}
			test.c = -1;
			test.r = -1;
			row++;
			
			//a[solindex--] = test;
		}
		nQueens(a, solindex, n, row, col);
	}


	/*
	//integer variable to count column of first queen
	int firstqcount = 0;

	//integer iterators for row and column
	int row = 0, col = 0;

	//for indexing of solution array
	int solindex = 0;

	//for ease in printing
	int solnum = 0;

	//bool variable to avoid printing twice
	bool print = false;

	//Memory allocation for constraints array
	coor * sol = new coor[n*n];

	//Initialization of constraints array
	//-1 shows a place yet to be filled
	for (int i = 0; i < n*n; i++)
	{
		sol[i].r = -1;
		sol[i].c = -1;
	}

	//Positioning Queens code
	while (firstqcount < n)
	{
		//first queen insertion
		temp.r = row++;
		temp.c = firstqcount;
		sol[solindex] = temp;
		a.push(sol[solindex++]);
		while (!a.isEmpty())
		{
			//so it doesn't go out of bound
			while (col < n)
			{
				temp.r = row;
				temp.c = col++;

				//if tested position isn't attacked by
				//an existing queen in array/stack
				//push onto stack
				if (!attQueen(sol, n, temp))
				{
					sol[solindex] = temp;
					a.push(sol[solindex++]);
					row++;
					col = 0;
					break;
				}
			}
			if (solindex == n && print == false)
			{
				solnum++;
				if (solnum == 1)
				{
					cout << "Solutions for NQueens problem for n = " << n << " are:" << endl;
				}
				cout << "Solution no. " << solnum << endl;
				display(sol, n);
				cout << endl;
				print = true;
			}
			if (col == n)
			{
				a.pop(popped);
				row--;
				col = popped.c + 1;
				solindex--;
				temp.r = -1;
				temp.c = -1;
				sol[solindex] = temp;
				print = false;
			}
		}
		if (a.isEmpty())
		{
			firstqcount++;
			col = 0;
		}
	}

	//for example when n=2 or n=3
	if (solnum == 0)
	{
		cout << "No solution found to NQueens problem for n = " << n << endl;
	}

	//deallocating memory
	delete[]sol;
	*/
}
