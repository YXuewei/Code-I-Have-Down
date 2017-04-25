#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main()
{
    printf("Enter strand: ");
    int input[100];
    int c;
    int count = 0;
    while( 1 )
    {
        c =getchar();
        if ( c == EOF || c == '\n')
        {
            input[count] = '\0';
            break;
        }
        else
        {
            input[count] = c;
            count++;
        }
    }
    if ( count == 0)
    {
        printf("\n\nNo strand provided.\n");
        return 1;
    }
    for ( int i = 0; i < count; i++ )
    {
        switch( input[i])
        {
            case 65:
                input[i] = 84;
                break;
            case 84:
                input[i] = 65;
                break;
            case 71:
                input[i] = 67;
                break;
            case 67:
                input[i] = 71;
                break;
            case 97:
                input[i] = 116;
                break;
            case 116:
                input[i] = 97;
                break;
            case 103:
                input[i] = 99;
                break;
            case 99:
                input[i] = 103;
                break;
            default:
                input[i] = 120;
        }
    }
    printf("\nComplementary strand is ");
    for ( int i = 0; i < count; i++ )
    {
        putchar(input[i]);
    }
    printf("\n");
}