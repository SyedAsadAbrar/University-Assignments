#include<iostream>
#include<string.h>
#include<conio.h>
using namespace std;
void printboard(int board[3][3], int size)
{
	for (int i = 0; i < size; i++)
	{
		for (int j = 0; j < size; j++)
		{
			if (board[i][j] == 0)
			{
				cout << "_ ";
			}
			else if (board[i][j] == 1)
			{
				cout << "X ";
			}
			else
			{
				cout << "O ";
			}
		}
		cout << endl;
	}
}
bool checkwinner(int board[][3], int r, int &who)
{
	for (int i = 0; i < 3; i++)
	{
		if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][0] == board[i][2])
		{
			who = board[i][0];
			return true;
		}
	}
	for (int j = 0; j < 3; j++)
	{
		if (board[0][j] != 0 && board[0][j] == board[1][j] && board[0][j] == board[2][j])
		{
			who = board[0][j];
			return true;
		}
	}
	if (board[0][0] != 0 && board[0][0] == board[1][1] && board[0][0] == board[2][2])
	{
		who = board[0][0];
		return true;
	}
	if (board[2][0] != 0 && board[2][0] == board[1][1] && board[2][0] == board[0][2])
	{
		who = board[2][0];
		return true;
	}
	return false;
}
void findplace(int board[3][3], int size, int ownsymbol, int oppsymbol, int &row, int &col)
{
	int who;
	bool winner;
	for (int i = 0; i < 3; i++)
	{
		for (int j = 0; j < 3; j++)
		{
			if (board[i][j] == 0)
			{
				row = i;
				col = j;
				board[i][j] = oppsymbol;
				winner = checkwinner(board, 3, who);
				board[i][j] = 0;
				if (winner)
					return;
			}
		}
	}
	if (board[1][1] == 0)
	{
		row = 1;
		col = 1;
	}
	else if (board[0][0] == 0)
	{
		row = 0;
		col = 0;
	}
	else if (board[0][2] == 0)
	{
		row = 0;
		col = 2;
	}
}
int main()
{
	int board[3][3] = { 0 };
	int i = 0, j = 0, k = 0,player=1;
	int cross = 1, not = 2, row=0,col=0;
	bool winner = false;
	int who;
	printboard(board, 3);
	getch;
	cout << endl;
	for (; i < 9; i++)
	{
		if (player == 1)
		{
			findplace(board, 3, 1, 2, row, col);
			//cin >> row;
			//cin >> col;
			//while (board[row][col] != 0)
			//{
			//	cout << endl << "Please re-enter"<<endl;
			//	cin >> row;
			//	cin >> col;
			//}
			board[row][col] = 1;
			player = 2;
		}
		else
		{
			cin >> row;
			cin >> col;
			while (board[row][col] != 0)
			{
				cout << endl << "Re enter pls"<<endl;
				cin >> row;
				cin >> col;
			}
			board[row][col] = 2;
			player = 1;
		}
		printboard(board, 3);
		cout << endl;
		winner = checkwinner(board, 3, who);
		system("cls");
		printboard(board, 3);
		if (winner == true)
		{
			cout << "The winner is player"<<who<<endl;
			i = 100;
		}	
	}
	return 0;
}