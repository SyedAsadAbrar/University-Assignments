/*
		TCP_Server. This Program will will create the Server side for TCP_Socket Programming.
		It will receive the data from the client and then send the same data back to client.
*/

#include <stdio.h> 
#include <string.h> 
#include <sys/socket.h> //socket
#include <arpa/inet.h> //inet_addr

int people_online[50] = {0};

void *handle_client (void * client_sock)
{
	int cid = *((int*) client_sock);
	char cmessage[2000];
	char smessage[2000];

	char buff[255];
	char voters[50][50];
	char cnic[50][16];
	char *token;
	char candidates[50][50];
	char symbols[50][10];
	int i = 0;
	FILE *fptr;
	char name[50];
	char voter_cnic[20];
	char voted_candidate[50];
	char voted_symbol[10];
	char people_voted[50][20];

	int found = 0;
	int len;
	int verify = 0;
	int not_voted = 0;
	int index = 0;

	//Reading voters list

	if ((fptr = fopen("Voters_List.txt", "r")) == NULL)
	{
		printf("Error! Couldn't open Voters_List.txt file\n");
		// Program exits if file pointer returns NULL.
		exit(1);         
	}
	else
	{
		while(fgets(buff, 255, (FILE*) fptr))
		{
			token = strtok(buff, "/");
			strcpy(voters[i], token);

			//to remove the delimeter
			token = strtok(NULL, "/");

			strncpy(cnic[i], token, strlen(token) - 2);

			i=i+1;
		}
		i = 0;
	}

	//Reading Candidates List

	if ((fptr = fopen("Candidates_List.txt", "r")) == NULL)
	{
		printf("Error! Couldn't open Candidates_List.txt file\n");
		// Program exits if file pointer returns NULL.
		exit(1);         
	}
	else
	{
		while(fgets(buff, 255, (FILE*) fptr))
		{
			token = strtok(buff, "\t");
			strcpy(candidates[i], token);

			//Testing
			//printf("'%s'", token);

			//to remove the delimeter
			token = strtok(NULL, "\t");
 
			//Testing
			//printf("'%s'", token);	

			strncpy(symbols[i], token, strlen(token) - 2);

			//Testing
			//printf("'%s'%d", symbols[i], i);

			i=i+1;

			//Testing
			//printf("%s\n", candidates[i-1]);
			//printf("%s\n", symbols[i-1]);
		}
		i = 0; 
	}

	//Sending first message to client

	strcpy(smessage, "Please enter your full name.\n");

	//Testing
	//printf("%d\n", cid);
	//printf("%s\n", smessage);

	if(send(cid, smessage, strlen(smessage),0) < 0)
	{
		printf("Send Failed. Error!!!!\n");
	}

	if(recv(cid, cmessage, sizeof(cmessage),0) < 0)
	{
		printf("Receive Failed. Error!!!!!\n");
	}

	i = 0;
	len = sizeof(voters[0])/sizeof(voters[0][0]);
	found = 0;

	//Testing
	//printf("%s\n\n", cmessage);

	//Checking if name sent by client in list or not
	for (i = 0;i < len; i++)
	{
		//Testing
		//printf("%d\t%s\n", i, voters[i]);
		if(strcmp(cmessage, voters[i]) == 0)
		{
			found = 1;
			strcpy(name, cmessage);
			break;
		}
	}

	memset(smessage,'\0',sizeof(smessage));
	memset(cmessage,'\0',sizeof(cmessage));

	//Name sent by client matched in list
	if(found == 1)
	{
		if(people_online[i] == 1)
		{
			strcpy(smessage, "You are already online from another instance. Program is quitting.\n");
			if(send(cid, smessage, strlen(smessage),0) < 0)
			{
				printf("Send Failed. Error!!!!\n");
			}
			close(cid);
			pthread_exit(NULL);
		}

		strcpy(smessage, "Please enter your CNIC (XXXXX-XXXXXXX-X) where X is an integer.\n");
		if(send(cid, smessage, strlen(smessage),0) < 0)
		{
			printf("Send Failed. Error!!!!\n");
		}
		if(recv(cid, cmessage, sizeof(cmessage),0) < 0)
		{
			printf("Receive Failed. Error!!!!!\n");
		}

		i = 0;
		len = sizeof(cnic[0])/sizeof(cnic[0][0]);
		found = 0;

		//Testing
		//printf("'%s'\n\n", cmessage);

		//Checking if CNIC sent by client in list or not
		for (i = 0;i < len; i++)
		{
			//Testing
			//printf("'%s'\n\n", cnic[i]);
			if(strcmp(cmessage, cnic[i]) == 0)
			{
				found = 1;
				strcpy(voter_cnic, cmessage);
				people_online[i] = 1;
				index = i;
				break;
			}
		}

		memset(smessage,'\0',sizeof(smessage));
		memset(cmessage,'\0',sizeof(cmessage));

		//CNIC sent by client matched in list
		if(found == 1)
		{
			verify = 1;
		}
		//CNIC not matched
		else
		{
			strcpy(smessage, "Invalid CNIC entered. Program is quitting.\n");
			if(send(cid, smessage, strlen(smessage),0) < 0)
			{
				printf("Send Failed. Error!!!!\n");
			}
			close(cid);
			pthread_exit(NULL);
		}
	}
	//Name not matched in list
	else
	{
		printf("%s\n", "Name doesn't exist in voters' list.\nListening for Incoming Connections.....\n");
		memset(smessage,'\0',sizeof(smessage));
		memset(cmessage,'\0',sizeof(cmessage));
		strcpy(smessage, "Invalid name entered. Program is quitting.\n");
		if(send(cid, smessage, strlen(smessage),0) < 0)
		{
			printf("Send Failed. Error!!!!\n");
		}
		close(cid);
		pthread_exit(NULL);
	}

	//Name and CNIC matched in list
	if(verify == 1)
	{
		//getting list of people who have already voted
		if ((fptr = fopen("Output.txt", "r")) == NULL)
		{
			printf("No user has voted as of yet.\n");
			not_voted = 1;
		}
		else
		{
			i = 0;
			while(fgets(buff, 255, (FILE*) fptr))
			{
				token = strtok(buff, "\t");
				strcpy(people_voted[i], token);

				//Testing
				printf("%s\n", people_voted[i]);

				i=i+1;
			}

			i = 0;
			len = sizeof(people_voted[0])/sizeof(people_voted[0][0]);
			found = 0;

			//checking if current client has voted or not
			for(i = 0;i < len; i++)
			{
				if(strcmp(voter_cnic, people_voted[i]) == 0)
				{
					found = 1;
					break;
				}
			}

			//if client has already voted
			if (found == 1)
			{
				memset(smessage,'\0',sizeof(smessage));
				memset(cmessage,'\0',sizeof(cmessage));

				if(people_online[i] == 1)
				{
					strcpy(smessage, "You are already online from another instance. Program is quitting.\n");
					if(send(cid, smessage, strlen(smessage),0) < 0)
					{
						printf("Send Failed. Error!!!!\n");
					}
					close(cid);
					pthread_exit(NULL);
				}

				strcpy(smessage, "Welcome to Online Voting System.\n\nYou have already voted. Program is quitting.\n");
				if(send(cid, smessage, strlen(smessage),0) < 0)
				{
					printf("Send Failed. Error!!!!\n");
				}   
				people_online[index] = 0;
				close(cid);
				pthread_exit(NULL);
			}

			//client has not voted yet
			else
			{
				not_voted = 1;
			}
		}
		if(not_voted == 1)
		{
			//making string of candidates and symbols to send
			memset(smessage,'\0',sizeof(smessage));
			memset(cmessage,'\0',sizeof(cmessage));
			strcpy(smessage, "Welcome to Online Voting System.\n\nVerification Successful!\n\nThe candidates are as follows:\nCandidate Name\tPolling Symbol\n");

			i = 0;
			len = sizeof(candidates[0])/sizeof(candidates[0][0]);

			//Testing
			//printf("'%d'\n", len);

			for(i = 0;i < len; i++)
			{
				//Testing
				//printf("'%s'\n'%s'\n", candidates[i],symbols[i]);
				if((strcmp(candidates[i], "") != 0) && (strcmp(symbols[i], "") != 0))
				{
					strcat(smessage, candidates[i]);
					strcat(smessage, "\t");
					strcat(smessage, symbols[i]);
					strcat(smessage, "\n");

					//Testing
					//printf("'%d'\n", i+1);
				}
			}
			strcat(smessage, "Please enter your desired candidate's symbol.\n");

			//Testing
			//printf("%s\n", smessage);

			//sending candidate list and symbols
			if(send(cid, smessage, strlen(smessage),0) < 0)
			{
				printf("Send Failed. Error!!!!\n");
			}

			memset(smessage,'\0',sizeof(smessage));
			memset(cmessage,'\0',sizeof(cmessage));

			//Receiving response
			if(recv(cid, cmessage, sizeof(cmessage),0) < 0)
			{
				printf("Receive Failed. Error!!!!!\n");
			}

			i = 0;
			len = sizeof(symbols[0])/sizeof(symbols[0][0]);
			found = 0;

			//checking if received symbol exists in list or not
			for(i = 0;i < len; i++)
			{
				if(strcmp(cmessage, symbols[i]) == 0)
				{
					found = 1;
					strcpy(voted_candidate, candidates[i]);
					strcpy(voted_symbol, cmessage);
					break;
				}
			}

			//Candidate symbol matched in list
			if(found == 1)
			{
				//saving vote
				if ((fptr = fopen("Output.txt", "a")) == NULL)
				{
					printf("Error! Couldn't open Output.txt file\n");    
				}
				else
				{
					fprintf(fptr, "%s\t%s\t%s\t%s\n\n", &voter_cnic, &name, &voted_candidate, &voted_symbol);

					fclose(fptr);

					memset(smessage,'\0',sizeof(smessage));
					memset(cmessage,'\0',sizeof(cmessage));
					strcpy(smessage, "Vote successfully casted. Program is quitting.\n");
					if(send(cid, smessage, strlen(smessage),0) < 0)
					{
						printf("Send Failed. Error!!!!\n");
					}
					people_online[index] = 0;
					close(cid);
					pthread_exit(NULL);
				}
			}
			else
			{
				memset(smessage,'\0',sizeof(smessage));
				memset(cmessage,'\0',sizeof(cmessage));
				strcpy(smessage, "The symbol is incorrect. Program is quitting.\n");
				if(send(cid, smessage, strlen(smessage),0) < 0)
				{
					printf("Send Failed. Error!!!!\n");
				}
				people_online[index] = 0;
				close(cid);
				pthread_exit(NULL);
			}
		}
	}

	memset(smessage,'\0',sizeof(smessage));
	memset(cmessage,'\0',sizeof(cmessage));

	people_online[index] = 0;
	close(cid);
	pthread_exit(NULL);
}

int main(void)
{
		int socket_desc, client_sock, client_size; 
		struct sockaddr_in server_addr, client_addr;         //SERVER ADDR will have all the server address
		char server_message[2000], client_message[2000];                 // Sending values from the server and receive from the server we need this
		pthread_t thread;

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
		
		printf("Socket Created\n");
		
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
		
		printf("Bind Done\n");
		
		//Put the socket into Listening State
		
		if(listen(socket_desc, 1) < 0)                               //This listen() call tells the socket to listen to the incoming connections.
		// The listen() function places all incoming connection into a "backlog queue" until accept() call accepts the connection.
		{
				printf("Listening Failed. Error!!!!!\n");
				return -1;
		}

		printf("Listening for Incoming Connections.....\n");

		while(1)
		{
			//Accept the incoming Connections
		
			client_size = sizeof(client_addr);
		
			client_sock = accept(socket_desc, (struct sockaddr*)&client_addr, (void*)&client_size);          // here particular client k liye new socket create kr rhaa ha
			
			if (client_sock < 0)
			{
					printf("Accept Failed. Error!!!!!!\n");
					return -1;
			}

			//Testing
			//printf("%d\n", client_sock);

			pthread_create(&thread, NULL, handle_client, &client_sock);
		}

		//Closing the Socket
		
		close(client_sock);
		close(socket_desc);
		return 0;       
}
