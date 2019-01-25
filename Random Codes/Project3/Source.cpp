#include <iostream>

#include <sstream>
#include <cstdlib>
#include <ctime>
#include <string>
#include <stdlib.h>
using namespace std;
char displayArray[5][5], mineArray[5][5], playAgain;
int numMines, xPosNum, yPosNum, count = 0;
string mines, xPos, yPos;
void assignArray();
void mineCount();
void showArray(char anArray[5][5]);
void playGame();
void adjacentCheck();
int checkGame();
int main()
{
	//Main program running
	//023
	do
		//024
	{
		//025
		system("cls");
		//026

		//027
		//User inputs the number of mines he wants
		//028
		do
			//029
		{
			cout << "Please enter a valid number of mines (min = 5, max = 10): ";

			cin >> mines;

			stringstream(mines) >> numMines;

		} while (numMines < 5 || numMines > 10);


		//Code to clear the screen

		system("cls");


		assignArray();

		showArray(displayArray);

		playGame();




		cin >> playAgain;

	} while (playAgain == 'Y' || playAgain == 'y');



	return 0;

}



//Function to randomly assign mines and symbols to the arrays

void assignArray()

{

	srand((unsigned int)time(0));


	//054
	//Assigning symbols to the arrays
	//055
	for (int row = 0; row < 5; row++) {
		//056
		for (int col = 0; col < 5; col++) {
			//057
			displayArray[row][col] = '*';
			//058
			mineArray[row][col] = '0';
			//059
		}
		//060
	}
	//061

	//2
	//Randomly assigning mines
	//3
	for (int i = 0; i < numMines; i++) {
		//064
		int row = (rand() % 4);
		//065
		int col = (rand() % 4);
		//066
		while (mineArray[row][col] == '@') {
			//067
			row = (rand() % 4);
			//068
			col = (rand() % 4);
			//069
		}
		//070
		mineArray[row][col] = '@';
		//071
	}
	//2

	showArray(mineArray);
	//073
	//Counting the mines for adjacent tiles
	//074
	mineCount();
	//075
}
//076

//077
//Function to count mines for surrounding tiles
//078
void mineCount()
//079
{
	//080
	for (int row = 0; row < 5; row++) {
		//081
		for (int col = 0; col < 5; col++) {
			//082
			if (mineArray[row][col] == '@') {
				//083
				for (int x = row - 1; x <= row + 1; x++) {
					//084
					for (int y = col - 1; y <= col + 1; y++) {
						//085
						if (x >= 0 && x <= 4) {
							//086
							if (y >= 0 && y <= 4) {
								//087
								if (mineArray[x][y] != '@') {
									//088
									mineArray[x][y] += 1;
									//089
								}
								//090
							}
							//091
						}
						//092
					}
					//093
				}
				//094
			}
			//095
		}
		//096
	}
	//097
}
//098

//099
//Function for displaying arrays
//100
void showArray(char anArray[5][5])
//101
{
	//102
	for (int row = 0; row < 5; row++) {
		//103
		for (int col = 0; col < 5; col++) {
			//104
			cout << anArray[row][col] << " ";
			//105
		}
		//106
		cout << endl;
		//107
	}
	//108
}
//109

//110
//Function for playing the game
//111
void playGame()
//112
{
	int count = 0;
	//113
	//Code for selecting a valid tile
	//114
	for (int i = 0; i <= 25; i++) {
		//115

		//116
		do
			//117
		{
			//118
			cout << "Please enter a valid row position number (1 - 5): ";
			//119
			cin >> xPos;
			//120
			stringstream(xPos) >> xPosNum;
			//121
		} while (xPosNum < 1 || xPosNum > 5);
		//122

		//123
		do
			//124
		{
			//125
			cout << "Please enter a valid column position number (1 - 5): ";
			//126
			cin >> yPos;
			//127
			stringstream(yPos) >> yPosNum;
			//128
		} while (yPosNum < 1 || yPosNum > 5);
		//129

		//130
		//Code for checking if the tile has already been chosen
		//131
		while (displayArray[xPosNum - 1][yPosNum - 1] == mineArray[xPosNum - 1][yPosNum - 1]) {
			//132
			cout << "That position has already been revealed." << endl << endl;
			//133

			//134
			cout << "Please enter another row position number: ";
			//135
			cin >> xPos;
			//136
			stringstream(xPos) >> xPosNum;
			//137

			//138
			cout << "Please enter another column position number: ";
			//139
			cin >> yPos;
			//140
			stringstream(yPos) >> yPosNum;
			//141
		}
		//142

		//143
		//Code for checking for a loss or victory
		//144
		if (mineArray[xPosNum - 1][yPosNum - 1] == '@') {
			//145
			cout << "\nOpps! You stepped on a mine!" << endl;
			//146
			cout << "Game Over!" << endl << endl;
			//147
			showArray(mineArray);
			//148
			return;
			//149
		}
		//150

		//151
		else {
			//152
			system("cls");
			//153
			displayArray[xPosNum - 1][yPosNum - 1] = mineArray[xPosNum - 1][yPosNum - 1];
			//154
			adjacentCheck();
			//155
			showArray(displayArray);
			//156
			count = checkGame();
			//157

			//158
			if (count == (25 - numMines)) {
				//159
				cout << "\nCongrats! You've cleared all the mines!" << endl;
				//160
				return;
				//161
			}
			//162
		}
		//163
	}
	//164
}
//165

//166
//Function for revealing numbers surrounding a '0' tile
//167
void adjacentCheck()
//168
{
	//169
	if (mineArray[xPosNum - 1][yPosNum - 1] == '0') {
		//170
		for (int x = xPosNum - 2; x != xPosNum + 1; x++) {
			//171
			for (int y = yPosNum - 2; y != yPosNum + 1; y++) {
				//172
				if (x >= 0 && x <= 4) {
					//173
					if (y >= 0 && y <= 4) {
						//174
						displayArray[x][y] = mineArray[x][y];
						//175
						if (mineArray[x][y] == '0') {
							//176
							for (int x2 = x - 1; x2 != x + 2; x2++) {
								//177
								for (int y2 = y - 1; y2 != y + 2; y2++) {
									//178
									if (x2 >= 0 && x2 <= 4) {
										//179
										if (y2 >= 0 && y2 <= 4) {
											//180
											if (mineArray[x2][y2] != '@') {
												//181
												displayArray[x2][y2] = mineArray[x2][y2];
												//182
											}
											//183
										}
										//184
									}
									//185
								}
								//186
							}
							//187
						}
						//188
					}
					//189
				}
				//190
			}
			//191
		}
		//192
	}
	//193
}
//194

//195
//Function to check for a victory
//196
int checkGame()
//197
{

	int count = 0;

	for (int x = 0; x < 5; x++) {
		for (int y = 0; y < 5; y++) {

			if (displayArray[x][y] == mineArray[x][y]) {

				count += 1;

			}

		}

	}
	return count;
}