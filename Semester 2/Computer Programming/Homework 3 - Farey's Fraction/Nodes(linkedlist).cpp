#include<iostream>
#include<fstream>
using namespace std;
struct node
{
	node * next;
	int data;
};
void add_to_list(int x, node * &head, node * &tail)
{
	if (head==nullptr)  // if the list is empty
	{
		tail = head = new node;
		head->data = x;                           //list with one node
		head->next = nullptr;
	}
	else  // if the list has nodes
	{
		node * temp = new node;
		temp->data = x;
		temp->next = nullptr;
		tail->next = temp;
		tail = temp;
	}
}
void remove_from_list(node * &head, node * &tail)
{
	if (head==nullptr)
	{
		cout << "EMPTY" << endl; // karna hai abhi
	}
	else if (head==tail)
	{


	}

}
void print_list(node * head) // can't use head[i] or something like that as the addresses are not contiguous
{
	if (head != nullptr)
	{
		node * current = head;
		while (current!=nullptr)
		{
			cout << current->data<<"  ";
			current = current->next;
		}
	}
	else
	{
		cout << "ERROR" << endl;
	}
}
int main()  //jumping of pointers without the copying of data
{
	node * head = nullptr;  //empty list
	node * tail = nullptr;  //empty list
	ifstream read("nums.txt");
	int a=0;
	if (read.is_open())
	{
		while (read >> a)
		{
			add_to_list(a, head, tail);
			print_list(head);
		}
	}
	else
		cout << "ERROR" << endl;
	print_list(head);

	system("pause");
	return 0;
}