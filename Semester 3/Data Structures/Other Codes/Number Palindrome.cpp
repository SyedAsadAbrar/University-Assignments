#include<iostream>
#include<conio.h>
using namespace std;

int main()
{
int a,no,b,temp=0;

cout<<"Enter any num: ";
cin>>no;
b=no;
while(no>0)
{
a=no%10;
no=no/10;
temp=temp*10+a;
}
if(temp==b)
{
cout<<"Palindrome";
}
else
{
cout<<"Not Palindrome";
}
getch();
}