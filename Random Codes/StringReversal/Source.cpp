#include <iostream>
#include <string>
#include <cstring>
void ReverseWord(char *p, int len)

{
	char temp;
	for (int i = 0; i<len / 2; i++)
	{
		temp = p[i];
		p[i] = p[len - i - 1];
		p[len - i - 1] = temp;
	}

}
using namespace std;
void main()
{
	char *sen;
	char *sent;
	int j = 0;
	int start = 0;
	sent = new char[20];
	sen = new char[20];
	cout << "Enter any input" << endl;
	//cin.getline(sen, 20);
	int count = 0;
	strcpy_s(sen,20, "hello frens");
	int a;

	a = strlen(sen);
	for (int i = 0; i < a; i++)
	{
		if (sen[i] != ' ')
		{
			sent[j++] = sen[i];
			count++;
		}
		if (sen[i+1] == ' ' || sen[i+1]=='\0')
		{
			ReverseWord(sent, count);
			for (int j = 0; j<count; j++)
			{
				cout << sent[j];
			}
			j = 0;
			count = 0;
			start = i + 1;
			//sent = NULL;
		}
	}
}