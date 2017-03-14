#include <stdio.h>
#include <stdlib.h>

int main ( int argc, char *argv[] )
{
    if ( argc != 4 )
        printf( "Invalid arguments number\n");
    else if ( atoi(argv[2]) < 0 )
        printf( "Invalid start position\n");
    else if ( atoi(argv[3]) < 0 )
        printf( "Invalid iteration count");
    else
    {   
        printf("%s,%s", argv[2], argv[3] );
        printf("Yes");
    }
}