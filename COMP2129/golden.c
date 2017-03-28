#include <stdio.h>
#include <math.h>
#include <ctype.h>
#include <stdlib.h>

int main( int argc, char *argv[])
{
    printf("Enter two numbers: ");
    char n1[10], n2[10];
    scanf("%s", n1);
    scanf("%s", n2);

    float a,b;
    a = atof(n1);
    b = atof(n2);
    if ( a <= 0 || b <= 0)
    {
        printf("\nInvalid input.\n");
        return 1;
    }

    //float gr = 1.618;
    float result = (a + b)/ a;
    result = roundf( result*10.000f)/10.000f;
    if ( result != 1.618)
    {
        printf("\nMaybe Next Time.\n");
    }
    else
    {
        printf("\nGolden Ratio!\n");
    }
    return 0;
}
