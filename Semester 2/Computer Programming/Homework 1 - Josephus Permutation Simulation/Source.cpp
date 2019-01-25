#include<iostream>
#include <stdlib.h>
#include <time.h>  
#include <windows.h> 
using namespace std;
void color(int col)
{
	HANDLE text = GetStdHandle(STD_OUTPUT_HANDLE);
	if (col==-1) //white
	{
		SetConsoleTextAttribute(text, 7);
	}
	if (col==1) //blue
	{
		SetConsoleTextAttribute(text, 1);
	}
	if (col==4) //red
	{
		SetConsoleTextAttribute(text, 4);
	}
}
int random(int start, int end)
{
	srand (time(NULL));
	return (rand()% (end-start+1) + start);
}
int * makeIntArray(int n)
{
	if(n==0)
	{
		return nullptr;
	}
	return new int[n];
}
int * extendBy1(int * arr1, int n, int add)
{
	int * arr2=makeIntArray(n+1);
	int temp=0;
	for (int i=0;i<(n+1);i++)
	{
		while (i!=add)
		{
			arr2[i]=arr1[temp];
			temp++;
			break;
		}
	}
	delete [] arr1;
	return arr2;
}
int * shrinkBy1(int * arr1, int n, int del)
{
	int * arr2=makeIntArray(n-1);
	int iterator=0;
	for (int i=0;i<n;i++)
	{
		while (i!=del)
		{
			arr2[iterator]=arr1[i];
			iterator++;
			break;
		}
	}
	delete [] arr1;
	return arr2;
}
void zeroes(int compareto, int num, int col)
{
	color(col);
	int temp=0, zeroes=0, temp2=0, temp3=0, temp4=0;
	while(compareto>0)
	{
		temp=compareto%10;
		compareto=compareto/10;
		zeroes++;
	}
	zeroes--;
	temp=zeroes;
	temp2=num;
	while(temp2>0)
	{
		temp3=temp2%10;
		temp2=temp2/10;
		temp4++;
	}
	while(zeroes!=0 && temp4!=(temp+1))
	{
		cout<<"0";
		zeroes--;
		temp4++;
	}
	color(-1);
}
void recdimensions(int &l, int &b, int &num) // to find l and b of rectangle and correct odd number of players
{
	int n,m;
	if (num%2!=0)
	{
		num++;
	}
	n=num/2;
	if (n%2==0)
	{
		m=n/2;
		l=m+1;
		b=m-1;
	}
	else 
	{
		m=n/2;
		l=m+1;
		b=m;
	}
	/*b=b+2;*/
}
void originalrect(int l, int b, int k,int highest, int players[])
{
	int iterator=-1;
	color(-1);
	for (int i=0;i<l;i++)
	{
		iterator++;
		zeroes(highest,players[i],-1);
		cout<<players[i]<<"  ";
	}
	cout<<endl;
	for (int j=1;j<=b;j++)
	{
		zeroes(highest,players[k-j],-1);
		cout<<players[k-j];
		if (k>10)
		{
			for (int z=2;z<(2*l)-1;z++)
			{
				cout<<"  ";
			}
		}
		else
		{
			for (int z=0;z<l;z++)
			{
				cout<<"  ";
			}
		}
		zeroes(highest,players[++iterator],-1);
		cout<<players[iterator]<<endl;
	}
	for (int temp=k-(b+1);temp>iterator;temp--)
	{
		zeroes(highest,players[temp],-1);
		cout<<players[temp]<<"  ";
	}
	cout<<endl;
	Sleep(200);
	system("cls");
}
void currentplayerrect(int l, int b, int z,int currenthighest, int curr, int col, int players[], int countremove)
{
	int iterator=-1, temp=col;
	int temp2=1;
	int temp3;
	color(-1);
	for (int i=0;i<l;i++)
	{
		if (i!=curr)
		{
			col=-1;
		}
		iterator++;
		zeroes(currenthighest,players[i],col);
		color(col);
		cout<<players[i];
		color(-1);
		cout<<"  ";
		col=temp;
	}
	cout<<endl;
	for (int j=1;j<=b;j++)
	{
		if ((z-temp2)!=curr)
		{
			col=-1;
		}
		if (countremove<1)
		{
			zeroes(currenthighest,players[z-temp2],col);
			color(col);
			cout<<players[z-temp2];
			color(-1);
			col=temp;
			temp2++;
		}
		else
		{
			cout<<"  ";
			countremove--;
		}
		color(-1);
		col=temp;
		if (z>10)
		{
			for (int g=2;g<(2*l)-1;g++)
			{
				cout<<"  ";
			}
		}
		else
		{
			for (int g=0;g<l;g++)
			{
				cout<<"  ";
			}
		}
		if ((++iterator)!=curr)
		{
			col=-1;
		}
		zeroes(currenthighest,players[iterator],col);
		color(col);
		cout<<players[iterator]<<endl;
		color(-1);
		col=temp;
	}
	temp3=z-temp2;
	for (int o=temp3;o>iterator;o--)
	{
		if ((o)!=curr)
		{
			col=-1;
		}
		zeroes(currenthighest,players[o],col);
		color(col);
		cout<<players[o];
		color(-1);
		cout<<"  ";
		col=temp;
	}
	cout<<endl;
	Sleep(200);
	system("cls");
}
int main()
{
	int n, k, r, p, q, j, randint, removecount=0, pcount=0, icount=0, highest, todel, currenthighest, removed=0, currentsize;
	int l=0,b=0;
	cout<<"Please enter value for number n."<<endl;
	cin>>n;
	k=n;
	recdimensions(l,b,k);
	highest=k;
	int *size=makeIntArray(k);
	for (int i=0;i<k;i++)				// for filling array with numbers
	{
		size[i]=(i+1);
	}
	currenthighest=size[k-1];
	currentsize=size[k-1];
	/*for (int i=0;i<k;i++)
	{
		cout<<size[i]<<endl;
	}*/
	/*cout<<l<<" "<<b<<endl;*/					//length and breadth of rectangle
	//int** size=new int*[l];					// Array of pointers stored in heap for numbers
	//for(int i=0; i<l; i++)
	//{
	//	size[i] = new int[b];
	//}
	originalrect(l,b,k,highest,size);					//Printing rectangle for the first time
	p=random(15,18);								//Random number p(only randomized once)
	r=random(1,(k));							//Random number r, randomized after every killing
	randint=random(1,k);
	int i=randint;
	while(k!=1)
	{
		if(pcount==p && removed<=7)
		{
			q=random(1,5);
			j=random(1,k-1);
			for (int i=0;i<q;i++)
			{
				size=extendBy1(size,k,j);
				highest++;
				size[j]=highest;
				currenthighest=highest;
				k++;
				j++;
				removecount--;
			}
			if (removecount<0)
			{
				removecount=0;
			}
			if (k%2!=0)
			{
				removecount=1;
			}
			recdimensions(l,b,k);
			if (removecount==1)
			{
				k--;
			}
			pcount=0;
		}
		if (icount==r)
		{
			currentplayerrect(l,b,k,currenthighest,i,4,size,removecount);
			size=shrinkBy1(size,k,i);
			k--;
			todel=r;
			r=random(1,(k));
			removecount++;
			icount=0;
			removed++;
		}
		if (removecount==2)
		{
			recdimensions(l,b,k);
			/*cout<<l<<" "<<b<<" "<<k<<endl;*/
			removecount=0;
			originalrect(l,b,k,highest,size);
		}
		/*for (int i=0;i<k;i++)
			{
				cout<<size[i]<<endl;
			}*/
		currentplayerrect(l,b,k,currenthighest,i,1,size,removecount);
		i++;
		pcount++;
		icount++;
		if (i>=k)
		{
			i=i%k;
		}
	}
	cout<<size[0]<<" is the winner."<<endl;
	system("pause");
	return 0;
}