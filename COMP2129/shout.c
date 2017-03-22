#include <stdio.h>
#include <string.h>

int main()
{
    char line[100];
    while( fgets(line, 100, stdin) != NULL)
    {
        int n = strlen( line );
        for ( int i = 0; i < n - 1; i++ )
        {
            if ( (int)line[i] >= 97 && (int)line[i] <= 122 )
            {
                char c = (char)( (int)line[i]  - 32 );
                printf("%c", c);
            }
            else
            {
                printf("%c", line[i]);
            }
        }
        printf("\n");
    }
}