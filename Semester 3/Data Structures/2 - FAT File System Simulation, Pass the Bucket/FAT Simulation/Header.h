#include<iostream>
#include<string>
#include<cmath>
using namespace std;

template <class type>
class node
{
public:
	type data;	
	node * next;					//Pointer to next node
	node()
	{
		next = nullptr;
	}
	node(node & nd)
	{
		data = nd.data;
		next = nd.next;
	}
};

class block
{
	int startSector;
	int endSector;
public:
	//Helper function for savefile function of filesystems
	int getSize()
	{
		return (endSector - startSector + 1);
	}
	int getStart()
	{
		return startSector;
	}
	int getEnd()
	{
		return endSector;
	}
	//Setter Functions
	void setStart(int size)
	{
		startSector = size;
	}
	void setEnd(int size)
	{
		endSector = size;
	}
	//To adjust block size
	void changeStart(int num)
	{
		startSector += num;
	}
	void changeEnd(int num)
	{
		startSector += num;
	}
};

template <class type>
class listIterator
{
	node<type> * current;
public:
	//default constructor
	listIterator<type>()
	{
		current = nullptr;
	}
	//overloaded constructor
	listIterator<type>(node<type> * parameter)
	{
		//points listIterator to node pointed by pointer 'parameter'
		current = parameter;
	}
	listIterator<type> & operator++()
	{
		//checking if current pointer doesn't point to last node
		if (current != nullptr)
		{
			//shifting current pointer to next node of node pointed by current
			current = current->next;
		}
		//returning the same listIterator object
		return *this;
	}
	type & operator*()
	{
		//returns data of current node if current points to a node and not to NULL
		if (current != nullptr)
		{
			return current->data;
		}
		else
		{
			//error handling
			cout << "Data not found." << endl;
		}
	}
	bool operator!=(listIterator<type> & lit)
	{
		//returns true if both are different, false otherwise
		return !(current == lit.current);
	}
	bool nextNodeExists()
	{
		if (current->next != nullptr)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	void removeNode()
	{
		node<type> * temp = current->next;
		current->next = temp->next;
		delete temp;
	}
	void insertNode(node<type> * & param, type & d)
	{
		param = new node<type>;
		param->data = d;
		param->next = current->next;
		current->next = param;
	}
	node<type>* getNode()	//make protected
	{
		return current;
	}
	~listIterator()
	{
		current = nullptr;
	}
};

template <class type>
class LinkedList 
{
	node<type> * head;
public:
	//Constructor
	LinkedList()
	{
		//Pointing head to NULL
		head = nullptr;
	}
	//Destructor
	~LinkedList()
	{
		//i.e. that list is non-empty
		if (head != nullptr)					
		{
			//To avoid it from becoming a dangling pointer
			node<type> * curr = head;

			//Loop-terminating condition, tells us that the list has finished
			while (curr!= nullptr)				
			{
				//to traverse through the list, this also avoids head becoming a dangling pointer at the end
				head = curr->next;	

				//deleting node pointed by curr
				delete curr;

				//pointing curr to head
				curr = head;
			}
		}
	}
	listIterator<type> & begin()
	{
		//Creating a temporary listIterator object which points to head node
		listIterator<type> temp(head);

		//Returning the listIterator object
		return temp;
	}
	listIterator<type> & end()
	{
		//Creating a temporary listIterator object, since default constructor is called so it points to NULL
		listIterator<type> temp;

		//Returning the listIterator object
		return temp;
	}
	void insert(listIterator<type> it, type & d)
	{
		listIterator<type> temp = it;
		//To confirm list is non-empty and that current node exists
		if (head != nullptr && temp!=(*this).end())		
		{
			node<type> * insertedNode;
			temp.insertNode(insertedNode, d);
			
			/*//Creation of new node on heap
			//node<type> * insertedNode = new node<type>;

			//assigning value d to data of new node
			insertedNode->data = d;

			//pointing next pointer to the node pointed by next pointer of listIterator
			++temp;
			insertedNode->next = temp.getNode();

			//pointing next pointer of listIterator to inserted node
			it + 1 = insertedNode;*/
		}
	}
	type remove(listIterator<type> it)
	{
		//CHECK COMMENTS
		//To confirm list is non-empty and that next node exists
		if (head != nullptr && it.nextNodeExists())
		{
			//creating a temporary listIterator and pointing it to node next to node pointed by listIterator it
			listIterator<type> temp = it;
			++temp;
			
			//creating a temporary type variable and assigning value of node next to the node pointed by listIterator it
			type tempd = (*temp);

			//deleting temp node(node next of node pointed by listIterator it)
			it.removeNode();

			//returning value of temp
			return tempd;
		}
		else
		{
			cout << "Error." << endl;
		}
	}
	void insertAtStart(type & d)
	{
		listIterator<type> temp = begin();
		//To confirm list is non-empty and that current node exists
		if (head != nullptr && temp != (*this).end())
		{
			node<type> * insertedNode;
			temp.insertNode(insertedNode,d);
		}
	}
	type removeFromStart()
	{
		return remove(begin());
	}
	bool isEmpty()
	{
		//returns true if list is empty, otherwise false
		return (head == nullptr);
	}
};

class file
{
	string name;
	int size;
	LinkedList <block> list;
public:
	file()
	{
		size = 0;
	}
	file(string n, int s)
	{
		name = n;
		size = s;
	}
	//helper function for savefile function for filesystems
	string getName()
	{
		return name;
	}
	int getSize()
	{
		return size;
	}
	//To insert at end
	void insertAtEnd(block d, listIterator <block> curr)
	{
		list.insert(curr,d);
	}
	//Helper function in savefile
	int getSizeAtEnd()
	{
		int counter = 0;
		listIterator <block> temp = list.begin();
		while (counter<=((*temp).getSize()))
		{
			++temp;
			counter++;
		}
		return counter;
	}
	//For deleting purposes
	listIterator<block> begin()
	{
		return list.begin();
	}
	~file()
	{
		listIterator<block>temp = list.begin();
		block temp1;
		while (temp!=list.end())
		{
			temp1 = list.removeFromStart();
			++temp;
		}
	}
	void outputFile()
	{
		cout << "The name of file is: " << name << endl;
		cout << "Size: " << size << endl;
		cout << "File contents" << endl;
		listIterator<block> temp = list.begin();
		int it = 1;
		while (temp!=list.end())
		{
			cout << "Sector " << it << endl;
			cout << (*temp).getStart() << endl << (*temp).getEnd() << endl;
			it++;
			++temp;
		}
	}
};

class fileSystem
{
	LinkedList <block> pool;
	LinkedList <file> files;
	char * disk;
	int numOfSectors;
	int sizeOfSectors;
public:
	fileSystem(int num, int size)
	{
		//files is empty
		//pool is of one block having sectors 1 to numOfSectors
		//disk size = numOfSectors*sizeOfSectors
		disk = new char[numOfSectors*sizeOfSectors];

		//assigning values to data members of filesystem
		numOfSectors = num;
		sizeOfSectors = size;

		//creating temporary block with start sector 1 and end sector numOfSectors
		block temp;
		temp.setStart(1);
		temp.setEnd(numOfSectors);

		//inserting that block into pool
		pool.insertAtStart(temp);
	}
	bool saveFile(string fname, int fsize, char * fcontent)
	{
		//to compare collective size of blocks in pool to fsize
		int csize=0;

		//temporary listIterator to traverse pool
		listIterator <block> ptemp;
		ptemp = pool.begin();

		//temporary listIterator to traverse files list

		listIterator <file> ftemp;
		ftemp = files.begin();

		while (ftemp!= files.end())
		{
			//if fname matches with name of any saved file, then return false.
			if (fname == (*ftemp).getName())
			{	
				return false;
			}
			//incrementing listIterator object to next node
			++ftemp;
		}
		
		//to calculate size of pool
		while (ptemp!=pool.end())
		{
			csize = csize + (*ptemp).getSize();
			++ptemp;
		}
		
		//if collective size of all blocks in the pool is less than fsize
		//fsize is in bytes
		if (ceil(fsize/numOfSectors) > csize)
		{	
			return false;
		}
		
		else
		{
			//inserting newfile and making assignments
			file tempfile(fname, fsize);
			files.insertAtStart(tempfile);

			//listiterator ptemp starts from the first block of pool to traverse pool
			ptemp = pool.begin();

			//temporary block variable
			block btemp;

			while (ptemp != pool.end())
			{
				if ((*ptemp).getSize() < ceil(fsize/sizeOfSectors))
				{
					btemp = (*ptemp);
					pool.remove(ptemp);
					tempfile.insertAtEnd(btemp,ptemp);
					fsize = fsize - (btemp.getSize()*sizeOfSectors);
					++ptemp;
				}
				else if ((*ptemp).getSize() == ceil(fsize / sizeOfSectors))
				{
					btemp = (*ptemp);
					pool.remove(ptemp);
					tempfile.insertAtEnd(btemp, ptemp);
					break;
				}
				else
				{
					btemp.setStart(tempfile.getSizeAtEnd()+1);
					int end = tempfile.getSizeAtEnd() + tempfile.getSize() + 1;
					btemp.setEnd(end);
					pool.remove(ptemp);
					tempfile.insertAtEnd(btemp, ptemp);
					ptemp = pool.begin();
					int j = btemp.getStart()-1;
					while (ptemp != pool.end())
					{
						(*ptemp).changeStart(end);
						(*ptemp).changeEnd(end);
					}
					for (int i = 0; i < (tempfile.getSize()); i++)
					{
						disk[j] = fcontent[i];
						j++;
					}
					return true;
				}
			}
		}
	}
	void deleteFile(string fname)
	{
		listIterator <file> temp = files.begin();
		//actual file which is to be deleted will be pointed by this iterator
		listIterator <file> temp2 = files.begin();
		//helping bool variable
		bool found = false;
		while (temp!=files.end())
		{
			if (fname != ((*temp).getName()))
			{
				++temp;
			}
			else
			{
				found = true;
				listIterator <block> btemp = (*temp).begin();
				while (btemp!=pool.end())
				{
					pool.insertAtStart((*btemp));
					++btemp;
				}
				files.remove(temp2);
				break;
			}
			if (found == true)
			{
				++temp2;
				found = false;
			}
		}
	}
	void readFile(string fname)
	{
		listIterator <file> temp = files.begin();
		bool found = false;
		while (temp != files.end())
		{
			if (fname != ((*temp).getName()))
			{
				++temp;
			}
			else
			{
				(*temp).outputFile();
				found = true;
			}
		}
		if (found == false)
		{
			cout << "File not found" << endl;
		}
	}
	~fileSystem()
	{
		delete disk;
	}
};