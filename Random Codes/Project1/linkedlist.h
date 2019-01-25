#include<iostream>
using namespace std;
template <class type>
struct node
{
	type data;
	node*next;
};

template<class type>
class listiterator
{
	node<type>*curr;
public:
	listiterator(node<type>*n = nullptr)
	{
		curr = n;
	}

	listiterator operator++()
	{
		if (curr != nullptr)
		{
			curr = curr->next;
		}

		return *this;
	}

	int& operator *()
	{
		return curr->data;
	}

	bool operator!=(listiterator& lit)
	{
		return !(curr == lit.curr);
	}
};

template<class type>
class linked_list
{
	node<type>*head;
public:
	linked_list()
	{
		head = nullptr;
	}

	void insertatstart(type d)
	{
		node*temp = new node;
		temp->data = d;
		temp->next = head;
		head = temp;
	}

	void insertatend(int d)
	{
		node*temp = new node;
		temp->data = d;
		temp->next = nullptr;
		node*curr = head;
		if (head == nullptr)
		{
			head = temp;
			return;
		}
		while (curr->next != nullptr)
		{
			curr = curr->next;
		}
		curr->next = temp;
	}

	void removefromstart(int d)
	{
		if (head != nullptr)
		{
			node*temp = head->next;
			delete head;
			head = temp;
		}
	}

	void removefromend(int d)
	{
		if (head != nullptr)
		{
			node*curr = head;
			if (head->next == nullptr)
			{
				delete head;
				head = nullptr;
			}
		}
		else
		{
			while (curr->next->next != nullptr)
			{
				curr = curr->next;
			}
			delete curr->next;
			curr->next = nullptr;
		}
	}

	void printlist(linked_list ax)
	{
		node*curr = ax.head;
		while (curr->next != nullptr)
		{
			cout << curr->data;
			if (curr->next != nullptr)
			{
				cout << " , ";
			}
			curr = curr->next;
		}
		cout << endl;
	}

	void removeafter(int d) //removes the node after the current node if its data is d
	{
		node*curr = head;
		while(curr != nullptr && curr->next->data != d)
		{
			curr = curr->next;
		}

		if(curr->next->data == d)
		{
			node*temp = curr->next->next;
			delete curr->next;
			curr->next = temp;
		}
	}

	listiterator<type> begin()
	{
		listiterator<type> lit(head);
		return lit;
	}

	listiterator<type> end()
	{
		listiterator<type> lit;
		return lit;
	}
};


