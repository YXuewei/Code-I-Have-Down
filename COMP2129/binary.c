#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

int calc( int times );
int main()
{
    int input[200];
    int decimal[200];
    int count = 0;
    printf("Enter binary: ");
    char c;
    while ( (c = getchar()) != EOF )
    {
        if (c != '1' && c != '0' && c != '\n')
        {
            printf("\nNot Binary!\n");
            return 1;
        }
        else if ( c == '1')
        {
            input[count] = 1;
            count++;
        }
        else if ( c == '0')
        {
            input[count] = 0;
            count++;
        }
        else
        {
            break;
        }
    }
    int sum = 0;
    int index = 0;
    for ( int i = count - 1; i >= 0; i-- )
    {
        decimal[index] = calc( i );
        index++;
    }
    for ( int i = 0; i < count; i++ )
    {
        sum = sum + (decimal[i] * input[i]);
    }
    printf("\n%d in decimal\n", sum);
}
int calc( int times )
{
    return (int)pow(2.0,(double)times );
}