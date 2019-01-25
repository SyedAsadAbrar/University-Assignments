#ifdef _MSC_VER
#define _CRT_SECURE_NO_WARNINGS
#endif
#include<iostream>
#include"Matrix.h"
#include<fstream>
#include<cstring>
#include<string.h>
#include<stdlib.h>
#include<time.h>
using namespace std;
Matrix::Matrix()
{
	rows=0;
	cols=0;
	mat=nullptr;
}
Matrix::Matrix(const Matrix & m)
{
	/*cout<<"Copy constructor called"<<endl;*/
	mat=nullptr;
	mat=new int*[m.rows];
	rows=m.rows;
	cols=m.cols;
	for (int i=0;i<rows;i++)
	{
		mat[i]=new int[cols];
	}
	for (int i=0;i<rows;i++)
	{
		for (int j=0;j<cols;j++)
		{
			mat[i][j]=m.mat[i][j];
		}
	}
}
Matrix::Matrix(char * file)
{
	char buff[500];
	ifstream fin (file);
	if (fin.is_open())
	{
		fin>>rows;
		fin.getline(buff,500);
		fin>>cols;
		fin.getline(buff,500);
		mat=new int*[rows];
		for (int i=0;i<rows;i++)
		{
			mat[i]=new int[cols];
		}
		fin.getline(buff,500);
		char*token=strtok(buff,",");
		while (token!=nullptr)
		{
			for(int i=0;i<rows;i++)
			{
				for (int j=0;j<cols;j++)
				{
					mat[i][j]=atoi(token);
					token=strtok(NULL,",");
				}
				fin.getline(buff,500);
				token=strtok(buff,",");
			}
		}
		fin.close();
	}
	else
	{
		cout<<"Error in reading file "<<file<<endl;
	}
}
Matrix::Matrix(int rowno, int colno)
{
	srand(time(NULL));
	int random = rand() % 10 + 1;
	rows=rowno;
	cols=colno;
	mat=new int*[rows];
	for (int i=0;i<rows;i++)
	{
		mat[i]=new int[cols];
	}
	for(int i=0;i<rows;i++)
	{
		for (int j=0;j<cols;j++)
		{
			mat[i][j]=random;
			random = rand() % 10 + 1;
		}
	}
}
int Matrix::getNumRows()
{
	return rows;
}
int Matrix::getNumCols()
{
	return cols;
}
Matrix::~Matrix()
{
	if (mat!=nullptr)
	{
		/*cout<<"Destructor called."<<endl;*/
		for (int i=0;i<rows;i++)
		{
			delete [] mat[i];
		}
		delete [] mat;
	}
}
/*const */Matrix & Matrix::operator=(const Matrix & m)
{
	if (mat!=nullptr)
	{
		for (int i=0;i<rows;i++)
		{
			delete []mat[i];
		}
		delete []mat;
	}
	mat=new int*[m.rows];
	rows=m.rows;
	cols=m.cols;
	for (int i=0;i<rows;i++)
	{
		mat[i]=new int[cols];
	}
	for (int i=0;i<rows;i++)
	{
		for (int j=0;j<cols;j++)
		{
			mat[i][j]=m.mat[i][j];
		}
	}
	return *this;	//pointer to object
}
Matrix Matrix::operator+(const Matrix & m)	//assuming both matrices have same number of rows and columns
{
	Matrix a(rows,cols);
	a.rows=rows;
	a.cols=cols;
	for (int i=0;i<rows;i++)
	{
		for (int j=0;j<cols;j++)
		{
			a.mat[i][j]=m.mat[i][j]+mat[i][j];
		}
	}
	return a;
}
Matrix Matrix::operator-(const Matrix & m)	//subtraction
{
	Matrix a(rows,cols);
	a.rows=rows;
	a.cols=cols;
	for (int i=0;i<rows;i++)
	{
		for (int j=0;j<cols;j++)
		{
			a.mat[i][j]=mat[i][j]-m.mat[i][j];
		}
	}
	return a;
}
bool Matrix::operator==(const Matrix & m)
{
	if ((rows!=m.rows) || (cols!=m.cols))
	{
		return false;
	}
	else
	{
		for (int i=0;i<rows;i++)
		{
			for (int j=0;j<cols;j++)
			{
				if (mat[i][j]!=m.mat[i][j])
				{
					return false;
				}
			}
		}
		return true;
	}
}
int* & Matrix::operator[](int i)
{
	return mat[i];
}
Matrix Matrix::operator*(const Matrix & m)
{
	int temp=0;
	int iteratorcols=0;
	int iteratorrows=0;
	int num;
	int numcount=0;
	Matrix a(rows,m.cols);
	a.rows=rows;
	a.cols=m.cols;
	num=a.cols*a.rows;
	for (int i=0;numcount<num;i++)
	{
		if (iteratorcols==a.cols)
		{
			iteratorcols=0;
			iteratorrows++;
			i=0;
		}
		for (int j=0;j<=a.cols;j++)
		{
			temp=temp+(mat[iteratorrows][j]*m.mat[j][i]);
		}
		a.mat[iteratorrows][iteratorcols]=temp;
		temp=0;
		iteratorcols++;
		numcount++;
	}
	return a;
}
Matrix & Matrix::transpose()
{
	int temp;
	int ** temp_mat=new int*[cols];
	for (int i=0;i<cols;i++)
	{
		temp_mat[i]=new int[rows];
	}
	for (int i=0;i<rows;i++)
	{
		for (int j=0;j<cols;j++)
		{
			temp_mat[j][i]=mat[i][j];
		}
	}
	for (int i=0;i<rows;i++)
	{
		delete [] mat[i];
	}
	delete [] mat;
	mat=temp_mat;
	temp=rows;
	rows=cols;
	cols=temp;
	return *this;
}
Matrix Matrix::operator-()	//negation operator
{
	Matrix a(rows,cols);
	for (int i=0;i<rows;i++)
	{
		for (int j=0;j<cols;j++)
		{
			a.mat[i][j]=-mat[i][j];
		}
	}
	return a;
}
Matrix Matrix::operator +=(int num)
{
	for (int i=0;i<rows;i++)
	{
		for (int j=0;j<cols;j++)
		{
			mat[i][j]=mat[i][j] + num;
		}
	}
	return *this;
}
Matrix Matrix::operator *=(int num)
{
	for (int i=0;i<rows;i++)
	{
		for (int j=0;j<cols;j++)
		{
			mat[i][j]=mat[i][j] * num;
		}
	}
	return *this;
}