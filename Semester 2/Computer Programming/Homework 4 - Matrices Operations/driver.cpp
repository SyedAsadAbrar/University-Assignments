#include<iostream>
#include"Matrix.h"
using namespace std;
void printMatrix(/*const */Matrix & m)
{
	for(int i=0;i<(m.getNumRows());i++)
	{
		for(int j=0;j<(m.getNumCols());j++)
		{
			cout<<m[i][j]<<" ";
		}
		cout<<endl;
	}
	cout<<endl;
}
int main()
{
	Matrix m1;//empty 0x0 matrix
	printMatrix(m1);
	Matrix m2("mat1.txt"),m3("m3.txt");//read from files
	Matrix m5(5,7);//create a 5x7 matrix with random values
	printMatrix(m5);
	m1=m2+m3;//add matrices
	printMatrix(m1);
	cout<<endl;
	printMatrix(m2);
	cout<<endl;
	printMatrix(m3);
	cout<<endl;
	Matrix m4=m2-m3;//subtract matrices
	printMatrix(m4);
	if(m1==m4)
	{
		cout<<"m1 and m4 are equal matrices";
	}
	m4.transpose();
	printMatrix(m4);
	m5=m1*m4;//matrix multiplication (assume compatability)
	printMatrix(m5);
	m5.transpose();//convert nxm to mxn matrix
	printMatrix(m5);
	m3=-m5;//negates all elements of m2
	printMatrix(m3);
	printMatrix(m5);
	m5+=5;//adds 5 to all elements of m2
	printMatrix(m5);
	m5*=5;//multiplies 5 to all elements of m2
	printMatrix(m5);
	system("pause");
	return 0;
}