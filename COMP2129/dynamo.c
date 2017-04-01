#include <stdlib.h>
#include <stdio.h>

int main ( int argc, char *argv[] )
{
    char *array = (char*)malloc(10 * sizeof(char));
    char input;
    char *warden = array;
    int count = 0;
    while ( (input = getchar() ) != EOF )
    {
        if ( ( count % 10) == 0 ) 
        {
        warden = realloc( warden, count * sizeof(char) );
        }
        *array = input;
        array++;
        count++;
    }

    while ( array != ( warden - 1 ))
    {
        printf( "%c", *array);
        array--;
    }
    free( warden );
    printf( "\n");
}