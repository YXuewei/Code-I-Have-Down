#include <stdio.h>
#include <string.h>

int main()
{
    char line[100];
    while( fgets(line, 100, stdin) != NULL )
    {
        int n = strlen( line );
        for ( int i = n - 2; i >= 0 ; i-- )
        {
            printf( "%c", line[i]);
        }
    printf("\n");
    }
}
