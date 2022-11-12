#include <stdio.h>

main()
{
	FILE *fp;
	char buff[255];
	fp = fopen("/home/oracle/Desktop/Lab 1/Input File.txt", "r");
	
	FILE * fp2;
	fp2 = fopen("/home/oracle/Desktop/Lab 1/Output File.txt", "w");
	
	int yes = 1;
	
	int i = 0;
	
	fscanf(fp, "%s", buff);
	
	while(buff!= NULL)
	{
		//printf("%s", "MAIN WHILE LOOP\n");
		while(buff[i]!='\0' && yes == 1)
		{
			//printf("%s", "INNER WHILE LOOP\n");
			if((buff[i]>='a' && buff[i]<='z') || (buff[i]>='A' && buff[i]<='Z'))
			{
				//printf("%c", buff[i]);
				yes = 0;
			}
			//printf("%c", i);
			i = i + 1;
		}
		if(yes == 1)
		{
			fputs(buff, fp2);
			fputs(" ", fp2);
		}
		fscanf(fp, "%s", buff);
		i=0;
		yes = 1;
		if(fgetc(fp)==EOF)
		{
			return;
		}
	}
}
