#pragma once

#include<Windows.h>

void dragMouse(int x1, int y1, int x2, int y2)	{
	SetCursorPos(x1, y1);
	Sleep(300);
	mouse_event(MOUSEEVENTF_LEFTDOWN, 1, 1, MOUSEEVENTF_WHEEL, 0);
	SetCursorPos(x2, y2);
	Sleep(300);
	mouse_event(MOUSEEVENTF_LEFTUP, 1, 1, MOUSEEVENTF_WHEEL, 0);
}

void leftClick()	{
	Sleep(400);
	mouse_event(MOUSEEVENTF_LEFTDOWN, 1, 1, MOUSEEVENTF_WHEEL, 0);
	Sleep(50);
	mouse_event(MOUSEEVENTF_LEFTUP, 1, 1, MOUSEEVENTF_WHEEL, 0);
}

void rightClick() {
	Sleep(400);
	mouse_event(MOUSEEVENTF_RIGHTDOWN, 1, 1, MOUSEEVENTF_WHEEL, 0);
	Sleep(50);
	mouse_event(MOUSEEVENTF_RIGHTUP, 1, 1, MOUSEEVENTF_WHEEL, 0);
}

void moveCursor( int x, int y)	{
	SetCursorPos( x, y);
	Sleep(400);
}