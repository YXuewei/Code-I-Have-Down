#include <stdio.h>
#define MAXLINE 100


int main(){
 
    char line[MAXLINE];
    int count;
    scanf("%s", line );
    int c; 
    
    while( line[ 0 ] != '\0' ){
        count = 0;
        c = 0;
        for ( int i = 0; i < MAXLINE; i++ ){
            if ( line[ i ] != '\0' ){                
                ++count;            
                }else{
                    break;
                }
        }
        if ( count >= 5 ){
            printf( "%s\t%d", line, count );
            printf( "\n");
        }
        scanf("%s", line );
    }

}