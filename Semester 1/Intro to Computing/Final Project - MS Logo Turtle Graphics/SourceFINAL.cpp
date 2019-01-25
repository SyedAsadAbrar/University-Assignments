// PROJECT BY:
// 16L-4211
// 16L-4292

#include "help.h"
#include "MyMouse.h"
#include<iostream>
#include<string>
#include<string.h>
#include <vector>
#include<conio.h>
#include<stdio.h>
#include<stdlib.h>
#include<cstring>
#include<fstream>
//
bool pu()
{
	return false;
}
bool pd()
{
	return true;
}
//
void color(int value, int array[3])
{
	if (value == 1) //red
	{
		array[0] = 255;
		array[1] = 0;
		array[2] = 0;
	}
	if (value == 2) //pink
	{
		array[0] = 255;
		array[1] = 177;
		array[2] = 214;
	}
	//if (value == 3) //brown
	//{
	//	array[0]=140;
	//	array[1]=90;
	//	array[2]=60;
	//}
	if (value == 4) //white
	{
		array[0] = 255;
		array[1] = 255;
		array[2] = 255;
	}
	//if (value == 5) //orange
	//{
	//	array[0]=255;
	//	array[1]=100;
	//	array[2]=0;
	//}
	//if (value == 6) //purple
	//{
	//	array[0]=155;
	//	array[1]=0;
	//	array[2]=255;
	//}
	if (value == 7) //yellow
	{
		array[0] = 255;
		array[1] = 200;
		array[2] = 0;
	}
	//if (value == 8) //grey
	//{
	//	array[0]=100;
	//	array[1]=100;
	//	array[2]=100;
	//}
	if (value == 9) //black
	{
		array[0] = 0;
		array[1] = 0;
		array[2] = 0;
	}
	if (value == 10) //blue
	{
		array[0] = 0;
		array[1] = 0;
		array[2] = 255;
	}
	if (value == 11) //green
	{
		array[0] = 0;
		array[1] = 155;
		array[2] = 0;
	}
}
//
void circle(int w, int c, int r, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredEllipse(ox,oy,ox+r,oy-r, a[0], a[1], a[2], w);
}
//
void origPointer(int x, int y) //Coordinates of left-corner vertex of pointer
{
	int x1 = x + 2;
	int y1 = y;
	int x2 = x - 2;
	int y2 = y - 4;
	myEllipse(x1, y1, x2, y2);
	//myLine(x, y, x1, y1);
	//myLine(x, y, x2, y2);
	//myLine(x2, y2, x1, y1);
}
void erasePointer(int x, int y)
{
	int x1 = x + 2;
	int y1 = y;
	int x2 = x - 2;
	int y2 = y - 4;
	eraseEllipse(x1, y1, x2, y2);
	//eraseLine(x, y, x1, y1);
	//eraseLine(x, y, x2, y2);
	//eraseLine(x2, y2, x1, y1);
}
//
void fd(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox, oy - d, a[0], a[1], a[2], w);
}
void bk(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox, oy + d, a[0], a[1], a[2], w);
}
void fd_45lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - (d / 2) - 5, oy - (d / 2) - 5, a[0], a[1], a[2], w);
}
void fd_90lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - d, oy, a[0], a[1], a[2], w);
}
void fd_135lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - (d / 2) - 10, oy + (d / 2) + 10, a[0], a[1], a[2], w);
}
void fd_180lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox, oy + d, a[0], a[1], a[2], w);
}
void fd_225lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + (d / 2) + 10, oy + (d / 2) + 10, a[0], a[1], a[2], w);
}
void fd_270lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + d, oy, a[0], a[1], a[2], w);
}
void fd_315lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + (d / 2) + 10, oy - (d / 2) - 10, a[0], a[1], a[2], w);
}
//
void bk_45lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + (d / 2) + 10, oy + (d / 2) + 10, a[0], a[1], a[2], w);
}
void bk_90lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + d, oy, a[0], a[1], a[2], w);
}
void bk_135lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + (d / 2) + 10, oy - (d / 2) - 10, a[0], a[1], a[2], w);
}
void bk_180lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox, oy - d, a[0], a[1], a[2], w);
}
void bk_225lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - (d / 2) - 10, oy - (d / 2) - 10, a[0], a[1], a[2], w);
}
void bk_270lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - d, oy, a[0], a[1], a[2], w);
}
void bk_315lt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - (d / 2) - 10, oy + (d / 2) + 10, a[0], a[1], a[2], w);
}
//
void fd_45rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + (d / 2) + 10, oy - (d / 2) - 10, a[0], a[1], a[2], w);
}
void fd_90rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + d, oy, a[0], a[1], a[2], w);
}
void fd_135rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + (d / 2) + 10, oy + (d / 2) + 10, a[0], a[1], a[2], w);
}
void fd_180rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox, oy + d, a[0], a[1], a[2], w);
}
void fd_225rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - (d / 2) - 10, oy + (d / 2) + 10, a[0], a[1], a[2], w);
}
void fd_270rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - d, oy, a[0], a[1], a[2], w);
}
void fd_315rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - (d / 2) - 10, oy - (d / 2) - 10, a[0], a[1], a[2], w);
}
//
void bk_45rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - (d / 2) - 10, oy + (d / 2) + 10, a[0], a[1], a[2], w);
}
void bk_90rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - d, oy, a[0], a[1], a[2], w);
}
void bk_135rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox - (d / 2) - 10, oy - (d / 2) - 10, a[0], a[1], a[2], w);
}
void bk_180rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox, oy + d, a[0], a[1], a[2], w);
}
void bk_225rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + (d / 2) + 10, oy - (d / 2) - 10, a[0], a[1], a[2], w);
}
void bk_270rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + d, oy, a[0], a[1], a[2], w);
}
void bk_315rt(int w, int c, int d, int ox, int oy)
{
	int a[3];
	color(c, a);
	coloredline(ox, oy, ox + (d / 2) + 10, oy + (d / 2) + 10, a[0], a[1], a[2], w);
}
//
void rotateleft(int ang, int x, int y)
{
	//	int x1, x2, y1, y2;
	//	x1 = x + 10;
	//	y1 = y;
	//	x2 = x + 5;
	//	y2 = y - 5;
	//	if (ang == 0 || ang == 360)
	//	{
	//		x1 = x + 8;
	//		y1 = y;
	//		x2 = x + 5;
	//		y2 = y -4;
	//
	//	}
	//	if (ang == 45)
	//	{
	//		y = y + 4;
	//		y1 = y1 - 5;
	//		x2 = x2 - 10;
	//		y2 = y2-4;
	//
	//		//myLine(x, y, x1, y1);
	//		//myLine(x, y, x2, y2);
	//		//myLine(x2, y2, x1, y1);
	//	}
	//	if (ang == 90)
	//	{
	//		y = y + 10;
	//		y1 = y1 - 10;
	//		x2 = x2 - 10;
	//		y2 = y2;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		y2 = y2 + 10;
	//	}
	//	if (ang == 135)
	//	{
	//		y = y + 10;
	//		y1 = y1 - 10;
	//		x2 = x2 - 10;
	//		y2 = y2;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		y2 = y2 + 10;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		x2 = x2;
	//		y2 = y2 + 10;
	//	}
	//	if (ang == 180)
	//	{
	//		y = y + 10;
	//		y1 = y1 - 10;
	//		x2 = x2 - 10;
	//		y2 = y2;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		y2 = y2 + 10;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		x2 = x2;
	//		y2 = y2 + 10;
	//		y = y - 10;
	//		y1 = y1 + 10;
	//		x2 = x2 + 10;
	//	}
	//	if (ang == 225)
	//	{
	//		y = y + 10;
	//		y1 = y1 - 10;
	//		x2 = x2 - 10;
	//		y2 = y2;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		y2 = y2 + 10;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		x2 = x2;
	//		y2 = y2 + 10;
	//		y = y - 10;
	//		y1 = y1 + 10;
	//		x2 = x2 + 10;
	//		y = y - 10;
	//		y1 = y1 + 10;
	//		x2 = x2 + 10;
	//	}
	//	if (ang == 270)
	//	{
	//		y = y + 10;
	//		y1 = y1 - 10;
	//		x2 = x2 - 10;
	//		y2 = y2;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		y2 = y2 + 10;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		x2 = x2;
	//		y2 = y2 + 10;
	//		y = y - 10;
	//		y1 = y1 + 10;
	//		x2 = x2 + 10;
	//		y = y - 10;
	//		y1 = y1 + 10;
	//		x2 = x2 + 10;
	//		x1 = x1 + 10;
	//		x = x - 10;
	//		y2 = y2 - 10;
	//	}
	//	if (ang == 315)
	//	{
	//		y = y + 10;
	//		y1 = y1 - 10;
	//		x2 = x2 - 10;
	//		y2 = y2;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		y2 = y2 + 10;
	//		x = x + 10;
	//		x1 = x1 - 10;
	//		x2 = x2;
	//		y2 = y2 + 10;
	//		y = y - 10;
	//		y1 = y1 + 10;
	//		x2 = x2 + 10;
	//		y = y - 10;
	//		y1 = y1 + 10;
	//		x2 = x2 + 10;
	//		x1 = x1 + 10;
	//		x = x - 10;
	//		y2 = y2 - 10;
	//		x = x - 10;
	//		x1 = x1 + 10;
	//		y2 = y2 - 10;
	//	}
	//
}
//
void rotateright(int ang, int x, int y)
{
	//	int x1, x2, y1, y2;
	//	x1 = x + 10;
	//	y1 = y;
	//	x2 = x + 5;
	//	y2 = y - 5;
	//	if (ang == 0 || ang == 360)
	//	{
	//		x1 = x + 10;
	//		y1 = y;
	//		x2 = x + 5;
	//		y2 = y - 5;
	//
	//	}
	//	if (ang == 45)
	//	{
	//		y = y - 5;
	//		y1 = y1 + 5;
	//		x2 = x2 + 5;
	//		y2 = y2;
	//	}
	//	if (ang == 90)
	//	{
	//		y = y - 5;
	//		y1 = y1 + 5;
	//		x2 = x2 + 5;
	//		y2 = y2;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		y2 = y2 + 5;
	//	}
	//	if (ang == 135)
	//	{
	//		y = y - 5;
	//		y1 = y1 + 5;
	//		x2 = x2 + 5;
	//		y2 = y2;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		y2 = y2 + 5;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		x2 = x2;
	//		y2 = y2 + 5;
	//	}
	//	if (ang == 180)
	//	{
	//		y = y - 5;
	//		y1 = y1 + 5;
	//		x2 = x2 + 5;
	//		y2 = y2;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		y2 = y2 + 5;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		x2 = x2;
	//		y2 = y2 + 5;
	//		y = y + 5;
	//		y1 = y1 - 5;
	//		x2 = x2 - 5;
	//	}
	//	if (ang == 225)
	//	{
	//		y = y - 5;
	//		y1 = y1 + 5;
	//		x2 = x2 + 5;
	//		y2 = y2;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		y2 = y2 + 5;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		x2 = x2;
	//		y2 = y2 + 5;
	//		y = y + 5;
	//		y1 = y1 - 5;
	//		x2 = x2 - 5;
	//		y = y + 5;
	//		y1 = y1 - 5;
	//		x2 = x2 - 5;
	//	}
	//	if (ang == 270)
	//	{
	//		y = y - 5;
	//		y1 = y1 + 5;
	//		x2 = x2 + 5;
	//		y2 = y2;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		y2 = y2 + 5;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		x2 = x2;
	//		y2 = y2 + 5;
	//		y = y + 5;
	//		y1 = y1 - 5;
	//		x2 = x2 - 5;
	//		y = y + 5;
	//		y1 = y1 - 5;
	//		x2 = x2 - 5;
	//		x1 = x1 + 5;
	//		x = x - 5;
	//		y2 = y2 - 5;
	//	}
	//	if (ang == 315)
	//	{
	//		y = y - 5;
	//		y1 = y1 + 5;
	//		x2 = x2 + 5;
	//		y2 = y2;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		y2 = y2 + 5;
	//		x = x + 5;
	//		x1 = x1 - 5;
	//		x2 = x2;
	//		y2 = y2 + 5;
	//		y = y + 5;
	//		y1 = y1 - 5;
	//		x2 = x2 - 5;
	//		y = y + 5;
	//		y1 = y1 - 5;
	//		x2 = x2 - 5;
	//		x1 = x1 + 5;
	//		x = x - 5;
	//		y2 = y2 - 5;
	//		x = x - 5;
	//		x1 = x1 + 5;
	//		y2 = y2 - 5;
	//	}
}
//

using namespace std;
int main()
{
	int radius;
	bool pen=1;
	int colorval = 4;
	int widthval = 2;
	int count = 0;
	int a = 300, b = 150;
	int angle = 0;
	int i = 0;
	char history[100][30];
	char command[500];
	do{
		myRect(0, 250, 166, 5, 255, 200, 0);//instruction box
		gotoxy(0, 1);
		cout << " INSTRUCTIONS: ";
		gotoxy(0, 3);
		cout << "> Forward = fd";
		gotoxy(0, 4);
		cout << "> Backward = bk";
		gotoxy(0, 5);
		cout << "> Rotate left = lt";
		gotoxy(0, 6);
		cout << "> Rotate right = rt";
		gotoxy(0, 7);
		cout << "> Clear Screen = cs";
		gotoxy(0, 8);
		cout << "> Center turtle = ct";
		gotoxy(0, 9);
		cout << "> Color change :";
		gotoxy(0, 10);
		cout << "type : color blue";
		gotoxy(0, 11);
		cout << "pink,yellow,red,";
		gotoxy(0, 12);
		cout << "black,white,green";
		gotoxy(0, 13);
		cout << "> for save = save";
		gotoxy(0, 14);
		cout << "> for width :";
		gotoxy(0, 15);
		cout << "type: width 2";
		gotoxy(0, 16);
		cout << "Pen up = pu";
		gotoxy(0, 17);
		cout << "Pen down = pd";
		gotoxy(0, 18);
		cout << "> Circle = circle x";
		gotoxy(0, 19);
		cout << "where x = integer";
		/*gotoxy(0, 2);
		gotoxy(0, 2);*/
		myLine(0, 250, 650, 250);//command box
		gotoxy(0, 21);
		cout << "Command Box:";
		gotoxy(0, 22);
		origPointer(a, b);
		gets_s(command);
		strcpy_s(history [i],command);
		erasePointer(a, b);
		gotoxy(a, b);
		int val1 = atoi(command + 2);
		if (command[2] == '\0' || command[3] == '\0')
		{
			gotoxy(0, 23);

			cout << "Incomplete command...Press any key to try again...\n";
			_getch();
		}
		else if (command[0] == 'w' && command[1] == 'i' && command[2] == 'd' && command[3] == 't' && command[4] == 'h')
		{
			if (command[6] == '1')
			{
				widthval = 1;
			}
			if (command[6] == '2')
			{
				widthval = 2;
			}
			if (command[6] == '3')
			{
				widthval = 3;
			}
		}
		else if (command[0] == 'p' && command[1] == 'u')
		{
			pen=pu();
		}
		else if (command[0] == 'p' && command[1] == 'd')
		{
			pen=pd();
		}
		else if (command[0] == 'c' && command[1] == 'o' && command[2] == 'l' && command[3] == 'o' && command[4] == 'r') ////assuming that entering color makes pen go in 'down' mode
		{
			if (command[6] == 'r' && command[7] == 'e' && command[8] == 'd')
			{
				colorval = 1;
			}
			if (command[6] == 'p' && command[7] == 'i' && command[8] == 'n' && command[9] == 'k')
			{
				colorval = 2;
			}
			if (command[6] == 'w' && command[7] == 'h' && command[8] == 'i' && command[9] == 't' && command[10] == 'e')
			{
				colorval = 4;
			}
			if (command[6] == 'y' && command[7] == 'e' && command[8] == 'l' && command[9] == 'l' && command[10] == 'o' && command[11] == 'w')
			{
				colorval = 7;
			}
			if (command[6] == 'b' && command[7] == 'l' && command[8] == 'a' && command[9] == 'c' && command[10] == 'k')
			{
				colorval = 9;
			}
			if (command[6] == 'b' && command[7] == 'l' && command[8] == 'u' && command[9] == 'e')
			{
				colorval = 10;
			}
			if (command[6] == 'g' && command[7] == 'r' && command[8] == 'e' && command[9] == 'e' && command[10] == 'n')
			{
				colorval = 11;
			}
		}
		else if (command[0] == 'c' && command[1] == 'i' && command[2] == 'r' && command[3] == 'c' && command[4] == 'l' && command[5] == 'e')
		{
			radius = atoi(command + 6);
			circle(widthval,colorval,radius,a-radius/2,b+radius/2);
		}
		else if (command[0] == 'f' && command[1] == 'd'&& angle == 0 || angle == 360)
		{
			if (pen==true)
			{
				fd(widthval, colorval, val1, a, b);
			}
			b = b - val1;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == 0 || angle == 360)
		{
			if (pen==true)
			{
				bk(widthval, colorval, val1, a, b);
			}
			b = b + val1;
		}
		else if (command[0] == 'l'&&command[1] == 't')
		{
			angle = angle + val1;
			rotateleft(angle, a, b);
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == 45)
		{
			if (pen==true)
			{
				fd_45lt(widthval, colorval, val1, a, b);
			}
			b = b - (val1 / 2) - 10;
			a = a - (val1 / 2) - 10;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == 90)
		{
			if (pen==true)
			{
				fd_90lt(widthval, colorval, val1, a, b);
			}
			a = a - val1;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == 135)
		{
			if (pen==true)
			{
				fd_135lt(widthval, colorval, val1, a, b);
			}
			b = b + (val1 / 2) + 10;
			a = a - (val1 / 2) - 10;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == 180)
		{
			if (pen==true)
			{
				fd_180lt(widthval, colorval, val1, a, b);
			}
			b = b + val1;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == 225)
		{
			if (pen==true)
			{
				fd_225lt(widthval, colorval, val1, a, b);
			}
			b = b + (val1 / 2) + 10;
			a = a + (val1 / 2) + 10;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == 270)
		{
			if (pen==true)
			{
				fd_270lt(widthval, colorval, val1, a, b);
			}
			a = a + val1;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == 315)
		{
			if (pen==true)
			{
				fd_315lt(widthval, colorval, val1, a, b);
			}
			b = b - (val1 / 2) - 10;
			a = a + (val1 / 2) + 10;
		}
		//
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == 45)
		{
			if (pen==true)
			{
				bk_45lt(widthval, colorval, val1, a, b);
			}
			b = b + (val1 / 2) + 10;
			a = a + (val1 / 2) + 10;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == 90)
		{
			if (pen==true)
			{
				bk_90lt(widthval, colorval, val1, a, b);
			}		
			a = a + val1;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == 135)
		{
			if (pen==true)
			{
				bk_135lt(widthval, colorval, val1, a, b);
			}
			b = b - (val1 / 2) - 10;
			a = a + (val1 / 2) + 10;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == 180)
		{
			if (pen==true)
			{
				bk_180lt(widthval, colorval, val1, a, b);
			}
			b = b - val1;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == 225)
		{
			if (pen==true)
			{
				bk_225lt(widthval, colorval, val1, a, b);
			}
			b = b - (val1 / 2) - 10;
			a = a - (val1 / 2) - 10;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == 270)
		{
			if (pen==true)
			{
				bk_270lt(widthval, colorval, val1, a, b);
			}
			a = a - val1;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == 315)
		{
			if (pen==true)
			{
				bk_315lt(widthval, colorval, val1, a, b);
			}
			b = b + (val1 / 2) + 10;
			a = a - (val1 / 2) - 10;
		}
		//
		else if (command[0] == 'r'&&command[1] == 't')
		{
			angle = angle - val1;
			rotateright(angle, a, b);
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == -45)
		{
			if (pen==true)
			{
				fd_45rt(widthval, colorval, val1, a, b);
			}
			b = b - (val1 / 2) - 10;
			a = a + (val1 / 2) + 10;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == -90)
		{
			if (pen==true)
			{
				fd_90rt(widthval, colorval, val1, a, b);
			}
			a = a + val1;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == -135)
		{
			if (pen==true)
			{
				fd_135rt(widthval, colorval, val1, a, b);
			}
			b = b + (val1 / 2) + 10;
			a = a + (val1 / 2) + 10;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == -180)
		{
			if (pen==true)
			{
				fd_180rt(widthval, colorval, val1, a, b);
			}
			b = b + val1;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == -225)
		{
			if (pen==true)
			{
				fd_225rt(widthval, colorval, val1, a, b);
			}
			b = b + (val1 / 2) + 10;
			a = a - (val1 / 2) - 10;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == -270)
		{
			if (pen==true)
			{
				fd_270rt(widthval, colorval, val1, a, b);
			}
			a = a - val1;
		}
		else if (command[0] == 'f'&&command[1] == 'd'&& angle == -315)
		{
			if (pen==true)
			{
				fd_315rt(widthval, colorval, val1, a, b);
			}
			b = b - (val1 / 2) - 10;
			a = a - (val1 / 2) - 10;
		}
		//
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == -45)
		{
			if (pen==true)
			{
				bk_45rt(widthval, colorval, val1, a, b);
			}
			b = b + (val1 / 2) + 10;
			a = a - (val1 / 2) - 10;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == -90)
		{
			if (pen==true)
			{
				bk_90rt(widthval, colorval, val1, a, b);
			}
			a = a - val1;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == -135)
		{
			if (pen==true)
			{
				bk_135rt(widthval, colorval, val1, a, b);
			}
			b = b - (val1 / 2) - 10;
			a = a - (val1 / 2) - 10;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == -180)
		{
			if (pen==true)
			{
				bk_180rt(widthval, colorval, val1, a, b);
			}
			b = b + val1;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == -225)
		{
			if (pen==true)
			{
				bk_225rt(widthval, colorval, val1, a, b);
			}
			b = b - (val1 / 2) - 10;
			a = a + (val1 / 2) + 10;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == -270)
		{
			if (pen==true)
			{
				bk_270rt(widthval, colorval, val1, a, b);
			}
			a = a + val1;
		}
		else if (command[0] == 'b'&&command[1] == 'k'&& angle == -315)
		{
			if (pen==true)
			{
				bk_315rt(widthval, colorval, val1, a, b);
			}
			b = b + (val1 / 2) + 10;
			a = a + (val1 / 2) + 10;
		}
		else if (command[0]=='s'&&command[1]=='a'&&command[2]=='v'&&command[3]=='e')
		{
			ofstream bazaid("myfile.txt");
			for (int j = 0; j <=i ; j++)
			{
				bazaid << history[j];
			}
		}
		else if (command[0] == 'c' && command[1] == 's')
		{
			cout << "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             ";
			erasePointer(a, b);
			angle = 0;
			a = 300, b = 150;
			origPointer(a, b);
		}
		else if (command[0] == 'c'&&command[1] == 't')
		{
			erasePointer(a, b);
			a = 300, b = 150;
			origPointer(a, b);
			angle = 0;
		}
		else
		{
			gotoxy(0, 23);
			cout << "Shugal tou na lgao Sarkar:D...Press any key to try again :)\n";
			_getch();
		}
		i++;
		system("cls");

	} while (1);

}