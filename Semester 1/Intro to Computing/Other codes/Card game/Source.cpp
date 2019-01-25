#include<iostream>
using namespace std;
int main()
{
	int card [52]={1,2,3,4,5,6,7,8,9,10,11,12,13,1,2,3,4,5,6,7,8,9,10,11,12,13,1,2,3,4,5,6,7,8,9,10,11,12,13,1,2,3,4,5,6,7,8,9,10,11,12,13};
	int p1[5], p2[5], p3[5], p4[5];
	int index=0;
	for (int i=0;i<52;i++)
	{
		if (card [i] ==7 || card [i] ==11)
		{
			card [i] = 0;
		}
		else if (card [i] ==12 || card [i]==13)
		{
			card [i]=10;
		}
	}
	for (int i=0;i<5;i++)
	{
		p1[i]=card[index];
		index++;
		p2[i]=card[index];
		index++;
		p3[i]=card[index];
		index++;
		p4[i]=card[index];
		index++;
	}
	int open = card [index];
	index++;
	int player=1;
	while (index<52)
	{
		if (player==1)
		{
			int max=p1[0];
			int max_index=0;
			for (int i=1;i<5;i++)
			{
				if (max<p1[i])
				{
					max=p1[i];
					max_index=i;
				}
			}
			if (open<max)
			{
				swap(p1[max_index],open);
			}
			else
			{
				open=card[index];
				index++;
				if (open<max)
				{
				swap(p1[max_index],open);
				}
			}
			player=2;
		}
		if (player==2)
		{
			int max=p2[0];
			int max_index=0;
			for (int i=1;i<5;i++)
			{
				if (max<p2[i])
				{
					max=p2[i];
					max_index=i;
				}
			}
			if (open<max)
			{
				swap(p2[max_index],open);
			}
			else
			{
				open=card[index];
				index++;
				if (open<max)
				{
				swap(p2[max_index],open);
				}
			}
			player=3;
		}
		if (player==3)
		{
			int max=p3[0];
			int max_index=0;
			for (int i=1;i<5;i++)
			{
				if (max<p3[i])
				{
					max=p3[i];
					max_index=i;
				}
			}
			if (open<max)
			{
				swap(p3[max_index],open);
			}
			else
			{
				open=card[index];
				index++;
				if (open<max)
				{
				swap(p3[max_index],open);
				}
			}
			player=4;
		}
		if (player==4)
		{
			int max=p4[0];
			int max_index=0;
			for (int i=1;i<5;i++)
			{
				if (max<p4[i])
				{
					max=p4[i];
					max_index=i;
				}
			}
			if (open<max)
			{
				swap(p4[max_index],open);
			}
			else
			{
				open=card[index];
				index++;
				if (open<max)
				{
				swap(p4[max_index],open);
				}
			}
			player=1;
		}
	}
	int sum1=0;
	int sum2=0;
	int sum3=0;
	int sum4=0;
	for (int i=0;i<5;i++)
	{
		sum1=sum1+p1[i];
		sum2=sum2+p2[i];
		sum3=sum3+p3[i];
		sum4=sum4+p4[i];
	}
	cout<<sum1<<endl;
	cout<<sum2<<endl;
	cout<<sum3<<endl;
	cout<<sum4<<endl;
	system("pause");
	return 0;
}