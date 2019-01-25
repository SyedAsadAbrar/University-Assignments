#include<iostream>
using namespace std;

template<class T>
class MyStack
{
	T * s;						//Pointer to stack array
	int size;					//Size of stack
	int stkptr;					//First index to place object
public:
	MyStack(int n = 20)			//Making a stack of size n, if n is not specified make stack of size 20
	{
		size = n;
		s = new T[n];
		stkptr = 0;
	}
	bool isEmpty()
	{
		if (stkptr == 0)
			return true;
		else
			return false;
	}
	bool isFull()
	{
		if (stkptr == size)
			return true;
		else
			return false;
	}
	bool push(T d)				//push data element d in the stack
	{
		if (!isFull())
		{
			s[stkptr] = d;
			stkptr++;
			return true;
		}
		else
			return false;
	}
	bool pop(T &d)				//Pop the element from stack and put it in d
	{
		if (!isEmpty())
		{
			stkptr--;
			d = s[stkptr];
			return true;
		}
		else
			return false;
	}
	bool top(T & d)				//Get the top of the stack element and place it in d
	{
		if (!isEmpty())
		{
			d = s[stkptr - 1];
			return true;
		}
		else
			return false;
	}
	~MyStack()
	{
		size = 0;
		stkptr = 0;
		delete[]s;
	}
};

struct xy
{
	int r;
	int c;
};

//helper function
//Checks if the tested row-column combination is threatened by another Queen sharing the same diagonal or column
bool attQueen(xy arr[], int size, xy test)
{
	//Underlying rule
	//If any specific position contains 1, and it shares
	//the same row, column or diagonal, it will attack
	//a queen placed on the position which is to be tested

	//mDiag for testing main diagonal(L to R)
	//sDiag for testing secondary diagonal(R to L)
	int mDiag = 0, sDiag = 0;

	
	for (int i = 0; i < size*size; i++)
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
void display(xy arr[], int size)
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

void nQueens(int n)
{
	MyStack<xy> a(n);
	xy temp;
	xy popped;
	
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
	xy * sol = new xy[n*n];

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
}