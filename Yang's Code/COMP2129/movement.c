#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

void bound( int width);
int argumentCheck( int argc, char *argv[] );
//void printBoard( int width, int height, int x, int y, int *board[]);


int main( int argc, char *argv[] )
{
    int argument = argumentCheck( argc, argv );
    switch ( argument )
    {
        case 0:
            break;
        case 1:
            printf( "Invalid Number Of Arguments\n");
            return 1;
        case 2:
            printf("Invalid Width\n");
            return 1;
        case 3:
            printf("Invalid Height\n");
            return 1;
        case 4:
            printf( "Invalid Character Properties\n");
            return 1;
    }

    int width, height, x, y, limit;
    width = atoi(argv[1]);
    height = atoi(argv[2]);
    x = atoi(argv[3]);
    y = atoi(argv[4]);
    limit = atoi(argv[5]);
    int move = limit;

    bound( width );
    int board[height][width];
    board[y][x] = limit;
    //fill coloum where (x,y) is
    for ( int i = x - 1; i >= 0; i-- )
    {
        board[y][i] = move - 1;
        move--;
    }
    move = limit;
    for ( int i = x + 1; i < width; i++ )
    {
        board[y][i] = move - 1;
        move--;
    }

    int col = y + 1;
    for ( int  i  = 0; i < height - y - 1; i++ )
    {
        for ( int j = 0; j < width; j++ )
        {
            board[col][j] = board[col-1][j] - 1;
        }
        col++;
    }
    col = y - 1;
    for ( int i = 0; i < y ; i++ )
    {
        for ( int j = 0; j < width;j++ )
        {
            board[col][j] = board[col+1][j] - 1;
        }
        col--;
    }

   // printBoard( width, height, x, y, bord[height][width] );
   for ( int i = 0; i < height; i++ )
    {
        printf("|");
        for ( int j = 0; j < width; j++ )
        {
            if ( j == x && i == y )
            {
                printf("C");
                printf("|");
            }
            else if ( board[i][j] < 0 )
            {
                printf(" ");
                printf("|");
            }
            else
            {
                printf("%d", board[i][j]);
                printf("|");
            }
        }
        printf("\n");
    }
    bound(width);
    return 0;
}

void  bound( int width)
{
    putchar('+');
    for ( int i = 0; i < width * 2 - 1; i++)
        putchar('-');
    putchar('+');
    printf("\n");
    return;
}

int argumentCheck( int argc, char *argv[])
{
    if (argc != 6)
        return 1; 
    int width, height;
    width = atoi( argv[1]);
    if (width <= 0 )
        return 2;
    height = atoi( argv[2]);
    if ( height <= 0 )
        return 3;
    if ( !isdigit(argv[3][0] ) )
        return 4;
    int x = atoi( argv[ 3 ]);
    if ( x < 0 || x > width )
        return 4;
    if ( !isdigit(argv[4][0]) )
        return 4;
    int y = atoi( argv[4]);
    if ( y < 0 || y > height )
        return 4;
    int limit = atoi( argv[5]);
    if (limit < 0 || !isdigit((int)argv[5][0]))
        return 4;
    return 0;
}

/*void printBoard( int width, int height, int x, int y, int *board[])
{
    for ( int i = 0; i < height; i++ )
    {
        printf("|");
        for ( int j = 0; j < width; j++ )
        {
            if ( j == x && i == y )
            {
                printf("C");
                printf("|");
            }
            else if ( board[i][j] < 0 )
            {
                printf(" ");
                printf("|");
            }
            else
            {
                printf("%d", board[i][j]);
                printf("|");
            }
        }
        printf("\n");
    }
    return;
}*/