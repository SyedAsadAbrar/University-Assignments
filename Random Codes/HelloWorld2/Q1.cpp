#include<iostream>
#include<fstream>
#include<string>
using namespace std;

int main(int argc, char * argv[])
{
	string line;
	ifstream myfile;
	char * arr = argv[1];
	myfile.open(arr);
	if (myfile.is_open())
	{
		while (getline(myfile, line))
		{
			cout << line << '\n';
		}
		myfile.close();
	}
	else
		cout << "Unable to open file";
	return 0;
}