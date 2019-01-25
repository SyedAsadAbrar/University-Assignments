class Clock
{
	int h,m,s;
	void tickSec();
	void tickMin();
	void tickHour();

public:
	Clock();
	void set(int,int,int);
	void tick();
	void display();
	Clock(int,int,int);
}