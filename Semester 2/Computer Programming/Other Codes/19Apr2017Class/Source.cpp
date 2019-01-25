#include<iostream>
using namespace std;
void merge(int *A, int * B, int * C, int nA, int nB, int lc, int rc)
{
	//MERGE SORT
	int cA=0,cB=0;
	while(cA<nA && cB<nB && lc<=rc)
	{
		if (A[cA]<=B[cB])
		{
			C[lc]=A[cA];
			lc++;
			cA++;
		}
		else
		{
			C[lc]=B[cB];
			lc++;
			cB++;
		}
	}
	while(cA<nA && lc<=rc)
	{
		C[lc]=A[cA];
		lc++;
		cA++;
	}
	while(cB<nB && lc<=rc)
	{
		C[lc]=B[cB];
		lc++;
		cB++;
	}
}
int main()
{
	//MERGE SORT
	//If I want to sort an arr A with n numbers and the first half(0 to n) and the second half((n/2) to (n-1)) are already
	//separately sorted(assumption). Then I can sort A in n steps.
	//Function defined above main.
	int A[5]={2,3,8,9,10};
	int B[7]={1,4,7,9,15,21,23};
	int C[12];
	merge(A,B,C,5,7,0,11);
	return 0;
}