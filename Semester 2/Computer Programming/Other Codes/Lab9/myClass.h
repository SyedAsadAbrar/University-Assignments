#include<ostream>
class myClass
{
private:
	int* arr;
	int currentSize;
public:
	myClass();					//Default Constructor
	myClass(int* arr1);			//Parameterized Constructor with current size as default argument (5)

	myClass(const myClass & obj);			//Copy Constructor

	~myClass();			//Destructor

	myClass operator+(const myClass & obj);
	myClass operator-(const myClass & obj);
	const myClass & operator=(const myClass & obj);
	myClass & operator+(int num);
	myClass & operator-(int num);
	bool operator==(const myClass & obj);
	myClass & operator-();
	friend ostream & operator<<(ostream & output,const myClass & obj);
	friend istream & operator>>(istream & input,myClass & obj);
	void extendBy1();
	int getSize();
	int getData(int num);
};
