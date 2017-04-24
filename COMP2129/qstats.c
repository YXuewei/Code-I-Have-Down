#include <math.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {

	//printf( "M_PI is: %f\n", M_PI);
	float array[3];
	//char *ipt;
	if (argc < 4)
	{
		fprintf(stderr, "error: not enough arguments: ./qstates m, E, h\n");	
		return 1;
	}
    char* n;
    int count = 0;
	for (int i = 1; i < argc; i++)
	{
        n = argv[i];
        float check;
        check = strtof(argv[i], &n);
		if(  n == argv[i] )
        {
            fprintf(stderr, "error: parameter %d is not a floating point number\n", i);
			return 2;
        }
        else if ( check == 0.0 || check < 0.00001 )
        {
			count++;
            continue;
        }
		array[i-1] = check;
	}
    if ( count > 0 )
    {
        fprintf(stderr, "error: %d invalid arguments\n", count);
        return 3;
    }
	float res = 8*M_PI*sqrt(2.0)*(sqrt(array[0]*array[0]*array[0])*sqrt(array[1]))/(array[2]*array[2]*array[2]);
	printf("%f",res);
	return 0;
}