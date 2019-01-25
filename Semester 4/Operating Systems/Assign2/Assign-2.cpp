#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <unistd.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string>

using namespace std;

/*
struct in_addr 
{
    unsigned long s_addr;  // load with inet_aton()
};
struct sockaddr
{
	unsigned short sa_family; //address family AF_xxx 
	unsigned short sa_data[14]; //14 bytes of protocol addr 
}

struct sockaddr_in
{

	short int sin_family; // set to AF_INET 
	unsigned short int sin_port; // port number 
	in_addr sin_addr; // internet address 
	unsigned char sin_zero[8]; //set to all zeros 
}
*/

// Driver program to test above functions
int main(int argv, char * arg[])
{
	//to take input
	string input;
	string ip;

	//for forking a child to load xterm
	pid_t pid;

	cout<<endl<<"Welcome to Chitee Chat."<<endl;
	cout<<endl<<"Type \"quit\" to quit program or \"connect <ip address>\" to set up chat."<<endl;

	getline(cin,input);
	if(input=="quit")
	{
		cout<<"You chose to quit. Exiting..."<<endl;
		exit(EXIT_SUCCESS);
	}
	else if(input[0]=='c' && input[1]=='o' && input[2]=='n' && input[3]=='n' && input[4]=='e' && input[5]=='c' && input[6]=='t' && input[7]==' ')
	{
		ip=input.substr(8);
		cout<<"You chose to connect to "<<ip<<". Connecting..."<<endl;

		pid = fork();

		if(pid == 0)	//child
		{
			execlp("/usr/bin/xterm","xterm","-e", "./server; $SHELL",NULL);
			exit(EXIT_SUCCESS);
		}
		else if(pid>0) //parent
		{
			pid = fork();
			if(pid==0)
			{
				execlp("/usr/bin/xterm","xterm","-e", "./client; $SHELL",NULL);
				exit(EXIT_SUCCESS);
			}
			else if(pid>0)
			{
				wait(NULL);
			}
			wait(NULL);
		}
	}


return 0;
}