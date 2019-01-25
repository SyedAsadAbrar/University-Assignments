#pragma once
#include<iostream>
using namespace std;
void coingame(int n, bool pA, bool pB)
{
	//The function takes three parameters
	//Coin number n, bool values to show
	//turn of player, if n turns 0 for any
	//player, he wins

	//The conditions are written in a way
	//in which in every turn, the player
	//tries to choose such a number from
	//1, 2 and 4 such that the other player
	//cannot win in the successive turn. If
	//no such number is there, 1 is choosen.
	if ((n - 4 == 0) || (n - 4 > 0 && n - 4 != 2 && n - 4 != 1 && n - 4 != 4))
	{
		n = n - 4;
		//cout << "The number is " << n << endl;
	}
	else if ((n - 2 == 0) || (n - 2 > 0 && n - 2 != 1 && n - 2 != 4 && n - 2 != 2))
	{
		n = n - 2;
		//cout << "The number is " << n << endl;
	}
	else
	{
		n--;
		//cout << "The number is " << n << endl;
	}
	if (n == 0)
	{
		if (pA)
		{
			cout << "Player A is the winner." << endl;
		}
		else
		{
			cout << "Player B is the winner." << endl;
		}
		return;
	}
	coingame(n, !pA, !pB);
	return;
}