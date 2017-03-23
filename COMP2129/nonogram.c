#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include "nonogram.h"

int argumentCheck( char x[], char y[]);

int main()
{
    int w, h;
    char arg1[3];
    char arg2[3];    
    scanf("%s", arg1);
    scanf("%s", arg2);   
    int n = argumentCheck( arg1, arg2 );
    if ( n == 0 )
    {
        printf("Cannot decode\n");
        return 1;
    }
    w = atoi( arg1);
    h = atoi( arg2 );
    //getchar();
    int i = 0;
    int image[h][w];
    char input[w+1];
    while( i < h)
    {
        //char input[w];
        
        for ( int j = 0; j < w; j++ )
        {
            if ( input[j] == '0' || input[j] == '1')
            {
                image[i][j] = (int)input[j] - 48;
            }
            else
            {
                printf("Invalid image data\n");
                return 1;
            }
        i++;
        }
    }

    printf("X:\n");
    int count = 0;
    for ( int j = 0; j < h; j++ )
    {
        for( int k = 0; k < w; k++ )
        {
            if(image[j][k] == 1 )
                count += 1;
            else if ( count != 0 )
            {
                printf("%d",count);
                count = 0;
            if( k != w - 1)
                printf(" ");
            }
            if ( k == w - 1)
            {
                printf("%d", count);
                count = 0;
            }
        }
        printf("\n");
    }
    printf("\n");

    printf("Y:\n");
    count = 0;
    for ( int j = 0; j < w ; j++ )
    {
        for ( int k = h - 1; k > 0; k-- )
        {
            if( image[k][j] == 1)
                count += 1;
            else if( count != 0)
            {
                printf("%d", count);
                count = 0;
            if ( k == h - 1)
                printf("\n");
            else
                printf(" ");
            }
        }
    }
    printf("\n");
}

int argumentCheck( char x[], char y[] )
{
    if ( atoi(x) == 0 || atoi(y) == 0)
    {
        return 0;
    }
    return 1;
}