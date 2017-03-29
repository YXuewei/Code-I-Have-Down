#include <stdio.h>
#include <string.h>

int argumentCheck( int argc, char **argv);

int main(int argc, char *argv[])
{
    int check = argumentCheck( argc, argv);
    switch ( check )
    {
        case 1:
            break;
        case 2:
            printf("Not enough arguments\n");
            return 1;
        case 3:
            printf("Too many arguments\n");
            return 1;
        case 4:
            printf("Invalid arguments\n");
            return 1;
        case 0:
            printf("No arguments\n");
            return 1;
    }

    char input[100];
    char get;
    int i = 0;
    while ( (get = getchar() ) != EOF )
    {
          for ( int j = 0; j < strlen( argv[1]); j++)
        {
            if ( get == argv[1][j] )
            {
                get = argv[2][j];
                break;
            }
        }
        input[i] = get;
        i++;
    }

    for ( int k = 0; k < i; k++ )
    {
        putchar( input[k]);
    }
}

int argumentCheck( int argc, char **argv )
{
    if ( argc > 3 )
        return 3;
    else if ( argc == 1)
        return 0;
    else if ( argc == 2 )
        return 2;
    else if ( strlen( argv[1]) != strlen( argv[2]))
        return 4;
    return 1;
}