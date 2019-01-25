#include<iostream>
#include<string>
using namespace std;
class Employee{
protected:
	string name;
	string address;
	int year;
	int empnum;
public:
	Employee(string & n, string &a, int en, int y)
	{
		name = n; address = a; year = y; empnum = en;
	}
	string getName()
	{
		return name;
	}
	virtual float monthlySalary() = 0;
	virtual float annualSalary() = 0;
};
class SalariedEmployee:public Employee
{
	float com;
	float bsal;		//assuming monthly
public:
	SalariedEmployee(string & n, string &a, int en, int y, float c, float bs)
		:Employee(n, a, en, y)
	{
		com = c;
		bsal = bs;
	}
	float annualSalary()
	{
		return bsal * 24 + com;		//random formula
	}
	float monthlySalary()
	{
		return bsal;
	}
};
class HourlyEmployee :public Employee
{
	int hw;
	int twh;
public:
	HourlyEmployee(string & n, string &a, int en, int y, int w, int h)
		:Employee(n, a, en, y)
	{
		hw = w;
		twh = h;
	}
	float monthlySalary()
	{
		return (hw*twh * 5);
	}
	float annualSalary()
	{
		return monthlySalary() * 12;
	}
};
class ContractEmployee :public Employee
{
	int nummonth;
	float brpm;
public:
	ContractEmployee(string & n, string &a, int en, int y, int nm, float rpm)
		:Employee(n,a,en,y)
	{
		nummonth = nm;
		brpm = rpm;
	}
	float monthlySalary()
	{
		return brpm;
	}
};