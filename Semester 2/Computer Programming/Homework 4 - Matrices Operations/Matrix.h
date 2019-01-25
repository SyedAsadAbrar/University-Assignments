class Matrix
{
private:
	int rows, cols;
	int **mat;
public:
	Matrix();
	Matrix(const Matrix & m);	//copy constructor
	Matrix(char * file);
	Matrix(int rowno, int colno);
	~Matrix();
	/*const */Matrix & operator=(const Matrix & m);
	Matrix operator+(const Matrix & m);
	Matrix operator-(const Matrix & m);	//subtraction
	bool operator==(const Matrix & m);
	Matrix operator*(const Matrix & m);
	Matrix & transpose();
	Matrix operator-();	//negation operator
	Matrix operator +=(int num);
	Matrix operator *=(int num);
	int getNumRows();
	int getNumCols();
	int* & operator[](int i);
};