#include <stdlib.h>
#include <stdio.h>

int main ( int argc, char *argv[] )
{
    int *array = (int*)malloc(10 * sizeof(int));
    int input;
    int *warden;
    warden = array;
    int count = 0;
    while ( (input = getchar() ) != EOF )
    {
        if ( ( count % 10) == 0 && count != 0) 
        {
        array = (int*)realloc( array,  2 * count * sizeof(int) );
        }
        warden = array + count;
        *warden = input;
        count++;
    }

    for (int i = count - 1; i >= 0; i-- )
    {
        printf( "%c", (char)*(array + i ) );
    }
    free( array );
    printf( "\n");
}