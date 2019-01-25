#include<iostream>
using namespace std;
void convertDecimalToBinary(int n,char num[])
{
    int remainder;
	int j=0;
    while (n!=0)
    {
        remainder = n%2;
		n /= 2;
        num[j]=remainder+48;
		j++;
    }
	num[j]='\0';
	while (strlen(num)%4!=0)
	{
		num[j]='0';
		num[j+1]='\0';
		j++;
	}
	for (int i=0;j>i;i++)
	{
		swap(num[i],num[j-1]);
		j--;
	}
}
bool isEvil(char num[])
{
	int count=0;
	for (int i=0;num[i]!='\0';i++)
	{
		if(num[i]=='1')
		{
			count++;
		}
	}
	if (count%2==0)
	{
		return true;
	}
	else if (count%2!=0)
		return false;
}
int main()
{
	int n;
	char array[1024];
    cout << "Enter a decimal number: ";
    cin >> n;
    convertDecimalToBinary(n,array);
    cout << n << " in decimal = " << array << " in binary" << endl ;
	if (isEvil(array))
	{
		cout<<"The number "<<n<<" is evil."<<endl;
	}
	else if(isEvil(array)==false)
	{
		cout<<"The number "<<n<<" is not evil."<<endl;
	}
	system("pause");
    return 0;
}