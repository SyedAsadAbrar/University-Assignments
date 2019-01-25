#pragma once
#include <iostream>
#include <algorithm> 
using namespace std;

template<class T>
class MyStack
{
	T * s;						//Pointer to stack array
	int size;					//Size of stack
	int stkptr;					//First index to place object
public:
	MyStack(int n = 20)			//Making a stack of size n, if n is not specified make stack of size 20
	{
		size = n;
		s = new T[n];
		stkptr = -1;
	}
	MyStack(const MyStack<T> & temp)
	{
		size = temp.size;
		stkptr = temp.stkptr;
		s = new T[size];
		for (int i = 0; i <= stkptr; i++)
		{
			s[i] = temp.s[i];
		}
	}
	bool isEmpty()
	{
		if (stkptr == -1)
			return true;
		else
			return false;
	}
	bool isFull()
	{
		if (stkptr == (size - 1))
			return true;
		else
			return false;
	}
	bool push(T & d)				//push data element d in the stack
	{
		if (!isFull())
		{
			s[++stkptr] = d;
			return true;
		}
		else
			return false;
	}
	bool pop(T &d)				//Pop the element from stack and put it in d
	{
		if (!isEmpty())
		{
			d = s[stkptr--];
			return true;
		}
		else
			return false;
	}
	bool top(T & d)				//Get the top of the stack element and place it in d
	{
		if (!isEmpty())
		{
			d = s[stkptr];
			return true;
		}
		else
			return false;
	}
	~MyStack()
	{
		size = 0;
		stkptr = 0;
		delete[]s;
	}
};

template <class T>
struct AVLnode
{
	T data;
	AVLnode * left;
	AVLnode * right;
	int height;
};
template <class T>
int geth(AVLnode<T> * n)
{
	if (n == nullptr)
		return 0;
	else
		return n->height;
}
template <class T>
int getbf(AVLnode<T> * n)
{
	if (n == nullptr)
	{
		return 0;
	}
	return geth(n->left) - geth(n->right);
}
template <class T>
void rotatetoright(AVLnode<T> * & x)
{
	AVLnode<T> * y = x->left;
	x->left = y->right;
	y->right = x;
	x->height = 1 + max(geth(x->left), geth(x->right));
	y->height = 1 + max(geth(y->left), geth(y->right));
	x = y;
}
template <class T>
void rotatetoleft(AVLnode<T> * & x)
{
	AVLnode<T> * y = x->right;
	x->right = y->left;
	y->left = x;
	x->height = 1 + max(geth(x->left), geth(x->right));
	y->height = 1 + max(geth(y->left), geth(y->right));
	x = y;
}
template <class T>
void Drotatetoright(AVLnode<T> * & x)
{
	rotatetoleft(x->left);
	rotatetoright(x);
}
template <class T>
void Drotatetoleft(AVLnode<T> * & x)
{
	rotatetoright(x->right);
	rotatetoleft(x);
}
template <class T>
class AVLTree
{
	AVLnode<T> * root;
	T removePredecessor(AVLnode<T> * & curr)
	{
		MyStack<AVLnode<T> *> a(curr->height);
		AVLnode<T> * temp2 = curr;;
		AVLnode<T>*par = curr;
		AVLnode<T>*child = curr->left;
		a.push(par);
		while (child->right != nullptr)
		{
			par = child;
			child = child->right;
			a.push(par);
		}
		T temp = child->data;
		if (par != curr)
		{
			par->right = child->left;
		}
		else
		{
			par->left = child->left;
		}
		delete child;
		while (!a.isEmpty())
		{
			a.pop(temp2);
			temp2->height = max(geth(temp2->left), geth(temp2->right)) + 1;
		}
		curr = temp2;
		return temp;
	}
public:
	AVLTree()
	{
		root = nullptr;
	}
	AVLnode<T> * Search(T d)
	{
		AVLnode<T> * curr = root;
		while (curr != nullptr)
		{
			if (curr->data == d)
			{
				return curr;
			}
			else if (curr->data > d)
			{
				curr = curr->left;
			}
			else
			{
				curr = curr->right;
			}
		}
		return curr;
	}
	bool insert(T d)
	{
		int h;
		//creation of stack
		if (root == nullptr)
		{
			h = 0;
		}
		else
		{
			h = root->height;
		}
		MyStack<AVLnode<T> *> a(h + 1);

		//insertion of node
		AVLnode<T> * temp = new AVLnode<T>;
		temp->data = d;
		temp->left = temp->right = nullptr;
		temp->height = 1;	//assuming height of inserted node which would be a leaf node is 1
		if (root == nullptr)
		{
			root = temp;
			return true;
		}
		AVLnode<T> * curr = root;
		AVLnode<T> * par = root;
		while (curr != nullptr)
		{
			a.push(curr);
			par = curr;
			if (curr->data > d)
			{
				curr = curr->left;
			}
			else if (curr->data < d)
			{
				curr = curr->right;
			}
			else
			{
				return false;
			}
			if (curr == nullptr && par->left == nullptr && par->right == nullptr)
			{
				par->height += 1;
			}
		}
		if (par->data > d)
		{
			par->left = temp;
		}
		else
		{
			par->right = temp;
		}
		
		//updating heights
		MyStack<AVLnode<T> *> b(a);
		AVLnode<T> * temp2;

		while (!b.isEmpty())
		{
			b.pop(temp2);
			temp2->height = max(geth(temp2->left), geth(temp2->right)) + 1;
		}

		//balance tree
		while (!a.isEmpty())
		{
			a.pop(temp2);
			if (getbf(temp2) == 2)
			{
				if (temp2->left->data > d)
				{
					rotatetoright(temp2);
				}
				else
				{
					Drotatetoright(temp2);
				}
			}
			else if (getbf(temp2) == -2)
			{
				if (temp2->right->data < d)
				{
					rotatetoleft(temp2);
				}
				else
				{
					Drotatetoleft(temp2);
				}
			}
			root = temp2;
		}
		return true;
	}
	//For use in remove which is a wrapper function
	bool remove(T d)
	{
		bool res = false;
		int h;
		//creation of stack
		if (root == nullptr)
		{
			return false;	//tree doesn't exist
		}
		else
		{
			h = root->height;
		}
		MyStack<AVLnode<T> *> a(h);

		//temp node pointer to traverse
		AVLnode<T> * curr = root;
		AVLnode<T> * par = root;
		while (curr != nullptr)
		{
			a.push(curr);
			par = curr;
			if (curr->data > d)
			{
				curr = curr->left;
			}
			else if (curr->data < d)
			{
				curr = curr->right;
			}
			if (curr->data == d)
			{
				break;
			}
		}
		if (curr->data != d)
		{
			return false;
		}
		else
		{
			if (curr->left == nullptr && curr->right == nullptr)
			{
				if (curr->data > par->data)
				{
					par->right = nullptr;
				}
				else
				{
					par->left = nullptr;
				}
				delete curr;
				curr = nullptr;
				res = true;
			}
			else if (curr->left == nullptr && curr->right != nullptr)
			{
				if (curr->data > par->data)
				{
					par->right = curr->right;
				}
				else
				{
					par->left = curr->right;
				}
				delete curr;
				curr = nullptr;
				res = true;
			}
			else if (curr->left != nullptr && curr->right == nullptr)
			{
				if (curr->data > par->data)
				{
					par->right = curr->left;
				}
				else
				{
					par->left = curr->left;
				}
				delete curr;
				curr = nullptr;
				res = true;
			}
			else if (curr->left != nullptr && curr->right != nullptr)
			{
				curr->data = removePredecessor(curr);
				res = true;
			}
		}

		//updating heights
		MyStack<AVLnode<T> *> b(a);
		AVLnode<T> * temp2;

		while (!b.isEmpty())
		{
			b.pop(temp2);
			temp2->height = max(geth(temp2->left), geth(temp2->right)) + 1;
		}

		//balance tree
		/*while (!a.isEmpty())
		{
			a.pop(temp2);
			curr = root;
			while (curr != nullptr)
			{
				if (temp2->data > curr->data)
				{
					curr = curr->right;
				}
				else if (temp2->data < curr->data)
				{
					curr = curr->left;
				}
				else
				{
					break;
				}
			}
			if (getbf(temp2) == 2)
			{
				if (getbf(temp2->left) >= 0)
				{
					rotatetoright(curr);
				}
				else
				{
					Drotatetoright(curr);
				}
			}
			else if (getbf(temp2) == -2)
			{
				if (getbf(temp2->right) <= 0)
				{
					rotatetoleft(curr);
				}
				else
				{
					Drotatetoleft(curr);
				}
			}
		}*/
		return res;
	}	
	~AVLTree()
	{
		MyStack<AVLnode<T> *> a(geth(root));
		AVLnode<T> * temp = root;
		AVLnode<T> * todelete;
		a.push(temp);
		/*bool completed = 0;*/

		while (!a.isEmpty())
		{
			if (temp == nullptr)
			{
				a.pop(temp);
				todelete = temp;
				temp = temp->right;
				if (temp != nullptr)
				{
					a.push(temp);
				}
				delete todelete;
			}
			else
			{
				temp = temp->left;
				if (temp != nullptr)
				{
					a.push(temp);
				}
			}
		}
		/*while (!completed)
		{
			if (temp != nullptr)
			{
				a.push(temp);
				temp = temp->left;
			}
			else
			{
				if (!a.isEmpty())
				{
					a.pop(temp);
					todelete = temp;
					temp = temp->right;
					delete todelete;
				}
				else
				{
					completed = 1;
				}
			}
		}*/
	}
	//helper function
	AVLnode<T> * retroot()
	{
		return root;
	}
};

//----------------------------------------------------------------------------------------------------------------------------
//NOTE: THE FOLLOWING CODE IS TAKEN FROM THE INTERNET AND MINOR CHANGES WERE MADE, THIS WASN'T A REQUIREMENT IN THE ASSIGNMENT BUT WAS ONLY USED FOR THE PURPOSE OF CHECKING IF THE CODE WORKED PROPERLY.
//----------------------------------------------------------------------------------------------------------------------------


// A utility function to find min and max distances with respect
// to root.
template<class T>
void findMinMax(AVLnode<T> * node, int *min, int *max, int hd)
{
	// Base case
	if (node == NULL) return;

	// Update min and max
	if (hd < *min)  *min = hd;
	else if (hd > *max) *max = hd;

	// Recur for left and right subtrees
	findMinMax(node->left, min, max, hd - 1);
	findMinMax(node->right, min, max, hd + 1);
}

// A utility function to print all nodes on a given line_no.
// hd is horizontal distance of current node with respect to root.
template<class T>
void printVerticalLine(AVLnode<T> * node, int line_no, int hd)
{
	// Base case
	if (node == NULL) return;

	// If this node is on the given line number
	if (hd == line_no)
		cout << node->data << " ";

	// Recur for left and right subtrees
	printVerticalLine(node->left, line_no, hd - 1);
	printVerticalLine(node->right, line_no, hd + 1);
}

// The main function that prints a given binary tree in
// vertical order
template<class T>
void verticalOrder(AVLnode<T> * root)
{
	// Find min and max distances with resepect to root
	int min = 0, max = 0;
	findMinMax(root, &min, &max, 0);

	// Iterate through all possible vertical lines starting
	// from the leftmost line and print nodes line by line
	for (int line_no = min; line_no <= max; line_no++)
	{
		printVerticalLine(root, line_no, 0);
		cout << endl;
	}
}
