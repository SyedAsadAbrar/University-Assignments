#pragma once
#include<iostream>
#include<fstream>
#include<string>
using namespace std;

struct AdjNode
{
	int node;
	int weight;
	AdjNode * next;
};

class listIterator
{
	AdjNode * curr;
public:
	//default constructor
	listIterator(AdjNode * parameter = nullptr)
	{
		curr = parameter;
	};
	listIterator & operator++()
	{
		//checking if current pointer doesn't point to last node
		if (curr != nullptr)
		{
			//shifting current pointer to next node of node pointed by current
			curr = curr->next;
		}
		//returning the same listIterator object
		return *this;
	};
	AdjNode operator*()
	{
		//returns data of current node if current points to a node and not to NULL
		if (curr != nullptr)
		{
			return (*curr);
		}
		else
		{
			//error handling
			cout << "Data not found." << endl;
		}
	};
	bool operator!=(listIterator & lit)
	{
		//returns true if both are different, false otherwise
		return !(curr == lit.curr);
	};
	~listIterator()
	{
		curr = nullptr;
	};
};

class LinkedList
{
	AdjNode * head;
public:
	//Constructor
	LinkedList()
	{
		//Pointing head to NULL
		head = nullptr;
	};
	//Destructor
	~LinkedList()
	{
		//i.e. that list is non-empty
		if (head != nullptr)
		{
			//To avoid it from becoming a dangling pointer
			AdjNode * curr = head;

			//Loop-terminating condition, tells us that the list has finished
			while (curr != nullptr)
			{
				//to traverse through the list, this also avoids head becoming a dangling pointer at the end
				head = curr->next;

				//deleting node pointed by curr
				delete curr;

				//pointing curr to head
				curr = head;
			}
		}
	};
	listIterator begin()
	{
		//Creating a temporary listIterator object which points to head node
		listIterator temp(head);

		//Returning the listIterator object
		return temp;
	};
	listIterator end()
	{
		//Creating a temporary listIterator object, since default constructor is called so it points to NULL
		listIterator temp;

		//Returning the listIterator object
		return temp;
	};
	void insert(int d, int wght)
	{
		//creating a node with parameters
		AdjNode * temp = new AdjNode;
		temp->node = d;
		temp->weight = wght;
		temp->next = nullptr;

		//Initializing a pointer on the head for traversal
		AdjNode * curr = head;

		//if list is empty
		if (curr == nullptr)
		{
			head = temp;
			return;
		}
		//if not-empty
		while (curr->next != nullptr)
		{
			curr = curr->next;
		}
		curr->next = temp;
	};
	void remove(int d)
	{
		//will only work if list is non-empty
		if (head != nullptr)
		{
			//for traversal
			AdjNode * curr = head;
			AdjNode * prev = head;

			//list with one node
			if (head->next == nullptr)
			{
				delete head;
				head = nullptr;
			}
			else
			{
				//otherwise
				while (curr->node != d)
				{
					prev = head;
					curr = curr->next;
				}
				//Node to be deleted is pointed by curr
				prev->next = curr->next;
				if (curr != nullptr)
				{
					delete curr;
				}
				curr = nullptr;
			}
		}
	};
};

class AdjList
{
	LinkedList * list;				//built-in singly linked list class
	int v;						//no of vertices
	bool weighted;				//false in case of unweighted and true otherwise
public:
	AdjList()
	{
		v = 0;
		weighted = false;
		list = nullptr;
	};
	void input()				//Since the file doesn't take any parameter, assuming the file would be saved by the name
	{							//"adjlist.txt"
		string line;
		ifstream myfile("adjlist.txt");		//follows the assumption that the list is correctly formatted
		int i = 0;
		int j = 0;
		int index = 0;
		int linesize = 0;
		string temp;
		int tempdata;
		int weight;

		if (myfile.is_open())
		{
			cout << "File opened successfully. Data is being input into the adjacency list from file." << endl;
			system("cls");
			getline(myfile, line);		//first line is number of vertices
			v = stoi(line);
			list = new LinkedList[v];
			getline(myfile, line);		//second line tells if list is weighted or not
			if (line == "weighted")
			{
				weighted = true;
			}
			else
			{
				weighted = false;
			}
			while (getline(myfile, line))
			{
				linesize = line.size();
				while (j < linesize - 1)
				{
					while (line[i] != '>')
					{
						i++;
					}
					i = i + 1;
					j = i;
					if (j >= linesize - 1)
					{
						break;
					}
					while (line[j] != ',')
					{
						j++;
					}
					temp = line.substr(i + 1, j - i - 1);
					tempdata = stoi(temp);
					i = j + 1;
					while (line[j] != ')')
					{
						j++;
					}
					temp = line.substr(i, j - i);
					weight = stoi(temp);
					list[index].insert(tempdata, weight);
				}
				i = j = 0;
				index++;
			}
			myfile.close();
		}
		else 
			cout << "Unable to open file, please check if a file name \"Adjlist.txt\" exists or not.";
	};
	void output()
	{
		//Creating a temporary node
		AdjNode temp;
		for (int i = 0; i < v; i++)
		{
			cout << i << "->";
			//Creating listiterator to traverse list
			listIterator it = list[i].begin();
			while (it != list[i].end())
			{
				temp = (*it);
				cout << "(" << temp.node << "," << temp.weight << ")->";
				++it;
			}
			cout << endl;
		}
	};
	friend bool HamiltonianCycle(AdjList Al, int * & arr);
	friend void Visit(AdjList al, int i, bool * & visited, int * & arr, int & index);
	friend bool MinHamiltonianCycle(AdjList Al, int * & arr);
	friend void MinVisit(AdjList al, int i, bool * & visited, int * & arr, int * & temp, int & index, int & lowest, int & solnum);
};

bool HamiltonianCycle(AdjList Al, int * & arr)
{
	//if weighted list is given, false is returned
	//according to question this takes an unweighted adj list
	if (Al.weighted == false)
	{
		//An array is made of size one greater than vertices to store exact path
		//of traversal(as initial point is repeated i.e. visited twice)
		arr = new int[Al.v + 1];
		int index = 0;
		bool found = false;
		bool loop = false;

		//to mark vertices visited (avoiding infinite loop and wrong answers)
		bool * visited = new bool[Al.v];
		for (int i = 0; i < Al.v; i++)
		{
			visited[i] = false;
		}
		//The helping function is called once because loop
		//stays the same even if starting point is different
		//if and only if it is part of that loop
		Visit(Al, 0, visited, arr, index);

		//if index of array is same as vertices
		//it means we have found a solution
		if (index == Al.v)
		{
			found = true;
			arr[index++] = arr[0];
			return found;
		}

		//otherwise return false
		if (!found)
		{
			delete arr;
			arr = nullptr;
			return found;
		}
	}
}

void Visit(AdjList Al, int i, bool * & visited, int * & arr, int & index)
{
	//mark vertex as visited
	visited[i] = true;

	//put in array
	arr[index++] = i;
	listIterator it;
	bool found = false;
	for (it = Al.list[i].begin(); it != Al.list[i].end(); ++it)
	{
		//if neighbour is not visited, call same function
		//recursively
		if (!visited[(*it).node])
		{
			Visit(Al, (*it).node, visited, arr, index);
		}
	}
	if (index == Al.v)
	{
		//if index=Al.v, i.e. Hamiltonian path is found
		//check if last node is the neighbour of first node
		//if yes then hamiltonian cycle exists
		for (listIterator it = Al.list[arr[index - 1]].begin(); it != Al.list[arr[index - 1]].end(); ++it)
		{
			if ((*it).node == arr[0])
			{
				found = true;
				return;
			}
		}

		//otherwise, it doesn't
		if (!found)
		{
			visited[i] = false;
			arr[--index] = -1;
		}
	}
	else
	{
		//if index is not equal to Al.v
		//then path is not found
		visited[i] = false;
		arr[--index] = -1;
	}
}

bool MinHamiltonianCycle(AdjList Al, int * & arr)
{
	//if unweighted list is given, false is returned
	//according to question this takes a weighted adj list
	if (Al.weighted == true)
	{
		//An array is made of size one greater than vertices to store exact path
		//of traversal(as initial point is repeated i.e. visited twice)
		arr = new int[Al.v + 1];
		
		//temp array to store multiple cycles and compare it with
		//least weighted cycle
		int * temp = new int[Al.v + 1];
		int arrmin = 0;
		int index = 0;
		bool found = false;
		bool loop = false;
		int solnum = 0;

		//to mark vertices visited (avoiding infinite loop and wrong answers)
		bool * visited = new bool[Al.v];
		for (int i = 0; i < Al.v; i++)
		{
			visited[i] = false;
		}
		
		//The helping function is called once because loop
		//stays the same even if starting point is different
		//if and only if it is part of that loop
		MinVisit(Al, 0, visited, temp, arr, index, arrmin, solnum);
		
		//solnum is greater than 0, then we have atleast one solution
		if (solnum > 0)
		{
			found = true;
			return found;
		}

		//solution not found i.e. cycle doesn't exist
		else if (solnum == 0)
		{
			delete arr;
			arr = nullptr;
			return found;
		}
	}
	else
		return false;
}

void MinVisit(AdjList Al, int i, bool * & visited, int * & arr, int * & temp, int & index, int & lowest, int & solnum)
{
	//mark vertex as visited
	visited[i] = true;
	
	//to check if hamiltonian path is also a cycle or not
	bool complete = 0;
	
	//put in array
	arr[index++] = i;
	listIterator it;
	bool found = false;
	for (it = Al.list[i].begin(); it != Al.list[i].end(); ++it)
	{
		//if neighbour is not visited, call same function
		//recursively
		if (!visited[(*it).node])
		{
			MinVisit(Al, (*it).node, visited, arr, temp, index, lowest, solnum);
		}
		//if index=Al.v, it means path exists
		//now checking for existence of cycle
		if (index == Al.v && !complete)
		{
			complete = true;
			for (listIterator it2 = Al.list[arr[index - 1]].begin(); it2 != Al.list[arr[index - 1]].end(); ++it2)
			{
				if ((*it2).node == arr[0])
				{
					arr[index++] = arr[0];
					found = true;
					break;
				}
			}

			//if cycle exists
			if (found)
			{
				if (solnum == 0)
				{
					//copying into array which will
					//be used later for comparison
					for (int i = 0; i < index; i++)
					{
						temp[i] = arr[i];
					}
					solnum++;

					//calculating sum of weights of nodes
					//in cycle
					listIterator it3;
					for (int i = 0; i < index - 1; i++)
					{
						it3 = Al.list[arr[i]].begin();
						while ((*it3).node != arr[(i + 1) % index])
						{
							++it3;
						}
						lowest += (*it3).weight;
					}
				}
				else
				{
					//calculating sum of weights of nodes in cycle
					int tempsum = 0;
					listIterator it4;
					for (int i = 0; i < index - 1; i++)
					{
						it4 = Al.list[arr[i]].begin();
						while ((*it4).node != arr[(i + 1) % index])
						{
							++it4;
						}
						tempsum += (*it4).weight;
					}

					//if it is lesser than the lowest sum of weight
					//which we calculated when solnum was 0
					//i.e. first cycle
					//then we copy this cycle into our array which
					//we use for comparison, also to store answer
					if (tempsum < lowest)
					{
						lowest = tempsum;
						for (int i = 0; i < index; i++)
						{
							temp[i] = arr[i];
						}
					}
				}
				arr[--index] = -1;
			}
			//visited[arr[--index]] = false;
			//arr[index] = -1;
		}
	}
	arr[--index] = -1;
	visited[i] = false;
}



