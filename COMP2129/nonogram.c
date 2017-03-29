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
    int input;
    while( i < h)
    {
        getchar();
        for ( int j = 0; j < w; j++ )
        {
            input = getchar();
            if ( input == '0' || input == '1')
            {
                image[i][j] = (int)input - 48;
            }
            else
            {
                printf("Invalid image data\n");
                return 1;
            }
        }
        i++;
    }

    int isEmpty = 1;
    int printed = 0;
    printf("X:\n");
    int count = 0;
    for ( int j = 0; j < h; j++ )
    {
        for( int k = w - 1; k >= 0; k-- )
        {
            if(image[j][k] == 1 )
                count += 1;
            else if ( count != 0 )
            {
                if( printed == 0)
                {
                    printf("%d",count);
                    count = 0;
                    isEmpty = 0;
                    printed = 1;
                }
                else
                {
                    printf(" ");
                    printf("%d", count);
                    count = 0;
                    isEmpty = 0;
                }

            }
            if ( k == 0)
            {
                if( isEmpty == 1 && count == 0)
                {
                    printf("0");
                    printed = 0;
                }
                else if ( count != 0)
                {
                    if( printed == 0)
                    {
                        printf("%d",count);
                        count = 0;
                        isEmpty = 1;
                        printed = 0;
                    }
                    else
                    {
                        printf(" ");
                        printf("%d", count);
                        count = 0;
                        isEmpty = 1;
                        printed = 0;
                    }
                }
                printed = 0;
            }
        }
        printf("\n");
    }
    printf("\n");

    printf("Y:\n");
    count = 0;
    isEmpty = 1;
    printed = 0;
    for ( int j = 0; j < w ; j++ )
    {
        for ( int k = h - 1; k >= 0; k-- )
        {
            if( image[k][j] == 1)
                count += 1;
            else if( count != 0)
            {
                if( printed == 0 )
                {
                    printf("%d", count);
                    count = 0;
                    isEmpty = 0;
                    printed = 1;
                }
                else
                {
                    printf(" ");
                    printf("%d", count);
                    count = 0;
                    isEmpty = 0;
                }
            }
            if ( k == 0)
            {
                if (isEmpty == 1 && count == 0)
                {
                   printf("0"); 
                   printed = 0;
                }
                else if ( count != 0)
                {
                  if( printed == 0)
                    {
                        printf("%d",count);
                        count = 0;
                        isEmpty = 1;
                        printed = 0;
                    }
                    else
                    {
                        printf(" ");
                        printf("%d", count);
                        count = 0;
                        isEmpty = 1;
                        printed = 0;
                    }
                }
                printed = 0;
                isEmpty = 1;
            }
        }
        printf("\n");
    }
}

int argumentCheck( char x[], char y[] )
{
    if ( atoi(x) <= 0 || atoi(y) <= 0)
    {
        return 0;
    }
    return 1;
}