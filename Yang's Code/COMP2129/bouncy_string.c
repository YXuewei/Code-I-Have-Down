#include <stdio.h>

int main ( int argc, char *argv[] )
{
    if ( argc != 4 )
        printf( "Invalid arguments number\n");
    else if ( ( argv[2] - '0') < 0 )
        printf( "Invalid start position\n");
    else if ( (argv[3] - '0') < 0 )
        printf( "Invalid iteration count");
    else
    {
        printf("Yes");
    }
}