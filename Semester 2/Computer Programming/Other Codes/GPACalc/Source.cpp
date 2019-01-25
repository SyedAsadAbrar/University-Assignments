#include<iostream>
#include<windows.h>
#include<stdio.h>
#include<winuser.h>
#include<windowsx.h>
#include<time.h>
#include<stdlib.h>
#include<conio.h>
#include<vector>
#include<string>
#include<fstream>
int main()
{
	float subjects;
	std::string a;
	float temp;
	float credhours=0;
	float gpa=0.0;
	float cgpa;
	std::vector <std::string> subjectname;
	std::vector <float> grades;
	std::vector <float> creds;
	char c[100]="WELCOME TO ASAD'S GPA CALCULATOR. HEHE xD";
	for (int i=0;c[i]!='\0';i++)
	{
		std::cout<<c[i];
		Sleep(100);
	}
	std::cout<<std::endl;
	getch();
	system("cls");
	std::cout<<"Enter number of subjects."<<std::endl;
	std::cin>>subjects;
	Sleep(1000);
	system("cls");
	std::cout<<"Enter "<<subjects<<" names, grades and credit hours."<<std::endl;
	for (int i=0;i<subjects;i++)
	{
		std::cin>>a;
		std::getline(std::cin,a);
		subjectname.push_back(a);
		std::cin>>a;
		if (a=="A" || a=="A+")
		{
			grades.push_back(4);
		}
		else if (a=="A-")
		{
			grades.push_back(3.67);
		}
		else if (a=="B+")
		{
			grades.push_back(3.33);
		}
		else if (a=="B")
		{
			grades.push_back(3);
		}
		else if (a=="B-")
		{
			grades.push_back(2.67);
		}
		else if (a=="C+")
		{
			grades.push_back(2.33);
		}
		else if (a=="C")
		{
			grades.push_back(2);
		}
		else if (a=="C-")
		{
			grades.push_back(1.67);
		}
		else if (a=="D+")
		{
			grades.push_back(1.33);
		}
		else if (a=="D")
		{
			grades.push_back(1);
		}
		else 
		{
			std::cout<<"Wrong grade entered.\n";
			std::cin>>a;
		}
		std::cin>>temp;
		creds.push_back(temp);
		credhours+=temp;
	}
	for (int i=0;i<3;i++)
	{
		std::cout<<"Please wait";
		Sleep(1000);
		system("cls");
		std::cout<<"Please wait.";
		Sleep(1000);
		system("cls");
		std::cout<<"Please wait..";
		Sleep(1000);
		system("cls");
		std::cout<<"Please wait...";
		Sleep(1000);
		system("cls");
	}
	std::cout<<"Result compiled.\n";
	getch();
	for (int i=0;i<subjects;i++)
	{
		gpa=gpa+(grades[i]/4)*creds[i];
	}
	gpa=gpa/credhours*4;
	std::cout<<"Your SGPA is "<<gpa<<".\n";
	getch();
	if (gpa < 3.78)
	{
		std::cout<<"You suck.\n";
		Sleep(1000);
		system("cls");
	}
	else
	{
		std::cout<<"NOICE.\n";
		Sleep(1000);
		system("cls");
	}
	getch();
	std::cout<<"Do you want to calculate your updated CGPA?\n";
	std::cin>>a;
	if (a=="Yes" || a=="YES" || a=="yes")
	{
		std::cout<<"Enter current CGPA.\n";
		std::cin>>cgpa;
		cgpa+=gpa;
		cgpa/=2;
		std::cout<<"Your CGPA is "<<cgpa<<".\n";
		getch();
		if (cgpa < 3.78)
		{
			std::cout<<"You suck.\n";
			Sleep(1000);
			system("cls");
		}
		else
		{
			std::cout<<"NOICE.\n";
			Sleep(1000);
			system("cls");
		}
	}
	getch();
	for(int i=0;i<1000000000;i++)
	{
		std::cout<<"lol lol lol lol lol lol lol lol lol lol lol lol lol lol lol lol lol lol lol lol lol lol";
	}
	system("pause");
	return 0;
}