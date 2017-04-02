#include <stdio.h>
#include <stdlib.h>

int main( int argc, char *argv[] )
{
   if ( argc == 1 ) 
   {
        printf("Missing Argument\n");
        return 1;
   }
    else if ( atoi( argv[ 1 ]) < 0 )
    {
        printf("Invalid Argument\n");
        return 1;
    }

    int height = atoi( argv[1]) + 1;
    int width = height;

    if ( height == 1 )
    {
        printf("1\n");
        return 0;
    }

    long array[height][width];
    //fill first column with 1
    for ( int i = 0; i < height; i++ )
    {
        array[i][0] = 1L;
    }
    // fill everything else with 0; 
    for ( int i = 0; i < height; i++ )
    {
        for ( int j = 1; j < width; j++ )
        {
            array[i][j] = 0L;
        }
    }

    long result = 0L;
    for ( int i = 1; i < height; i++ )
    {
        for ( int j =1; j < width ; j++ )
        {
            result = array[i - 1][j - 1] + array[i - 1][j];
            if ( result == 0L )
            {
                break;
            }
            else
            {
                array[i][j] = result;
            }
        }
    }

    for ( int i = 0; i < height; i++ )
    {
        for ( int j = 0; j < width; j++ )
        {
            if ( array[i][j] != 0L)
            {
                printf("%lu",array[i][j]);
                if ( i== j)
                {
                    printf("\n");
                }
                else
                {
                    printf(" ");
                }
            }
            else
            {
                break;
            }
        }
    }

    return 0;
}