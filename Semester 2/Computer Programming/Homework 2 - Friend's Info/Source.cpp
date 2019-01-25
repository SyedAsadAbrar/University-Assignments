#include<iostream>
#include<fstream>
#include<cstring>
#include<string.h>
#include <windows.h> 
using namespace std;
void PrintMenu()
{
	cout<<"1) Add new user"<<endl;
	cout<<"2) Make friends"<<endl;
	cout<<"3) Remove friends"<<endl;
	cout<<"4) Remove User"<<endl;
	cout<<"5) Print Friends"<<endl;
	cout<<"6) Print Friends of Friends"<<endl;
	cout<<"7) Print Mutual Friends"<<endl;
	cout<<"8) Print Likely Friends"<<endl;
	cout<<"9) Save"<<endl;
	cout<<endl;
	cout<<"Enter numbers in 1-9 range."<<endl;
}
void DisplayFriends(int** friends, int n)
{
	cout<<"Friends Display"<<endl;
	cout<<n<<endl;
	for (int i=0;i<n;i++)
	{
		cout<<i;
		if (friends[i]!=nullptr)
		{
			for (int j=0;friends[i][j]!=-1;j++)
			{
				cout<<",";
				cout<<friends[i][j];
			}
		}
		cout<<endl;
	}
	cout<<endl;
}
int * makeIntArray(int n)
{
	if(n==0)
	{
		return nullptr;
	}
	return new int[n];
}
int * extendBy1(int * arr1, int n)
{
	int * arr2=makeIntArray(n+1);
	for (int i=0;i<n;i++)
	{
		arr2[i]=arr1[i];
	}
	delete [] arr1;
	return arr2;
}
int ** addUser(int ** arr1, int n)
{
	int ** arr2=new int*[n+1];
	for (int i=0;i<n;i++)
	{
		arr2[i]=arr1[i];
	}
	delete [] arr1;
	return arr2;
}
int * shrinkBy1(int * arr1, int n, int del)
{
	int * arr2=makeIntArray(n-1);
	int iterator=0;
	for (int i=0;i<n;i++)
	{
		while (arr1[i]!=del)
		{
			arr2[iterator]=arr1[i];
			iterator++;
			break;
		}
	/*	cout<<arr2[iterator-1]<<" ";*/
	}
	delete [] arr1;
	return arr2;
}
int ** RemoveUser(int **arr1, int n, int del)
{
	int ** arr2=new int*[n-1];
	int iterator=0;
	for (int i=0;i<n;i++)
	{
		while (i!=del)
		{
			arr2[iterator]=arr1[i];
			iterator++;
			break;
		}
	/*	cout<<arr2[iterator-1]<<" ";*/
	}
	delete [] arr1;
	return arr2;
}
//bool found(int ** friends, int index, int tofind)
//{
//	bool present=false;
//	if (friends[index]!=nullptr)
//	{
//		
//	}
//}
int countFriends(int ** friends, int &size, int index)
{
	int j=0;
	if (friends[index]==nullptr)
	{
		size=0;
		return size;
	}
	while(friends[index][j]!=-1)
	{
		j++;
		size++;
	}
	return size;
}
//int* addFriend(int n)
//{
//	
//}
//int* RemoveFriend(int n)
//{
//	
//}
int main()
{
	ifstream friendsinfo("friendinfo.txt");
	/*for (int i=0; i<n; i++)
	{
		friends[i] = new int;
	}*/
	
	if (friendsinfo.is_open())
	{
		int n, input, user1, user2, count=2, temp;
		bool found=false;
		friendsinfo>>n;
		int**friends = new int*[n];
		char buff[500];
		int j=0;
		friendsinfo.getline(buff, 500);
		for (int i=0;i<n;i++)
		{
			friendsinfo.getline(buff, 500);
			char *token=strtok(buff,",");
			token=strtok(NULL,",");
			if (token==NULL)
			{
				friends[i]=nullptr;
			}
			while (token!=NULL)
			{
				friends[i]=new int[2];
				while (token!=NULL)					//Loop to store friends for a user
				{
					friends[i][j]=atoi(token);
					/*cout<<friends[i][j];*/		//for testing purposes
					j++;
					token=strtok(NULL,",");
					if (j==count)
					{
						friends[i]=extendBy1(friends[i],count);
						count++;
					}
				}
				friends[i][j]=-1;
				/*cout<<"--";						//for testing purposes
				for (int k=0;k<=j;k++)
				{
					cout<<friends[i][k]<<endl;
				}
				cout<<"--";*/
			}
			j=0;
			count=2;
		}
		DisplayFriends(friends,n);
		friendsinfo.close();
		PrintMenu();
		while(true)
		{
			cin>>input;
			if (input==1)
			{
				friends=addUser(friends,n);
				friends[n]=nullptr;
				system("cls");
				n=n+1;
				DisplayFriends(friends,n);
				PrintMenu();
			}
			if (input==2)
			{
				temp=0;
				cout<<"Enter first user"<<endl;
				cin>>user1;
				while (user1>=n)
				{
					cout<<"Entered user is out-of-bounds. Please re-enter first user."<<endl;
					cin>>user1;
				}
				cout<<"Enter second user"<<endl;
				cin>>user2;
				while (user2>=n)
				{
					cout<<"Entered user is out-of-bounds. Please re-enter second user."<<endl;
					cin>>user2;
				}
				if (countFriends(friends,temp,user1)==0)
				{
					friends[user1]=new int[2];
					friends[user1][0]=user2;
					friends[user1][1]=-1;
				}
				else
				{
					friends[user1]=extendBy1(friends[user1],temp);
					friends[user1][temp]=user2;
					friends[user1]=extendBy1(friends[user1],temp+1);
					friends[user1][temp+1]=-1;
					temp=0;
				}
				if (countFriends(friends,temp,user2)==0)
				{
					friends[user2]=new int[2];
					friends[user2][0]=user1;
					friends[user2][1]=-1;
				}
				else
				{
					friends[user2]=extendBy1(friends[user2],temp);
					friends[user2][temp]=user1;
					friends[user2]=extendBy1(friends[user2],temp+1);
					friends[user2][temp+1]=-1;
					temp=0;
				}
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
			}
			if (input==3)
			{
				temp=0;
				bool found=false;
				cout<<"Enter first user"<<endl;
				cin>>user1;
				while (user1>=n)
				{
					cout<<"Entered user is out-of-bounds. Please re-enter first user."<<endl;
					cin>>user1;
				}
				cout<<"Enter second user"<<endl;
				cin>>user2;
				while (user2>=n)
				{
					cout<<"Entered user is out-of-bounds. Please re-enter second user."<<endl;
					cin>>user2;
				}
				while(friends[user1][temp]!=-1)
				{
					if (friends[user1][temp]==user2)			// to check if second user is a friend of first user and vice versa 
					{
						found=true;
						break;
					}
					temp++;
				}
				temp=0;
				if (friends[user1]!=nullptr && found==true)
				{
					countFriends(friends,temp,user1);
					temp++;
					friends[user1]=shrinkBy1(friends[user1],temp,user2);
					temp=0;
				}
				if (friends[user2]!=nullptr && found==true)
				{
					countFriends(friends,temp,user2);
					temp++;
					friends[user2]=shrinkBy1(friends[user2],temp,user1);
					temp=0;
				}
				else
				{
					cout<<"The specified users are not friends with each other."<<endl;
				}
				found=false;
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
			}
			if (input==4)
			{
				temp=0;
				cout<<"Enter user to be removed."<<endl;
				cin>>user1;
				while (user1>=n)
				{
					cout<<"Entered user is out-of-bounds. Please re-enter user."<<endl;
					cin>>user1;
				}
				for (int i=0;i<n;i++)
				{
					temp=countFriends(friends,temp,i);
					temp++;
					for (int j=0;friends[i]!=nullptr && friends[i][j]!=-1;j++)
					{
						if (friends[i][j]==user1)
						{
							friends[i]=shrinkBy1(friends[i],temp,user1);
							break;
						}
					}
					temp=0;
				}
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
				Sleep(1000);
				friends=RemoveUser(friends,n,user1);
				n=n-1;
				Sleep(1000);
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
				for (int i=0;i<n;i++)
				{
					for (int j=0;friends[i]!=nullptr && friends[i][j]!=-1;j++)
					{
						if (friends[i][j]>=user1)
						{
							friends[i][j]--;
						}
					}
				}
				Sleep(1000);
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
			}
			if (input==5)
			{
				cout<<"Enter user whose friends are to be printed."<<endl;
				cin>>user1;
				while (user1>=n)
				{
					cout<<"Entered user is out-of-bounds. Please re-enter user."<<endl;
					cin>>user1;
				}
				cout<<"The friends of "<<user1<<" are:"<<endl;
				for (int i=0;friends[user1][i]!=-1;i++)
				{
					cout<<(i+1)<<". "<<friends[user1][i]<<endl;
				}
				system("pause");
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
			}
			if (input==6)
			{
				cout<<"Enter user whose friends of friends are to be printed."<<endl;
				cin>>user1;
				while (user1>=n)
				{
					cout<<"Entered user is out-of-bounds. Please re-enter user."<<endl;
					cin>>user1;
				}
				cout<<"The friends of "<<user1<<" are:"<<endl;
				for (int i=0;friends[user1][i]!=-1;i++)
				{
					cout<<(i+1)<<". "<<friends[user1][i]<<endl;
					user2=friends[user1][i];
					cout<<"    The friends of friends of "<<user2<<" are:"<<endl;
					for (int j=0;friends[user2][j]!=-1;j++)
					{
						cout<<"\t"<<(j+1)<<" "<<friends[user2][j]<<endl;
					}
				}
				system("pause");
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
			}
			if (input==7)
			{
				int temp1=0,temp2=0;
				temp=1;
				cout<<"Enter first user"<<endl;
				cin>>user1;
				while (user1>=n)
				{
					cout<<"Entered user is out-of-bounds. Please re-enter first user."<<endl;
					cin>>user1;
				}
				cout<<"Enter second user"<<endl;
				cin>>user2;
				while (user2>=n)
				{
					cout<<"Entered user is out-of-bounds. Please re-enter second user."<<endl;
					cin>>user2;
				}
				for (int i=0;friends[user1][i]!=-1;i++)
				{
					for (int j=0;friends[user2][j]!=-1;j++)
					{
						if (friends[user1][i]==friends[user2][j])
						{
							found=true;
							break;
						}
					}
				}
				if (countFriends(friends,temp1,user1)!=0 && countFriends(friends,temp2,user2)!=0 && found==true)
				{
					cout<<"The mutual friends of "<<user1<<" and "<<user2<<" are:"<<endl;
					for (int i=0;friends[user1][i]!=-1;i++)
					{
						for (int j=0;friends[user2][j]!=-1;j++)
						{
							if (friends[user1][i]==friends[user2][j])
							{
								cout<<temp<<". "<<friends[user1][i]<<endl;
								temp++;
							}
						}
					}
					temp=0;
				}
				else
				{
					cout<<user1<<" and "<<user2<<" have no mutual friends."<<endl;
				}
				system("pause");
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
			}
			if (input==8)
			{
				temp=0;
				bool no=true;
				found=false;
				int x=0, y=0, count1=0, iterator;
				cout<<"Enter user."<<endl;
				cin>>user1;
				while (user1>=n)
				{
					cout<<"Entered user is out-of-bounds. Please re-enter user."<<endl;
					cin>>user1;
				}
				if (friends[user1]!=nullptr)
				{
					int val;
					countFriends(friends,x,user1);
					int * freq = new int [1];
					for(int i=0;i<x;i++)
					{
						val=friends[user1][i];
						for (int j=0;friends[val][j]!=-1;j++)
						{
							if (friends[val][j]!=user1)
							{
								freq[temp]=friends[val][j];
								freq=extendBy1(freq,temp+1);
								/*cout<<freq[temp]<<" ";*/
								temp++;
							}
						}
					}
					iterator=temp-1;
					/*for (int j=0;j<x;j++)
					{
						for (int i=0;i<iterator && iterator!=i;i++)
						{
							if(freq[iterator]==friends[user1][j])
							{
								iterator--;
							}
							if(freq[i]==friends[user1][j])
							{
								y=freq[i];
								freq[i]=freq[iterator];
								freq[iterator]=y;
								iterator--;
							}
						}
					}*/
					/*for (int i=0;i<iterator+1;i++)
					{
						cout<<freq[i]<<" ";
					}
					cout<<endl;*/				//for testing purposes
					int *reducedfreq=new int[1];
					temp=0;
					for (int i=0;i<iterator+1;i++)
					{
						for(int j=0;friends[user1][j]!=-1;j++)
						{
							if (freq[i]==friends[user1][j])
							{
								found=true;
								break;
							}
						}
						if (found==false)
						{
							reducedfreq[temp]=freq[i];
							reducedfreq=extendBy1(reducedfreq,temp+1);
							temp++;
						}
						found=false;
					}
					delete [] freq;
					/*for (int i=0;i<temp;i++)
					{
						cout<<reducedfreq[i]<<" ";
					}*/
					/*temp--;*/
					int*freq1=makeIntArray(temp);
					for (int i=0;i<temp;i++)
					{
						freq1[i]=0;
					}
					for(int i=0;i<temp;i++)
					{
						for (int j=0;j<temp;j++)
						{
							if (reducedfreq[i]==reducedfreq[j])
							{
								freq1[i]++;
							}
						}
					}
					int counter=0;
					int repeat=0;
					cout<<"Suggested friends are ";
					for (int i=0;i<temp;i++)
					{
						if (i!=0)
						{
							for (int k=0;k<=i-1;k++)
							{
								if (reducedfreq[k]==reducedfreq[i])
								{
									repeat=1;
								}

							}
						}
						if (repeat==0)
						{
							cout<<endl<<"-> "<<reducedfreq[i]
							;
							no=false;
						}
						counter=0;
						repeat=0;
					}
					if (no==true)
					{
						cout<<"N/A."<<endl;
					}
					else
					{
						cout<<endl;
					}
				}
				else
				{
					cout<<"The specified user has no friends so likely friends cannot be suggested."<<endl;
				}
				system("pause");
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
			}
			if (input==9)
			{
				ofstream output ("friendinfo.txt");
				if (output.is_open())
				{
					output<<n<<endl;
					for (int i=0;i<n;i++)
					{
						output<<i;
						if (friends[i]!=nullptr)
						{
							for (int j=0;friends[i][j]!=-1;j++)
							{
								output<<",";
								output<<friends[i][j];
							}
						}
						output<<endl;
					}
					output.close();
					break;
				}
				else
				{
					cout<<"File cannot be opened due to unknown errors. :("<<endl;
				}
				system("pause");
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
			}
			else if (input<=0 || input>=10)
			{
				cout<<"Invalid input entered. Please re-enter integral value between 1-9."<<endl;
				system("pause");
				system("cls");
				DisplayFriends(friends,n);
				PrintMenu();
			}
		}
	}
	else
	{
		cout<<"File cannot be opened due to unknown errors. :("<<endl;
	}
	system("pause");
	return 0;
}