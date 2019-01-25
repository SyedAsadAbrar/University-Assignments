#include<unistd.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netinet/in.h>
#include <arpa/inet.h>
#include<stdio.h>
#include<string>
#include<iostream>
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
int main()
{
	int sd;
	char msg[1000];
	string message;	
	sockaddr_in server_addr;
	server_addr.sin_family = AF_INET;
	server_addr.sin_port = htons(7788);
	server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	
	sd = socket(AF_INET,SOCK_STREAM,0);
	
	connect(sd, (struct sockaddr *)&server_addr, sizeof(sockaddr));

	cout<<"Connection established."<<endl;

	while(1)
	{
		cout<<"You: ";
		getline(cin,message);
		send(sd,message.c_str(),1024,0);
		if (message=="quit")
		{
			close(sd);
			exit(EXIT_SUCCESS);
		}
		recv(sd,msg,1024,0);
		if(msg[0]=='q' && msg[1]=='u' && msg[2]=='i' && msg[3]=='t')
		{
			close(sd);
			exit(EXIT_SUCCESS);
		}
		cout<<"Other user: ";
		printf("%s\n",msg);
	}

	return 0;
	
}
