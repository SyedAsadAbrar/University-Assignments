#include <stdio.h>
#include <stdlib.h>

int main()
{
	FILE *fp;
	char buff[255];
	fp = fopen("/proc/stat", "r");
	
	int count = 0;
	int time = 0;
	int total_time = 0;
	int idle_time = 0;

	double old = 0;
	double new = 0;
	double diff = 0;	

	fscanf(fp, "%s", buff);
	
	while(count < 7)
	{
		fscanf(fp, "%i", &time);
		//printf("%d \n", time);	
		total_time = total_time + time;
		if(count == 3 || count == 4)
		{
			idle_time = idle_time + time;
		}		
		count = count + 1;
	}

	old = (double)idle_time/(double)total_time;
	old = old * 100;

	total_time = 0;
	idle_time = 0;
	count = 0;

	fclose(fp);

	sleep(1);

	while(1)
	{
		fp = fopen("/proc/stat", "r");

		fscanf(fp, "%s", buff);
	
		while(count < 7)
		{
			fscanf(fp, "%i", &time);
			//printf("%d \n", time);	
			total_time = total_time + time;
			if(count == 3 || count == 4)
			{
				idle_time = idle_time + time;
			}		
			count = count + 1;
		}

		new = (double)idle_time/(double)total_time;
		new = new * 100;
				
		total_time = 0;
		idle_time = 0;
		count = 0;

		diff = new + old;

		diff = diff/2; 

		printf("Average CPU Utilization Percentage: %f \n", diff);

		fclose(fp);

		sleep(1);
	}

	return 0;
}
