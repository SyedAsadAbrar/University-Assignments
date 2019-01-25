#include<iostream>
#include<math.h>
using namespace std;

class Set 
{
	int * arr;			//Pointer to the array of integers
	int size;			//current number of elements
public:
	//default constructor
	Set()
	{
		size = 0;
		arr = nullptr;
	}
	//Copy constructor
	Set(const Set & s)
	{
		size = s.size;
		arr = new int[size];
		for (int i = 0; i < size; i++)
		{
			arr[i] = s.arr[i];
		}
	}
	void print()
	{
		cout << "{";
		for (int i = 0; i < size; i++)
		{
			cout << arr[i];
			//So that comma isn't printed after last element
			if (size - i > 1)
			{
				cout << ", ";
			}
		}
		cout << "}"<<endl;
	}
	void add(int x)
	{
		//Allocation of more memory
		int index = size;
		int * temp = new int[++size];
		//If size=0, then there is no need to copy
		//as there exists no element in set
		//as size is incremented so condition is size>1
		//instead of size>0
		if (size > 1)
		{
			for (int i = 0; i < size; i++)
			{
				temp[i] = arr[i];
			}
			//deleting old arr
			delete[]arr;
		}
		//assigning value
		temp[index] = x;
		//assigning adress of new array to pointer
		arr = temp;
	}
	void remove()
	{
		//New memory is allocated of size-1
		int* temp = new int[--size];
		//Copying elements of set till second-last element
		//essentially removing it
		for (int i = 0; i < size; i++)
		{
			temp[i] = arr[i];
		}
		//deleting old arr
		delete[]arr;
		//assigning adress of new array to pointer
		arr = temp;
	}
	int getLast()
	{
		return arr[size-1];
	}
	~Set()
	{
		//if array exists
		if (arr != nullptr)
		{
			delete[] arr;
			size = 0;
		}
	}
	//assignment operator overload
	Set & operator =(const Set & s)
	{
		size = s.size;
		arr = new int[size];
		for (int i = 0; i < size; i++)
		{
			arr[i] = s.arr[i];
		}
		return *this;
	}
};

template<class T>
class Queue
{
	T * q;
	int start, end;
	int maxsize, size;
public:
	//default and parameterized constructor
	Queue(int s = 10)
	{
		q = new T[s];
		maxsize = s;
		size = 0;
		start = -1;
		end = 0;
	}
	bool isEmpty()
	{
		if (size == 0)
			return true;
		else
			return false;
	}
	bool isFull()
	{
		//true if size=maxsize i.e full
		return size == maxsize;
	}
	bool dequeue(T & d)
	{
		if (!isEmpty())
		{
			start++;
			if (start == maxsize)
				start = 0;
			d = q[start];
			size--;
			return true;
		}
		else
			return false;
	}
	bool enqueue(T & d)
	{
		if (!isFull())
		{
			q[end] = d;
			end++;
			if (end == maxsize)
			{
				end = 0;
			}
			size++;
			return true;
		}
		else
			return false;
	}
	//destructor
	~Queue()
	{
		start = end = 0;
		size = 0;
		delete[]q;
	}
	//Following Rule of 3
	//copy constructor
	Queue(Queue<T> & temp)
	{
		start = temp.start;
		end = temp.end;
		maxsize = temp.maxsize;
		size = temp.size;
		q = new T[maxsize];
		for (int i = 0; i < size; i++)
		{
			q[i] = temp.q[i];
		}
	}
	//assignment operator
	Queue & operator =(const Queue<T> & temp)
	{
		start = temp.start;
		end = temp.start;
		maxsize = temp.maxsize;
		size = temp.size;
		delete[]q;
		q = new T[maxsize];
		for (int i = 0; i < size; i++)
		{
			q[i] = temp.q[i];
		}
		return *this;
	}
};

void allSubset(int n)
{
	//Because subsets are 2^n, as we're not considering null subsets so 2^n-1
	Queue<Set> uSet(int(pow(2.0, n) - 1));
	Set s;
	Set temp;
	Set r;

	//Step 1 of algorithm
	temp.add(1);
	uSet.enqueue(temp);

	//For better UX
	cout << "The subsets for the set {";
	for (int i = n; i > 0; i--)
	{
		cout << n - i + 1;
		if (n - i < n - 1)
		{
			cout << ", ";
		}
	}
	cout << "} are:" << endl;

	//Algorithm
	while (uSet.isEmpty() != true)
	{
		uSet.dequeue(s);
		s.print();
		if (s.getLast() < n)
		{
			r = s;
			r.remove();
			r.add(s.getLast() + 1);
			uSet.enqueue(r);
			s.add(s.getLast() + 1);
			uSet.enqueue(s);
		}
	}
}