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
void handler(int );

int id;
char input[50];
int count = 0;
int num;
pid_t prc[100];
queue_t queue;
int sig;
locker_t locker;
int* p2c[100];
int* c2p[100];

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
                return;
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
    count++;
    int pipefd[2];
    int pipefd2[2];
    pipe( pipefd );
    pipe( pipefd2 );
    p2c[count] = pipefd;
    c2p[count] = pipefd2;
    pid_t pid = fork();
    if ( pid < 0 )
    {
        perror("Unable to fork:");
    }
    else if ( pid == 0 )
    {
        signal( SIGUSR1, handler );
        signal( SIGUSR2, handler );
        char buffer[5];
        while( true )
        {
            int user_id;
            int lock_status;
            read( p2c[count][0], buffer, 5 );
            if ( buffer[0] == 'A' )
            {
                read( p2c[count][0], buffer, 5 );
                user_id = atoi( buffer );
                locker.owned =  user_id;
            }
            else if ( buffer[0] == 'D')
            {
                locker.owned = -1;
            }
            else if ( buff[0] == 'U')
            {
                locker.locked = 0; 
            }
            else if ( buffer[0] == 'L')
            {
                locker.locked = 1;
            }
            else if ( buffer[0] == 'Q')
            {
                write( c2p[count][1], &locker.locked, 1);
                write( c2p[count][1], &locker.owned, 1 );
            }
            else if ( buffer[0] == 'K')
            {
                quit = true;
                close( p2c[count][0] );
                close( p2c[count][1] );
                close( c2p[count][0] );
                close( c2p[count][1] );
            }
        }
    }
    else
    {
        proc[count] = pid;
        printf("New Locker Created: %d\n", count);
    }
}

void handler( int signo )
{
    sig = signo;
    locker.locked = signo;
}

void delete()
{
    write( p2c[id][1], 'K', 1);
    close( p2c[id][0] );
    close( p2c[id][1] );
    close( c2p[id][0] );
    close( c2p[id][1] );
    p2c[id] = NULL;
    c2p[id] = NULL;
}

void query(int flag )
{
    if ( flag == 3 )
    {
        
    }
    else
    {

    }
}