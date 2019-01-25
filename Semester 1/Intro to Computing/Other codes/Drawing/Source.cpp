#include<conio.h>
#include<string.h>
#include <windows.h>
#include <time.h>
#include "help.h"

using namespace std;
int main()
{
	myLine(220,200,230,140);
	myLine(230,140,255,100);
	myLine(255,100,280,90);
	myLine(280,90,320,85);
	myLine(320,85,360,90);
	myLine(360,90,385,100);
	myLine(385,100,410,140);
	myLine(410,140,420,200);
	for (int i=0;i<=200;i=i+20)
	{
		myRect(i,170,i+20,200,255,255,255);
	}
	for (int i=00;i<=200;i=i+20)
	{
		myEllipse(i,160,i+20,180,255,255,255);
	}
	for (int i=421;i<=900;i=i+20)
	{
		myRect(i,170,i+20,200,255,255,255);
	}
	for (int i=421;i<=900;i=i+20)
	{
		myEllipse(i,160,i+20,180,255,255,255);
	}
	myLine(0,200,1000,200);
	myLine(0,220,1000,220);
	for (int i=20;i<=200;i=i+70)
	{
		myEllipse(i,240,i+40,280,255,255,255);
	}
	for (int i=20;i<=200;i=i+70)
	{
		myRect(i+1,260,i+40,300,255,255,255);
	}
	for (int i=440;i<=900;i=i+70)
	{
		myEllipse(i,240,i+40,280,255,255,255);
	}
	for (int i=440;i<=900;i=i+70)
	{
		myRect(i+1,260,i+40,300,255,255,255);
	}
	myLine(220,240,220,900);
	myLine(219,240,421,240);
	myLine(421,240,421,900);
	myLine(240,260,240,900);
	myLine(239,260,400,260);
	myLine(400,260,400,900);
	_getch();
}