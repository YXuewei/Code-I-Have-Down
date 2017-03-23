#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

void firstLevel( int height );
void baseLevel( int height );

int main(){
    
    printf("Enter height: ");
    char ch[100];
    scanf("%s", ch);
    printf("\n");
    if ( isalpha(ch[0]) )
    {
        printf("Invalid height.\n");
        return 1;
    }
    int height = atoi( ch );
    if ( height < 2 || height > 20 )
    {
        printf("Invalid height.\n");
        return 1;
    }
        
    firstLevel( height);
    baseLevel( height);

    return 0;
}

void firstLevel( int height )
{
    int baseLine = height - 1;
    for( int i = 0; i < height; i++ )
    {
        for ( int x = height * 2 - 1; x > i; x -- )
        {
            printf(" ");
        }
        printf("/");
        for ( int j = 0; j < i*2; j++)
        {
            if( i == baseLine )
                printf("_");
            else
                printf(" ");
        }
        printf("\\");
        printf("\n");
    }
    return;
}

void baseLevel( int height )
{
    int baseLine = height - 1;
    for( int i = 0; i < height; i++)
    {
        for ( int j = 0; j < height - i - 1;j++)
            printf(" ");
        printf("/");
        for ( int x = 0; x < i * 2; x++)
        {
            if ( i == baseLine )
                printf("_");
            else
                printf(" ");
        }
        printf("\\");
        for ( int z = 0; z <( (height - 1 ) *2) - (i*2); z++)
            printf(" ");
        printf("/");
        for(int x = 0; x < i * 2; x++)
        {
                if ( i == baseLine )
                printf("_");
            else
                printf(" ");
        }
        printf("\\");
        printf("\n");
    }
}