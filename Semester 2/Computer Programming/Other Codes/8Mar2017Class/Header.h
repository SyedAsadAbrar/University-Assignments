class Set{
private:
	int * arr;
	int size;
public:
	Set(); //default constructor
	Set(char * str,int n); // constructor with parameters or parameterize constructor
	Set(int * ptr, int n);
	Set(const Set &obj);
	Set operator+(const Set & obj);
	bool operator==(const Set & obj);
	~Set(); //destructor, the "~" sign is important. It is automatically called when the scope of the constructor ends.
	const Set & operator=(const Set &obj);
	int getSize();
	int & operator[](int i);
};