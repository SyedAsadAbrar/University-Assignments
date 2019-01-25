﻿#include <windows.h>

void gotoxy(int x, int y)
{
	COORD coord;
	coord.X = x;
	coord.Y = y;
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);

}
void coloredline(int x1, int y1, int x2, int y2, int c1, int c2, int c3, int w) //use three 3 integers if you want colored lines.
{
	HWND console_handle = GetConsoleWindow();
    HDC device_context = GetDC(console_handle);

    //change the color by changing the values in RGB (from 0-255).
	HPEN pen =CreatePen(PS_SOLID,w,RGB(c1,c2,c3)); //2 is the width of the pen
    SelectObject(device_context,pen);
	MoveToEx(device_context,x1,y1,NULL);
    LineTo(device_context,x2, y2);
	ReleaseDC(console_handle, device_context);  
	::DeleteObject(pen);
	::DeleteObject(device_context);
}
void coloredEllipse(int x1, int y1, int x2, int y2, int c1, int c2, int c3, int w) //ellipse takes in color information as well
{
	HWND console_handle = GetConsoleWindow();
    HDC device_context = GetDC(console_handle);

    //change the color by changing the values in RGB (from 0-255)
    HPEN pen =CreatePen(PS_SOLID,w,RGB(c1,c2,c3)); 
    SelectObject(device_context,pen);
	HBRUSH brush = ::CreateSolidBrush(RGB(0,0,0));  //Fill color is black.  change these values to change the fill color.
	SelectObject(device_context,brush);
	Ellipse(device_context,x1,y1,x2,y2);
	ReleaseDC(console_handle, device_context);
	::DeleteObject(pen);
	::DeleteObject(brush);
	::DeleteObject(device_context);
}
void myLine(int x1, int y1, int x2, int y2) //use three 3 integers if you want colored lines.
{
	HWND console_handle = GetConsoleWindow();
    HDC device_context = GetDC(console_handle);

    //change the color by changing the values in RGB (from 0-255).
	HPEN pen =CreatePen(PS_SOLID,1,RGB(255,255,255)); //2 is the width of the pen
    SelectObject(device_context,pen);
	MoveToEx(device_context,x1,y1,NULL);
    LineTo(device_context,x2, y2);
	ReleaseDC(console_handle, device_context);  
	::DeleteObject(pen);
	::DeleteObject(device_context);
}
void eraseLine(int x1, int y1, int x2, int y2) //use three 3 integers if you want colored lines.
{
	HWND console_handle = GetConsoleWindow();
	HDC device_context = GetDC(console_handle);

	//change the color by changing the values in RGB (from 0-255).
	HPEN pen = CreatePen(PS_SOLID, 1, RGB(0, 0, 0)); //2 is the width of the pen
	SelectObject(device_context, pen);
	MoveToEx(device_context, x1, y1, NULL);
	LineTo(device_context, x2, y2);
	ReleaseDC(console_handle, device_context);
	::DeleteObject(pen);
	::DeleteObject(device_context);
}

void myRect(int x1, int y1, int x2, int y2,int color1,int color2,int color3) //this function also takes in color information
{
	HWND console_handle = GetConsoleWindow();
    HDC device_context = GetDC(console_handle);

    //change the color by changing the values in RGB (from 0-255)
    HPEN pen =CreatePen(PS_SOLID,2,RGB(color1,color2,color3)); 
    SelectObject(device_context,pen);
	HBRUSH brush = ::CreateSolidBrush(RGB(255,0,0)); //Fill color is black. change these values to change the fill color.
	
	SelectObject(device_context,brush);
	
	Rectangle(device_context,x1,y1,x2,y2);
	ReleaseDC(console_handle, device_context); 
	::DeleteObject(pen);
	::DeleteObject(brush);
	::DeleteObject(device_context);
}
void myEllipse(int x1, int y1, int x2, int y2) //ellipse takes in color information as well
{
	HWND console_handle = GetConsoleWindow();
    HDC device_context = GetDC(console_handle);

    //change the color by changing the values in RGB (from 0-255)
    HPEN pen =CreatePen(PS_SOLID,1,RGB(255,255,255)); 
    SelectObject(device_context,pen);
	HBRUSH brush = ::CreateSolidBrush(RGB(0,0,0));  //Fill color is black.  change these values to change the fill color.
	SelectObject(device_context,brush);
	Ellipse(device_context,x1,y1,x2,y2);
	ReleaseDC(console_handle, device_context);
	::DeleteObject(pen);
	::DeleteObject(brush);
	::DeleteObject(device_context);
}
void eraseEllipse(int x1, int y1, int x2, int y2) //ellipse takes in color information as well
{
	HWND console_handle = GetConsoleWindow();
	HDC device_context = GetDC(console_handle);

	//change the color by changing the values in RGB (from 0-255)
	HPEN pen = CreatePen(PS_SOLID, 1, RGB(0, 0, 0));
	SelectObject(device_context, pen);
	HBRUSH brush = ::CreateSolidBrush(RGB(255, 255, 255));  //Fill color is black.  change these values to change the fill color.
	SelectObject(device_context, brush);
	Ellipse(device_context, x1, y1, x2, y2);
	ReleaseDC(console_handle, device_context);
	::DeleteObject(pen);
	::DeleteObject(brush);
	::DeleteObject(device_context);
}

void mySetPixel(int x1, int y1, int color1, int color2, int color3)
{
	HWND console_handle = GetConsoleWindow();
	HDC device_context = GetDC(console_handle);

	SetPixel(device_context, x1, y1, RGB(color1, color2, color3));
	::DeleteObject(device_context);


}


