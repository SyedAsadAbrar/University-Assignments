#include<iostream>
using namespace std;
struct node
{
	//numerator and denominator
	int neu, dem;
	//pointer to next node
	node * next;
};
void addToList(int &level, node* &head, node* &tail)
{
	bool levelinc=false;
	if (head==nullptr)
	{
		head=new node;
		tail=new node;
		head->neu=0;                           //list with one node
		head->dem=1;
		head->next=tail;
		tail->neu=1;
		tail->dem=1;
		tail->next=nullptr;
		level++;
	}

	else  // if the head(start) is not null, i.e. there would be already members there 
	{
		node* first=head;
		node* second=head->next;
		while(first!=tail)
		{
			while ((first->dem+second->dem)<=(level+1))
			{
				node * temp = new node;
				temp->neu=first->neu+second->neu;
				temp->dem=first->dem+second->dem;
				first->next=temp;
				temp->next=second;
				second=first->next;
				levelinc=true;
			}
			if ((first->dem+second->dem)>level)
			{
				first=first->next;
				second=second->next;
			}
		}
		if(levelinc==true)
		{
			level++;
		}
		levelinc=false;
	}
}
void removeFromList(int &level, node* &head, node* &tail)
{
	if (tail==head && tail==nullptr)
	{
		cout<<"The list contains no elements, cannot decrease further."<<endl;
		return;
	}
	else if (tail==head->next)
	{
		cout<<"Farey fraction is already at lowest level(i.e 1), cannot decrease further."<<endl;
	}
	else
	{
		node* first=head;
		node* second=(first->next)->next;
		while(first!=tail)
		{
			node* temp=first->next;
			if((first->dem+second->dem)==(temp->dem))
			{
				first->next=temp->next;
				temp->next=nullptr;
				delete temp;
				temp=nullptr;
			}
			first=first->next;
			if(first!=tail)
			{
				second=(first->next)->next;
			}
		}
		level--;
	}
}
void printList(node* head)
{
	if (head!=nullptr)
	{
		node* current=head;
		cout<<"The list is now changed to ";
		cout<<"(";
		while (current!=nullptr)
		{
			cout<<current->neu<<"/"<<current->dem;
			current=current->next;
			if (current!=nullptr)
			{
				cout<<", ";
			}
		}
		cout<<")";
		cout<<endl;
	}
	else
	{
		cout << "ERROR" << endl;
	}
}
void deleteList(node* head)
{
	if (head!=nullptr)
	{
		node* current=head;
		node* del=head;
		while (current!=nullptr)
		{
			current=current->next;
			delete del;
			del=current;
		}
	}
}
void printMenu()
{
	cout<<"Farey fraction"<<endl;
	cout<<"1. Generate Farey fraction of level n."<<endl;
	cout<<"2. Decrease or increase level of farey fraction by 1."<<endl;
	cout<<"3. Change level of Farey fraction to level m."<<endl;
	cout<<"4. Exit"<<endl;
	cout<<endl<<"Please input appropriate integer."<<endl;
}
int main()
{
	int level=0;
	int input,n,m;
	node* head=nullptr;
	node* tail=nullptr;
	printMenu();
	while(true)
	{
		cin>>input;
		if (input==1)
		{
			/*system("cls");*/
			cout<<"You have chosen option 1, please enter integer value for Farey fraction generation."<<endl;
			cin>>n;
			while(n<=0)
			{
				cout<<"Invalid input entered. Entered number cannot be less than 1. Please re-enter."<<endl;
				cin>>n;
			}
			while(level<n)
			{
				addToList(level,head,tail);
			}
			printList(head);
			system("pause");
			/*system("cls");*/
			printMenu();
		}
		else if (input==2)
		{
			/*system("cls");*/
			cout<<"You have chosen option 2. Please choose appropriate integer."<<endl;
			cout<<"1. Decrease by 1 level"<<endl<<"2. Increase by 1 level"<<endl;
			cin>>input;
			if (input==1)
			{
				removeFromList(level,head,tail);
				printList(head);
				system("pause");
				/*system("cls");*/
				printMenu();
			}
			if (input==2)
			{
				addToList(level,head,tail);
				printList(head);
				system("pause");
				/*system("cls");*/
				printMenu();
			}
		}
		else if (input==3)
		{
			/*system("cls");*/
			cout<<"You have chosen option 3. Please input integer m(not less than 1)."<<endl;
			cin>>m;
			while(m<=0)
			{
				cout<<"Invalid input entered. Entered number cannot be less than 1. Please re-enter."<<endl;
				cin>>m;
			}
			m=m-level;
			if (m>=0)
			{
				for(int i=0;i<m;i++)
				{
					addToList(level,head,tail);
				}
			}
			if(m<0)
			{
				m=m*(-1);
				for(int i=0;i<m;i++)
				{
					removeFromList(level,head,tail);
				}
			}
			printList(head);
			system("pause");
			/*system("cls");*/
			printMenu();
		}
		else if (input==4)
		{
			deleteList(head);
			break;
		}
	}
	system("pause");
	return 0;
}