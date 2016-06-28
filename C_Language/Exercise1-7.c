#include <stdio.h>
#include <string.h>
#define MAXLINE 300
#define LIMIT 80

//int getLine( cahr s[], int limit);

int main(){
    char line[MAXLINE];
    scanf("%s", line);
    int len;

    while ( len = getLine( line, MAXLINE ) > 0){
        if ( len > LIMIT){
            printf("%s", line);
        }else{
            printf("The length is less than 80");
        }
        scanf( "%s", line);
        prinf("\n");
    }
}

int getLine( char s[], int limit){
    int c , i;
    c = 0;
    for ( i = 0; i < limit; ++i ){
        if ( s[i] != EOF || s[i] != '\n'){
            c += 1;
        }
    }

    return c;
}
