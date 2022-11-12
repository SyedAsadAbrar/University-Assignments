/*
        TCP_Server. This Program will will create the Server side for TCP_Socket Programming.
        It will receive the data from the client and then send the same data back to client.
*/

#include <stdio.h> 
#include <string.h> 
#include <sys/socket.h> //socket
#include <arpa/inet.h> //inet_addr
#include <pthread.h>

int number = 0;
pthread_t thread[4];

struct thread_struct
{
        //char server_message[2000];
        //char client_message[2000];
        int cid;
        int index;
};


void *handle_client (void *thread_struct)
{
	struct thread_struct *t_st = (struct thread_struct*)thread_struct;
	int cid = t_st->cid;
	int index = t_st->index;
	char cmessage[2000];
	char smessage[2000];

    while(1)
	{
	  	if (number == 4)
		{
			strcpy(smessage, "Server Full");
			thread[3] = NULL;
		}

	  	else
	  	{
	  		//Receive the message from the client
		   
			if (recv(cid, cmessage, sizeof(smessage),0) < 0)
			{
		        printf("Receive Failed. Error!!!!!\n");
		        return -1;
			}
				
			printf("Client Message: %s\n\n",cmessage);
		
			if(strcmp(cmessage, "DISCONNECT") == 0)
            {
            	thread[index] = NULL;
            	number = number - 1;
            	for (int i = index; i < 3; i++)
            	{
            		thread[i] = thread[i+1];
            	}
                close(cid);
                pthread_exit(NULL);
            }


			//Send the message back to client		
			strcpy(smessage, cmessage);
		}
		
		if (send(cid, smessage, strlen(smessage),0)<0)
		{
	        printf("Send Failed. Error!!!!!\n");
	        return -1;
		}
				
		memset(smessage,'\0',sizeof(smessage));
		memset(cmessage,'\0',sizeof(cmessage));
	

		if (number == 4)
		{
			number = number - 1;
			close(cid);
			pthread_exit(NULL);
		}
	}
}

int main(void)
{
        int socket_desc, client_sock, client_size; 
        struct sockaddr_in server_addr, client_addr;         //SERVER ADDR will have all the server address
        char server_message[2000], client_message[2000];                 // Sending values from the server and receive from the server we need this

        int iret = 0;

        struct thread_struct thread_st;
        //Cleaning the Buffers
        
        memset(server_message,'\0',sizeof(server_message));
        memset(client_message,'\0',sizeof(client_message));     // Set all bits of the padding field//
        
        //Creating Socket
        
        socket_desc = socket(AF_INET, SOCK_STREAM, 0);
        
        if(socket_desc < 0)
        {
                printf("Could Not Create Socket. Error!!!!!\n");
                return -1;
        }
        
        //printf("Socket Created\n");
        
        //Binding IP and Port to socket
        
        server_addr.sin_family = AF_INET;               /* Address family = Internet */
        server_addr.sin_port = htons(2000);               // Set port number, using htons function to use proper byte order */
        server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");    /* Set IP address to localhost */
		
		
		
		// BINDING FUNCTION
        
        if(bind(socket_desc, (struct sockaddr*)&server_addr, sizeof(server_addr))<0)    // Bind the address struct to the socket.  /
	                            	//bind() passes file descriptor, the address structure,and the length of the address structure
        {
                printf("Bind Failed. Error!!!!!\n");
                return -1;
        }        
        
        //printf("Bind Done\n");
        
        //Put the socket into Listening State
        
        if(listen(socket_desc, 1) < 0)                               //This listen() call tells the socket to listen to the incoming connections.
     // The listen() function places all incoming connection into a "backlog queue" until accept() call accepts the connection.
        {
                printf("Listening Failed. Error!!!!!\n");
                return -1;
        }
        
        //printf("Listening for Incoming Connections.....\n");
        
        while(1)
        {
        	//Accept the incoming Connections
        
		    client_size = sizeof(client_addr);
		
		    client_sock = accept(socket_desc, (struct sockaddr*)&client_addr, &client_size);          // here particular client k liye new socket create kr rhaa ha
		    
		    if (client_sock < 0)
		    {
		            printf("Accept Failed. Error!!!!!!\n");
		            return -1;
		    }
		    
		    thread_st.cid = client_sock;
		    thread_st.index = number;

		    iret = pthread_create(&thread[number], NULL, handle_client, &thread_st);

		    if(iret == 0)
		    {
		    	if(number < 3)
		    	{
		    		printf("Client Connected.\n");
		    	}
		    	number = number + 1;
				   //inet_ntoa() function converts the Internet host address in, given in network byte order, to a string in IPv4 dotted-decimal notation
		    }
        }
                 
        //Closing the Socket
        
        close(client_sock);
        close(socket_desc);
        return 0;       
}
