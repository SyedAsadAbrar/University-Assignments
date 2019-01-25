#include "clock.h"
#include<iostream>
using namespace std;
void Clock::set(int hr, int min, int sec)
{
	h=hr;
	m=min;
	s=sec;
}
void Clock::display()
{
	cout<<h<<":"<<m<<":"<<s;
}
void Clock::tickSec()
{
	if (s<59)
	{
		s++;
	}
	else
	{
		s=0;
		tickMin();
	}
}
void Clock::tickMin()
{
	if (m<59)
	{
		m++;
	}
	else
	{
		m=0;
		tickHour();
	}
}
void Clock::tickHour()
{
	h=(h+1)%24;
}
void Clock::tick()
{
	tickSec();
}
Clock::Clock(int hr, int min, int sec)
{
	h=hr;
	m=min;
	s=sec;
}
Clock::Clock();
{
	h=m=s=0;
}