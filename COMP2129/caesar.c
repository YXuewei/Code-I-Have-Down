#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main()
{
    printf("Enter key: ");
    int key; 
    scanf("%d", &key );
    getchar();
    if ( key < 0 || key > 26 )
    {
        printf("\nInvalid key!\n");
        return 1;
    }
    char c;
    int input[100];
    int count = 0;
    printf("Enter line: ");
    while ( (c = getchar() ) != EOF )
    {
        if( c == EOF || c == '\n')
        {
            input[count] = '\0';
            break;
        }
        input[count] = c;
        count++;
    }
    for ( int i = 0; i < count; i++ )
    {
        if ( input[i] <= 90 && input[i] >= 65)
        {
            if ( input[i] + key > 90 )
            {
                input[i] = input[i] + key - 90 + 64;
            }
            else
            {
                input[i] = input[i] + key;
            }
        }
        else if (input[i] >= 97 && input[i] <= 122)
        {
            if ( input[i] + key > 122)
            {
                input[i] = input[i] + key - 122 + 96;
            }
            else
            {
                input[i] = input[i] + key;
            }
        }
    }

    printf("\n");
    for ( int i = 0; i < count; i++ )
    {
        putchar(input[i]);
    }
    printf("\n");
    
}