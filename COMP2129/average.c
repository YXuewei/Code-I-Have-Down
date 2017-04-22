#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char* argv[]) {
	int sum = 0;
	int count = 0;
//	int num;
	char *ipt;
	if(argc == 1)
		return 1;
	for(int i = 1; i < argc; i++)
	{
		ipt = argv[i];
        long len = strlen( argv[i]);
        int num = strtol( argv[i], &ipt, 10);
		if ( ipt != argv[i] + len)
        {
			continue;
        }
	    sum += num;
		count++;
	}
	if(count == 0)
    {
		return 1;
    }
	
	printf("%d",sum/count);
    return 0;
}