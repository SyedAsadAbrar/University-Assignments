#include<iostream>
using namespace std;
int gcd(int num1, int num2)
{
	//i sometimes think i'm stupid
	//then i realise I am
	//- Mariyam Tanveer(circa 2017)
	int g, l, ret, temp = 1;
	while (num1 != 0 || num2 != 0)
	{
		if (num1 > num2)
		{
			g = num1;
			l = num2;
		}
		else if (num1 < num2)
		{
			g = num2;
			l = num1;
		}
		else
		{
			return num1;
		}
		while (temp < (g/2))
		{
			if (l%temp == 0 && g%temp == 0)
			{
				ret = temp;
			}
			temp++;
		}
		return ret;
	}
	return 0;
}
int main()
{
	int n, count = 0;
	float x, y, sum = 0;
	float num;
	float den = 1;
	int temp, temp2;
	cout << "Enter calculator name here, idk lol"<< endl;
	cout << "Select operation." << endl;
	cout << "1. Multiplication\n2. Division\n3. Addition\n4. Average\n5. Subtraction\n6. Remainder\n";
	cin >> n;
	while (n < 1 || n > 6)
	{
		cout << "Invalid input entered. Enter number from 1-6." << endl;
		cin >> n;
	}
	if (n == 1)
	{
		cout << endl << "You selected multiplication." << endl;
		cout << "Enter multiplicand." << endl;
		cin >> x;
		cout << endl << "Enter multiplier" << endl;
		cin >> y;
		num = x*y;
		temp = int(num);
		cout << endl << "The answer of multplying " << x << " and " << y << " is ";
		if (temp != 0)
		{
			cout << temp;
		}
		num = num - temp;
		if (num != 0)
		{
			while ((num - temp) != 0)
			{
				num = num * 10;
				den = den * 10;
				temp = int(num);
			}
			temp2 = gcd(int(num), int(den));
			cout << num / temp2 << "/" << den / temp2 << endl;
		}
	}
	if (n == 2)
	{
		cout << endl << "You selected division." << endl;
		cout << "Enter dividend." << endl;
		cin >> x;
		cout << endl << "Enter divisor" << endl;
		cin >> y;
		num = x/y;
		temp = int(num);
		cout << endl << "The answer of dividing " << x << " and " << y << " is ";
		if (temp != 0)
		{
			cout << temp;
		}
		num = num - temp;
		if (num != 0)
		{
			while ((num - temp) != 0)
			{
				num = num * 10;
				den = den * 10;
				temp = int(num);
			}
			temp2 = gcd(int(num), int(den));
			cout << num / temp2 << "/" << den / temp2 << endl;
		}
	}
	if (n == 3)
	{
		cout << endl << "You selected addition." << endl;
		cout << "Enter first number." << endl;
		cin >> x;
		cout << endl << "Enter second number" << endl;
		cin >> y;
		num = x + y;
		temp = int(num);
		cout << endl << "The answer of adding " << x << " and " << y << " is ";
		if (temp != 0)
		{
			cout << temp;
		}
		num = num - temp;
		if (num != 0)
		{
			while ((num - temp) != 0)
			{
				num = num * 10;
				den = den * 10;
				temp = int(num);
			}
			temp2 = gcd(int(num), int(den));
			cout << num / temp2 << "/" << den / temp2 << endl;
		}
	}
	if (n == 4)
	{
		cout << endl << "You selected average." << endl;
		cout << "Enter quantity of numbers." << endl;
		cin >> x;
		while (int(x) < 0)
		{
			cout << endl << "Invalid quantity entered." << endl;
			cin >> x;
		}
		while (count < int(x))
		{
			cout << endl << "Enter number" << endl;
			cin >> y;
			num = num + y;
		}
		num = num / x;
		temp = int(num);
		cout << endl << "The average of entered numbers is ";
		if (temp != 0)
		{
			cout << temp;
		}
		num = num - temp;
		if (num != 0)
		{
			while ((num - temp) != 0)
			{
				num = num * 10;
				den = den * 10;
				temp = int(num);
			}
			temp2 = gcd(int(num), int(den));
			cout << num / temp2 << "/" << den / temp2 << endl;
		}
	}
	if (n == 5)
	{
		cout << endl << "You selected subtraction." << endl;
		cout << "Enter first number." << endl;
		cin >> x;
		cout << endl << "Enter second number" << endl;
		cin >> y;
		num = x - y;
		temp = int(num);
		cout << endl << "The answer of subtracting " << x << " and " << y << " is ";
		if (temp != 0)
		{
			cout << temp;
		}
		num = num - temp;
		if (num != 0)
		{
			while ((num - temp) != 0)
			{
				num = num * 10;
				den = den * 10;
				temp = int(num);
			}
			temp2 = gcd(int(num), int(den));
			cout << num / temp2 << "/" << den / temp2 << endl;
		}
	}
	if (n == 6)
	{
		cout << endl << "You selected remainder." << endl;
		cout << "Enter dividend." << endl;
		cin >> x;
		cout << endl << "Enter divisor" << endl;
		cin >> y;
		if (x < y)
		{
			cout << endl << "The remainder of " << x << " and " << y << " is " << y << endl;
		}
		else 
		{
			num = x;
			while (num >= y)
			{
				num = x - y;
			}
			temp = int(num);
			cout << endl << "The remainder of " << x << " and " << y << " is ";
			if (temp != 0)
			{
				cout << temp << endl;
			}
			num = num - temp;
			if (num != 0)
			{
				while ((num - temp) != 0)
				{
					num = num * 10;
					den = den * 10;
					temp = int(num);
				}
				temp2 = gcd(int(num), int(den));
				cout << num / temp2 << "/" << den / temp2 << endl;
			}
		}
	}
	system("pause");
	return 0;
}