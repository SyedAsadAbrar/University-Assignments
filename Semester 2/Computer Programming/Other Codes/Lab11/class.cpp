#include"bankAccount.h"
#include<iostream>
//#include<vector>		//dynamic array which handles memory allocation automatically
using namespace std;
bankAccount::bankAccount()									//default constructor
{
	bal=0;
	acc=0;
}
bankAccount::bankAccount(int num)									//set account num
{
	bal=0;
	acc=num;
}
int bankAccount::retAcc(const bankAccount & user)					//retrieve account num
{
	return user.acc;
}
double bankAccount::retBal(const bankAccount & user)				//retrive account balance
{
	return user.bal;
}
void bankAccount::depositMoney(double money)
{
	bal+=money;
}
void bankAccount::withdrawMoney(double money)
{
	if (money<=bal)
	{
		bal-=money;
	}
}
void bankAccount::printAcc()
{
	cout<<"Details"<<endl;
	cout<<"Bank account num: "<<acc<<endl;
	cout<<"Bank account bal: "<<bal<<endl;
}
checkingAccount::checkingAccount():bankAccount()
{
	interest=0;
	min_bal=0;
	charges=0;
}
checkingAccount::checkingAccount(int acc, double i, double min, double service):bankAccount(acc)
{
	interest=i;
	min_bal=min;
	charges=service;
}
double checkingAccount::retInt(const checkingAccount & user)
{
	return user.interest;
}
double checkingAccount::retBal(const checkingAccount & user)
{
	return user.min_bal;
}
double checkingAccount::retCharges(const checkingAccount & user)
{
	return user.charges;
}
double checkingAccount::postInterest(const checkingAccount & user)
{
	double temp=0;
	temp=(user.interest*user.bal)/100;
	return temp;
}
void checkingAccount::withdrawMoney(double money)
{
	bankAccount::withdrawMoney(money);
	if (bal<min_bal)
	{
		bal-=charges;
	}
}
//void checkingAccount::printAcc()
//{
//	(*this).bankAccount::printAcc;
//	cout<<"Bank account interest rate: "<<interest<<endl;
//	cout<<"Bank account minimum balance: "<<min_bal<<endl;
//	cout<<"Bank account service charges(if any): "<<charges<<endl;
//}
savingAccount::savingAccount():bankAccount()
{
	interest=0;
}
savingAccount::savingAccount(int acc, double i):bankAccount(acc)
{
	interest=i;
}
double savingAccount::retInt(const savingAccount & user)
{
	return user.interest;
}
void savingAccount::recInt(double int_money)
{
	bal+=int_money;
}
void savingAccount::withdrawMoney(double money)
{
	bankAccount::withdrawMoney(money);
}
void savingAccount::printAcc()
{
	(*this).bankAccount::printAcc;
	cout<<"Bank account interest rate: "<<interest<<endl;
}