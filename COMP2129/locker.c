#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <string.h>
#include <stdbool.h>
#include "locker.h"

int ccheck();
void ctake();
void create();
void delete();
void query( int);
void lock( int );
void attach(int );

int id;
char input[50];
int count = 0;
int num;
pid_t prc[100];
queue_t queue;

int main()
{
    queue_t warden = queue; 
    int command; 
    bool quit = false;
    while( quit == false )
    {
        ctake();
        command = ccheck(); 
        switch( command )
        {
            case 1:
                create();
                break;
            case 2:
                delete();
                break;
            case 3:
                query( command );
                break;
            case 4:
                query( command ); //query all
                break;
            case 5:
                lock( command );
                break;
            case 6
                lock( command ); //unlock
                break;
            case 7:
                attach( command );
                break;
            case 8:
                attack( command ); // detach
                break;
            case 9:
                quit = true;
                break;
            case 0:
                printf("Invalid Command\n");
                break;
        }
    }
}

void ctake()
{
    char c;
    int count = 0;
    while( ( c = getchar() ) != EOF )
    {
        if ( c == '\n')
        {
            input[count] = '\0';
            return;
        }
        input[count++] = c;
    }
}

int ccheck()
{
    char* token;
    strtok( input, " ");
    if ( strcmp(token, "CREATE") == 0 )
    {
        return 1;
    }
    else if ( strcmp( token, "QUIT") == 0 )
    {
        return 9;
    }
    else if ( strcmp( token,"DELETE") == 0 )
    {
        token = strtok(NULL, " ");
        char* endptr;
        id = strtol( token, endptr, 10 );
        if ( endptr == token )
        {
            return 0;
        }
        return 2; 
    }
    else if ( strcmp( token, "QUERY") == 0 )
    {
        token = strtok( NULL, " " );
        char* endptr;
        id = strtol( token, endptr, 10 );
        if ( endptr == token )
        {
            return 0;
        }
        return 3;
    }
    else if ( strcmp( token, "QUERYALL") == 0 )
    {
        return 4;
    }
    else if ( strcmp( token, "LOCK") == 0 )
    {
        token = strtok( NULL, " ");
        char* endptr;
        id = strtol( token, endptr, 10 );
        if ( endptr == token )
        {
            return 0;
        }
        return 5;
    }
    else if ( strcmp( token, "UNLOCK") == 0 )
    {
        token = strtok( NULL, " ");
        char* endptr;
        id = strtol( token, endptr, 10 );
        if ( endptr == token )
        {
            return 0;
        }
        return 6;
    }
    else if ( strcmp( token, "ATTACH") == 0 )
    {
        token = strtok(NULL, " ");
        char* endptr;
        id = strtol( token, endptr, 10 )
        if ( endptr == token ) 
        {
            return 0;
        }
        return 7;
    }
    else if ( strcmp( token, "DETACH") == 0 )
    {
        token = strtok( NULL, " ");
        char* endptr;
        id = strtol( token, endptr, 10 );
        if ( endptr == token )
        {
            return 0;
        }
        return 8;
    }
    return 0;
}

void create()
{
    pid_t pid = fork();
    if ( pid < 0 )
    {
        perror("Unable to fork:");
    }
    else if ( pid == 0 )
    {
        locker_t *locker = malloc( sizeof( struct lokcer_t ) );
        locker->id = count+1;
        locker->user_id = -1;
        locker->owned = 0; // 0 is not 1 is yes
        
    }
    else
    {
        proc[count++] = pid;
    }
    
}