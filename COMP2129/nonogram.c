#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include "nonogram.h"

int argumentCheck( char *line );
//int imageCheck( int w, int h, int *image[h][w] );

int main(){
    int w, h;
    char line[4];     scanf("%3[^\n]",line);    
    int n = argumentCheck( line );
    if ( n == 0 )
    {
        printf("Cannot decode\n");
        return 1;
    }
    w = atoi( &line[0] );
    h = atoi( &line[2] );
    //getchar();
    int i = 0;
    int image[h][w];
    char input[w+1];
    while( i < h)
    {
        //char input[w];
        scanf("%s",input);
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
        }
        i++;
    }
   /* n = imageCheck( w, h, image[h][w]) ;
    if( n == 0)
    {
        printf("Invalid image data\n");
        return 0;
    }*/

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
            if ( count != 0 && k == w - 1)
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

int argumentCheck( char *line ){
    if ( !isdigit(line[0]) || !isdigit(line[2]) )
        return 0;
    else{
        int x = (int)line[0] - 48;
        int y = (int)line[2] - 48;
        if ( x < 0 || y < 0)
            return 0;
        else
            return 1;
    }
}

/*int imageCheck( int w, int h, int image[h][w])
{
    for ( int i = 0; i < h; i++ )
    {
        for ( int j = 0; j < w; j++ )
        {
            if ( image[i][j] != 0 || image[i][j] != 1)
                return 0;
        }
    }
    return 1;
}*/