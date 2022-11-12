#include <stdio.h>

main()
{
	FILE *fp;
	char buff[255];
	fp = fopen("/home/oracle/Desktop/Lab 1/Input File.txt", "r");
	
	FILE * fp2;
	fp2 = fopen("/home/oracle/Desktop/Lab 1/Output File.txt", "w");
	
	int c = fgetc(fp);
	
	while(c!=EOF)
	{
		if ((c >= '0' && c<= '9') || (c == '\n'))
		{
			fputc( c, fp2 );
		}
		c = fgetc(fp);
	}
}
