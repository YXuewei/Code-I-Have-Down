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
    float diff = result - 1.618;
    float result2 = (a + b)/b;
    float diff2 = result2 - 1.618;
    if ( ( diff < 0.0005 && diff > -0.0005 ) || ( diff2 < 0.0005 && diff2 > -0.0005) )
    {
        printf("\nGolden ratio!\n");
    }
    else
    {
        printf("\nMaybe next time.\n");
    }
    return 0;
}