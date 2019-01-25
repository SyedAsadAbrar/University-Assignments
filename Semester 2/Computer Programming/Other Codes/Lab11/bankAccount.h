class bankAccount
{
protected:
	int acc;
	double bal;
public:
	bankAccount();										//default constructor
	bankAccount(int num);									//set account num
	int retAcc(const bankAccount & user);					//retrieve account num
	double retBal(const bankAccount & user);				//retrive account balance
	void depositMoney(double money);	
	void withdrawMoney(double money);
	void printAcc();
	/*~bankAccount();*/
};
class checkingAccount:public bankAccount
{
	double interest;
	double min_bal;
	double charges;
public:
	checkingAccount();
	checkingAccount(int acc, double i, double min, double service);
	double retInt(const checkingAccount & user);
	double retBal(const checkingAccount & user);
	double retCharges(const checkingAccount & user);
	double postInterest(const checkingAccount & user);
	void withdrawMoney(double money);
	void printAcc();
};
class savingAccount:public bankAccount
{
	double interest;
public:
	savingAccount();
	savingAccount(int acc, double i);
	double retInt(const savingAccount & user);
	void recInt(double int_money);
	void withdrawMoney(double money);
	double postInterest(const savingAccount & user);
	void printAcc();
};