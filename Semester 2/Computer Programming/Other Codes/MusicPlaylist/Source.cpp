#include<iostream>
#include<fstream>
#include<cstring>
#include<string.h>
using namespace std;
struct song
{
	char*art;
	char*title;
};
song**createplaylist(char*filename)
{
	ifstream fin(filename);
	int n;
	fin >> n;
	song**pl = new song*[n];
	for (int i = 0; i < n; i++)
	{
		pl[i] = new song;
	}
	if (fin.fail())
	{
		cout << "you're an idiot!";
	}
	else
	{
		char buff[500];
		int i = 0;
		fin.getline(buff, 500);
		int length = strlen(buff);
		while (fin.getline(buff, 500))
		{
			length = strlen(buff);
			pl[i]->art = new char[strlen(buff) + 1];
			strcpy_s(pl[i]->art,length+1, buff);
			cout << buff << endl;
			fin.getline(buff, 500);
			length = strlen(buff);
			pl[i]->title = new char[strlen(buff) + 1];
			strcpy_s(pl[i]->title, length + 1, buff);
			i++;
		}
	}
	return pl;
}
//song**Addsong(song**arr1, int n, song s)
//{
//
//}
//song**cleanup()
//{
//
//}
int main()
{
	createplaylist("songs.txt");
}