class Bag{
	int el[9];
	int size;
	int cSize;
	char bagName[30];
public:
	Bag();
	//take array of size 9 and initliatze elements randomly and name is NULL,

	void setName(char * name);
	//sets bagName with the value of name
	
	char * getBagName();
	//returns the char[] of bagName if exists else NULL

	void show();
	//prints elements in single line separated by spaces
	void add(int);
	//add element, if cSize<size, otherwise first call

	int indexOf(int);
	//search element from start, return index if found,
	//otherwise return -1[mean element not found]

	int indexOf(int, int);
	//first parameter is element ot search, second element
	//is starting position, here start from given position
	//instead of 0, return index if found, otherwise return -1

	void remove(int);
	//remove element

	void remove(int, int);
	//first parameter is element, second element is starting 
	//position, search from starting position and remove

	int elementAt(int);
	//if index is less than cSize return return element otherwise -1

	int *elements(int, int);
	//first parameter, is start, second is end. If start>=end
	//return null, if end>cSuze set end to cSize, take a 
	//dynamic arry of size end-start.Copy elements from
	//start toend-1/into new array. FInally return array

	bool isEqual(Bag b)
	//compare current/ calling object to passsing object
	//first compare cSize, if equal, compare corresponding
	//elements and return true or false accordingly
};

