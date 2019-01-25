#include<iostream>
#include"Header.h"
int main()
{
	int numOfSectors, sizeOfSectors;
	cout << "FAT System" << endl;
	cout << "Enter number of sectors." << endl;
	cin >> numOfSectors;
	cout << "Enter size of sector." << endl;
	cin >> sizeOfSectors;
	system("cls");
	cout << "FAT System" << endl;
	string name;
	char fcontent[100];
	string c;
	int i = 0;
	int choice;
	
	fileSystem a(numOfSectors, sizeOfSectors);
	cout << "1. Save file" << endl;
	cout << "2. Delete file" << endl;
	cout << "3. Read file" << endl;
	cout << "4. Exit" << endl;
	cout << "Enter choice" << endl;
	cin >> choice;

	while (choice < 1 || choice >4)
	{
		cout << "Invalid number entered. Please enter a number in the range of 1-4." << endl;
		cin >> choice;
	}
	while(choice!=4)
	{
		if (choice == 1)
		{
			cout << "Enter file name." << endl;
			cin >> name;
			cout << "Enter file content." << endl;
			cin >> c;
			while (i<c.length())
			{
				fcontent[i] = c[i];
				i++;
			}
			fcontent[i] = '\0';
			a.saveFile(name, i, fcontent);
			i = 0;
			cout << endl << "Enter choice." << endl;
			cin >> choice;
		}
		if (choice == 2)
		{
			cout << "Enter file name." << endl;
			cin >> name;
			a.deleteFile(name);
			cout << endl << "Enter choice." << endl;
			cin >> choice;
		}
		if (choice == 3)
		{
			cout << "Enter file name." << endl;
			cin >> name;
			a.readFile(name);
			cout << endl << "Enter choice." << endl;
			cin >> choice;
		}
		if (choice == 4)
		{
			return 0;
		}
	}
	system("pause");
	return 0;
}