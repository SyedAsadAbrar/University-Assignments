#include<iostream>
using namespace std;
struct node
{
	int data;
	node * next;
};
class CirList
{
	int n;
	node * head;
public:
	CirList(int num)
	{
		//to add n nodes
		n = num;
		//counter
		int count = 1;
		node * curr = new node;
		curr->data = count;
		count++;
		head = curr;
		for (count; count <= num; count++)
		{
			curr->next = new node;
			curr = curr->next;
			curr->data = count;
		}
		//to make it circular
		curr->next = head;
	}
	void printList()
	{
		//if list is non-empty, print
		if (head->next != nullptr)
		{
			node * curr = head;
			int count = 0;
			while (count < n)
			{
				cout << curr->data << "	";
				curr = curr->next;
				count++;
			}
			cout << endl;
		}
	}
	//param is starting point for traversal
	node* removeNode(node * param, int num)
	{
		node * todelete = param;
		int count = 0;
		node *curr = param;
		while (count < num)
		{
			curr = todelete;
			todelete = todelete->next;
			count++;
		}
		curr->next = todelete->next;
		if (head == todelete)
		{
			head = head->next;
		}
		delete todelete;
		param = curr->next;
		n--;
		return param;
	}
	~CirList()
	{
		node * curr = head;
		node * todelete = head;
		int count = 0;
		while (count < n)
		{
			todelete = curr->next;
			delete curr;
			curr = todelete;
			count++;
		}
	}
	node * getHead()
	{
		return head;
	}
};