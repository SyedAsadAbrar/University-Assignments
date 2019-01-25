#include<iostream>
#include<vector>
#include<string>
#include"Header.h"
using namespace std;
int main()
{
	vector <Employee*> e;
	int choice;
	string name;
	string address;
	int empnum;
	int year;
	while (true)
	{
		cout << "Enter your choice";
		cin >> choice;
		cout << "Enter name of the Emplyee";
		cin >> name;
		cout << "Enter address of the Emplyee";
		cin >> address;
		cout << "Enter number of the Emplyee";
		cin >> empnum;
		cout << "Enter year of joining of the Emplyee";
		cin >> year;
		if (choice==1)//hourly
		{
			int hw; int twh;
			cout << "Enter hourly wage of the Emplyee";
			cin >> hw;
			cout << "Enter total working hours of the Emplyee";
			cin >> twh;
			e.push_back(new HourlyEmployee(name, address, empnum, year, hw, twh));
		}
		else if (choice==2)//salaried
		{
			float com;
			float bsal;
			cout << "Enter commission of the Emplyee";
			cin >> com;
			cout << "Enter base salary of the Emplyee";
			cin >> bsal;
			e.push_back(new SalariedEmployee(name, address, empnum, year, com, bsal));
		}
		else
		{
			break;
		}
	}
	for (int i = 0; i < e.size(); i++)
	{
		
		cout<<e[i]->monthlySalary()<<endl;

	}
	return 0;
}