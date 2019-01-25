#include<iostream>
#include<fstream>
#include<string>
#include<stdlib.h>
#include<sstream>
#include"linkedlist.h"
using namespace std;

template<class type>
class Adjlist
{
	linked_list<type>* l;
	int v;
	bool weighted;	//0 for unweighted 1 for weighted
	int givesize(string arr)
	{
		int size = 0;
		for (int i = 0; arr[i] != '\0'; i++)
		{
			if (arr[i] <= '9' && arr[i] >= '0')
				size++;
		}
		return size;
	}

public:
	Adjlist()
	{
		l = nullptr;
		v = 0;
		weighted = 0;
	}

	void Input()
	{
		l = new linked_list<type>[v];
		int**list = new int*[v];
		int count = 0;
		string line;
		ifstream myfile("Graph.txt");
		if (myfile.is_open())
		{
			while (getline(myfile, line))
			{
				if (count == 0)
				{
					stringstream geek(line);
					geek >> v;
					count++;
					cout << v << endl;
				}
				else if (count == 1)
				{
					stringstream geek(line);
					geek >> weighted;
					count++;
					cout << weighted << endl;
				}
				else if (count > 1)
				{
					int n = line.length();
					cout << n << endl;
				}
				getline(myfile, line);
				//cout << line << '\n';
			}
			myfile.close();
		}
	}

	void Output()
	{

	}

	~Adjlist()
	{

	}
};