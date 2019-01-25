#pragma once
#include<iostream>
using namespace std;

template<class T>
struct Bnode {
	T data;
	Bnode * left;
	Bnode * right;
};
template<class T>
class BST
{
	Bnode<T> * root;
	//For use in search which is a wrapper function
	Bnode<T> * in_search(Bnode<T> * root, T d)
	{
		if (root == nullptr)
		{
			return root;
		}
		else
		{
			if (root->data == d)
			{
				return root;
			}
			if (root->data > d)
			{
				return in_search(root->left, d);
			}
			else
			{
				return in_search(root->right, d);
			}
		}
	}
	//For use in insert which is a wrapper function
	bool in_insert(Bnode<T> * & r, T d)
	{
		Bnode<T> * curr;
		if (r == nullptr)
		{
			curr = new Bnode<T>;
			curr->left = nullptr;
			curr->right = nullptr;
			curr->data = d;
			r = curr;
			return true;
		}
		else if (r->data > d)
		{
			return in_insert(r->left, d);
		}
		else if (r->data < d)
		{
			return in_insert(r->right, d);
		}
		return false;
	}
	//For use in remove which is a wrapper function
	bool in_remove(Bnode<T> * & root, T d)
	{
		if (root == nullptr)
		{
			return false;
		}
		if (root->data == d)
		{
			if (root->left == nullptr && root->right == nullptr)	//when root is leaf
			{
				delete root;
				root = nullptr;
				return true;
			}
			Bnode<T> * temp = root;
			if (root->left == nullptr && root->right != nullptr)
			{
				root = root->right;
				delete temp;
				return true;
			}
			if (root->left != nullptr && root->right == nullptr)
			{
				root = root->left;
				delete temp;
				return true;
			}
			if (root->left != nullptr && root->right != nullptr)
			{
				root->data = removePredecessor(root);
				return true;
			}
		}
		if (root->data < d)
		{
			return in_remove(root->right, d);
		}
		if (root->data > d)
		{
			return in_remove(root->left, d);
		}
	}
	//helper function
	T removePredecessor(Bnode<T> * curr)
	{
		Bnode<T>*par = curr;
		Bnode<T>*child = curr->left;
		while (child->right != nullptr)
		{
			par = child;
			child = child->right;
		}
		T temp = child->data;
		if (child->left == nullptr)
		{
			par->right = nullptr;
		}
		if (child->left != nullptr)
		{
			par->right = child->left;
		}
		return temp;
	}
	//For use in inorder which is a wrapper function
	void printInOrder(Bnode<T> * r)
	{
		if (r != nullptr)
		{
			printInOrder(r->left);
			cout << r->data << " ";
			printInOrder(r->right);
		}
	}
	//For use in preorder which is a wrapper function
	void printPreOrder(Bnode<T> * r)
	{
		if (r != nullptr)
		{
			cout << r->data << " ";
			printPreOrder(r->left);
			printPreOrder(r->right);
		}
	}
	//For use in postorder which is a wrapper function
	void printPostOrder(Bnode<T> * r)
	{
		if (r != nullptr)
		{
			printPostOrder(r->left);
			printPostOrder(r->right);
			cout << r->data << " ";
		}
	}
	//For use in destructor
	void destroy(Bnode<T> * r)
	{
		if (r != nullptr)
		{
			destroy(r->left);
			destroy(r->right);
			delete r;
		}
	}
public:
	BST()
	{
		root = nullptr;
	}
	Bnode<T> * search(T d)
	{
		return in_search(root, d);
	}
	bool insert(T d)
	{
		return in_insert(root, d);
	}
	bool remove(T d)
	{
		return in_remove(root, d);
	}
	void Inorder()
	{
		printInOrder(root);
		cout << endl;
	}
	void Preorder()
	{
		printPreOrder(root);
		cout << endl;
	}
	void Postorder()
	{
		printPostOrder(root);
		cout << endl;
	}
	~BST()
	{
		destroy(root);
	}
};