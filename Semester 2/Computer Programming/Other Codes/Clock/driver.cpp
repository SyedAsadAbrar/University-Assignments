#include "clock.h"
#include<time.h>
#include<Windows.h>
int main()
{
	Clock c;
	c.set(0,0,0);
	while (true)
	{
		c.display();
		c.tick();
		Sleep(1000);
	}
}