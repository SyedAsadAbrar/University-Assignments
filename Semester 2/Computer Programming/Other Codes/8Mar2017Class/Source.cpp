#include<iostream>
#include"Header.h"
using namespace std;
int main()
{
	//Set*ps=new Set("2,3",2); //any constructor can also be called on stack, like parameterize constructor is called here
	Set s1; //empty set, default constructor is called
	//Set t("3,8,11",3); //tokenizes the string and allocates an int array of size 3 on heap and makes set of size 3
	Set q("2,19",2);
	int arr1[3]={1,2,3};
	int arr2[2]={5,6};
	Set s2(arr1,3),  s3(arr2,2)/*, s4("1,2",2)*/;
	/*s1=s2;*/
	/*Set s5 = s1;*/	//copy constructor is called as s5 is initialized to the value of s1
	s1=s2 + s3;	//s1.operator=(s2,operator+(s3))
	/*t=q;*/	//memberwise copy or shallow copy, this will make the pointer t point to address stored 
				//in q so both pointers will point to same array so when scope ends and destructor is 
				//called, q would delete the array and there would be nothing for q to delete so program would crash
	/*t.operator=(q);*/		//performs same task as t=q which implies that assignment operator is a function and its name is "operator="
	/*s1=s2;
	int i=s1.getSize();*/
	system("pause");
	return 0;
}