#include <stdio.h>

main()
{
	FILE *fp;
	char buff[255];
	fp = fopen("/home/oracle/Desktop/Lab 1/Input File.txt", "r");
	
	int c = fgetc(fp);
	
	while(c!=EOF)
	{
		printf("%c", c);
		fgets(buff, 255, (FILE*)fp);
		printf("%s\n", buff );
		c = fgetc(fp);
	}	
}
