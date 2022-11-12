/*
        TCP_Client. This Program will implement the Client Side for TCP_Socket Programming.
        It will get some data from user and will send to the server and as a reply from the
        server, it will get its data back.
*/

#include <stdio.h>
#include <string.h>
#include <sys/socket.h> //socket
#include <arpa/inet.h> //inet_addr

int main(void)
{
        int socket_desc;
        struct sockaddr_in server_addr;
        char server_message[2000], client_message[2000];
        
        //Cleaning the Buffers
        
        memset(server_message,'\0',sizeof(server_message));
        memset(client_message,'\0',sizeof(client_message));
        
        //Creating Socket
        
        socket_desc = socket(AF_INET, SOCK_STREAM, 0);
        
        if(socket_desc < 0)
        {
                printf("Could Not Create Socket. Error!!!!!\n");
                return -1;
        }
        
        printf("Socket Created\n");
        
        //Specifying the IP and Port of the server to connect
        
        server_addr.sin_family = AF_INET;
        server_addr.sin_port = htons(2000);
        server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
        
        //Now connecting to the server accept() using connect() from client side
        
        if(connect(socket_desc, (struct sockaddr*)&server_addr, sizeof(server_addr)) < 0)
        {
                printf("Connection Failed. Error!!!!!");
                return -1;
        }
        
        printf("Connected\n");
        
        if(recv(socket_desc, server_message, sizeof(server_message),0) < 0)
        {
            printf("Receive Failed. Error!!!!!\n");
        }

        printf("Server Message: %s\n",server_message);

        memset(server_message,'\0',sizeof(server_message));
        memset(client_message,'\0',sizeof(client_message));

        //Get Name from the User
        
        printf("Enter message: ");
        gets(client_message);                                     //One is that gets() will only get character string data. 
                                                       //		will get only one variable at a time.
																//  reads characters from stdin and loads them into str
        //Send the name to Server
        
        if(send(socket_desc, client_message, strlen(client_message),0) < 0)
        {
                printf("Send Failed. Error!!!!\n");
                return -1;
        }
        
        printf("%s\n\n", "Name successfully sent to server for verification.");

        //Receive the message back from the server
        
        if(recv(socket_desc, server_message, sizeof(server_message),0) < 0)
        {
                printf("Receive Failed. Error!!!!!\n");
                return -1;
        }
        
        printf("Server Message: %s\n",server_message);

        if(strcmp(server_message, "Invalid name entered. Program is quitting.\n") == 0)
        {            
            close(socket_desc);
            return 0;
        }
        else if(strcmp(server_message, "You are already online from another instance. Program is quitting.\n") == 0)
        {
            close(socket_desc);
            return 0;            
        }

        memset(server_message,'\0',sizeof(server_message));
        memset(client_message,'\0',sizeof(client_message));

        //Get CNIC from the User
        
        printf("Enter message: ");
        gets(client_message);                                     //One is that gets() will only get character string data. 
                                                       //       will get only one variable at a time.
                                                                //  reads characters from stdin and loads them into str

        //Send the CNIC to Server
        
        if(send(socket_desc, client_message, strlen(client_message),0) < 0)
        {
                printf("Send Failed. Error!!!!\n");
                return -1;
        }
        
        printf("%s\n\n", "CNIC successfully sent to server for verification.");

        //Receive the message back from the server
        
        if(recv(socket_desc, server_message, sizeof(server_message),0) < 0)
        {
                printf("Receive Failed. Error!!!!!\n");
                return -1;
        }
        
        printf("Server Message: %s\n",server_message);
        
        if(strcmp(server_message, "Welcome to Online Voting System.\n\nYou have already voted. Program is quitting.\n") == 0)
        {
            close(socket_desc);
            return 0;
        }
        else if(strcmp(server_message, "Invalid CNIC entered. Program is quitting.\n") == 0)
        {            
            close(socket_desc);
            return 0;
        }
        else if(strcmp(server_message, "You are already online from another instance. Program is quitting.\n") == 0)
        {
            close(socket_desc);
            return 0;            
        }


        memset(server_message,'\0',sizeof(server_message));
        memset(client_message,'\0',sizeof(client_message));

        //Get vote from the User
        
        printf("Enter message: ");
        gets(client_message);                                     //One is that gets() will only get character string data. 
                                                       //       will get only one variable at a time.
                                                                //  reads characters from stdin and loads them into str

        //Send vote to Server
        
        if(send(socket_desc, client_message, strlen(client_message),0) < 0)
        {
                printf("Send Failed. Error!!!!\n");
                return -1;
        }
        
        printf("%s\n\n", "Vote successfully sent to server to be casted.");

        //Receive the message back from the server
        
        if(recv(socket_desc, server_message, sizeof(server_message),0) < 0)
        {
                printf("Receive Failed. Error!!!!!\n");
                return -1;
        }
        
        printf("Server Message: %s\n",server_message);
        
        if(strcmp(server_message, "The symbol is incorrect. Program is quitting.\n") == 0)
        {            
            close(socket_desc);
            return 0;
        }

        memset(server_message,'\0',sizeof(server_message));
        memset(client_message,'\0',sizeof(client_message));
        
        //Closing the Socket
        
        close(socket_desc);
        
        return 0;
}