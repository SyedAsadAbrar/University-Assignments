#include <iostream>
#include "Header.h"
using namespace std;
int main()
{
	AVLTree<int> avl;
	avl.insert(10);
	avl.insert(20);
	avl.insert(4);
	avl.insert(3);
	avl.insert(5);
	avl.insert(8);
	avl.insert(9);
	verticalOrder(avl.retroot());
	system("pause");
	return 0;
}