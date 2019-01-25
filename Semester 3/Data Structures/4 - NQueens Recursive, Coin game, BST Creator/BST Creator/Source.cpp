#include<iostream>
#include"Header.h"
using namespace std;
int main()
{
	Bnode<int> * temp;
	cout << "BST Creator" << endl;
	BST<int> a;
	cout << bool(a.insert(60)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.insert(3)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.insert(5)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.insert(23)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.insert(10)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.insert(25)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.insert(32)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.insert(50)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.insert(59)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.insert(43)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	temp = a.search(43);
	if (temp != nullptr)
	{
		cout << "43 is found." << endl;
	}
	else
	{
		cout << "43 is not found" << endl;
	}
	cout << bool(a.insert(59)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.remove(59)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	cout << bool(a.remove(59)) << endl;
	a.Preorder();
	a.Inorder();
	a.Postorder();
	system("pause");
	return 0;
}