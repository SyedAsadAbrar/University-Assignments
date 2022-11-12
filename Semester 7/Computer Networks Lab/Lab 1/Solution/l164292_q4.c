#include <stdio.h>
#include <string.h>


main()
{
	FILE *fp;
	char buff[255];
	fp = fopen("/home/oracle/Desktop/Lab 1/Input File.txt", "r");
	
	FILE * fp2;
	fp2 = fopen("/home/oracle/Desktop/Lab 1/Output File.txt", "w");
	
	int yes = 1;
	
	int i = 0;
	
	int j;
	
	fscanf(fp, "%s", buff);
	
	while(buff!= NULL)
	{
		while(buff[i]!='\0')
		{
			if((buff[i]=='a' || buff[i]=='e' || buff[i]=='i' || buff[i]=='o' || buff[i]=='u') || (buff[i]=='A' || buff[i]=='E' || buff[i]=='I' || buff[i]=='O' || buff[i]=='U'))
			{
				yes = 1;
				break;
			}
			else
			{
				yes = 0;
			}
			i = i + 1;
		}
		if(yes == 1)
		{
			fputs("Non- Inverted = ", fp2);
			fputs(buff, fp2);
			fputc('\n', fp2);
			fputs("Inverted = ", fp2);
			
			j = strlen(buff) - 1;
						
			while (j >= 0)
			{
				fputc( buff[j], fp2 );
				j = j - 1;
			}
			fputc('\n', fp2);			
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
