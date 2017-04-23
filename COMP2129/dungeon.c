#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct room room;
struct room
{
	char* name;
	room* N;
	room* S;
	room* E;
	room* W; 
};
typedef struct list list;
struct list
{
	room* curr;
	list* next;
	list* prev;
};
list* rlist;
int count = 0;
room* current_room;
void append( list* a, list* b);
void take_command( char* comm );
int command_check( char* comm );
void print_room( room* r );
void redirection( room* r );
int main(int argc, char** argv) 
{
	list* warden = rlist;
	FILE* fp;
	fp = fopen(argv[1], "r");
	if ( fp == NULL )
	{
		printf("No Level File Specified\n");
		return 1;
	}
	char input[8192];
	fgets(input, 8192,fp);
	char* token;
	do
	{
		token = strtok( input, " ");
		list* temp = malloc( sizeof( struct list ) );
		room* r_temp = malloc ( sizeof( struct room ) );
		r_temp->N = NULL;
		r_temp->S = NULL;
		r_temp->E = NULL;
		r_temp->W = NULL;
		//finished intialize of room
		temp->curr = r_temp;
		temp->curr->name = token;
		if ( strcmp( token, "START") == 0 )
		{
			current_room = r_temp;
		}
		append( rlist, temp );
		rlist = temp;
		count++;
	}
	while( token != NULL );
	append( rlist, warden);// finished reading room list


	while( feof(fp) )
	{
		fgets( input, 8192, fp );
		token = strtok( input, " > ");
		list* temp = warden;
		for ( int i = 0; i < count; i++ )
		{
			if ( strcmp( temp->curr->name, token ) != 0 )
			{
				temp = temp->next;
			}
			else
			{
				break;
			}
		}
		token = strtok( NULL, " > ");
		char direction = token[0];
		token = strtok( NULL, " > ");
		list* temp2 = warden;
		for ( int i = 0; i < count; i++ )
		{
			if ( strcmp( temp2->curr->name, token ) != 0 )
			{
				temp2 = temp->next;
			}
			else
			{
				break;
			}
		}
		if ( direction == 'N')
		{
			temp->curr->N = temp2->curr;
		}
		else if ( direction == 'S')
		{
			temp->curr->S = temp2->curr;
		}
		else if ( direction == 'E')
		{
			temp->curr->E = temp2->curr;
		}
		else
		{
			temp->curr->W = temp2->curr;
		}
	}

	char command[100];
	while ( 1 )
	{
		take_command( command );
		int comm = command_check( command );
		switch( comm )
		{
			case 0Q:
			case 1N;
			case 2S;
			case 3E;
			case 4W;
			default:
		}
	}

	return 0;
}

void take_command( char* comm )
{
	char c;
	int count = 0;
	while( (c = getchar() ) != EOF )
	{
		if ( c == '\n')
		{
			comm[count] = '\0';
			return;
		}
		else
		{
			comm[count] = c;
			count++;
		}
	}
}

int command_check( char* command )
{
	if ( strcmp( command, "NORTH") == 0 )
	{
		return 1;
	}
	else if ( strcmp( command, "SOUTH") == 0 )
	{
		return 2;
	}
	else if ( strcmp( command, "EAST") == 0 )
	{
		return 3;
	}
	else if ( strcmp( command, "WEST") == 0 )
	{
		return 4;
	}
	else if ( strcmp ( command. "QUIT") == 0 )
	{
		return 0;
	}
	else
	{
		return 5;
	}
}

void print_room( room* r )
{
	printf("%s\n",r->name);
	printf(' ');
	for ( int i = 0; i < 7; i++ )
	{
		//printf("-");
		if ( i == 3 && r->N != NULL )
		{
			printf('N');
		}
		else
		{
			printf("-");
		}
	}
	printf(" \n");

	for ( int i = 0; i < 5; i++ )
	{
		if ( i == 2 && r->W != NULL )
		{
			printf("W");
		}
		else
		{
			printf("|");
		}
		for( int j = 0; j < 7; j++ )
		{
			printf(" ");
		}
		if ( i == 2 && r->E != NULL )
		{
			printf("E");
		}
		else
		{
			printf("|\n");
		}
	}

	printf(' ');
	for ( int i = 0; i < 7; i++ )
	{
		if ( i == 3 && r->N != NULL )
		{
			printf('S');
		}
		else
		{
			printf("-");
		}
	}
	printf(" \n");
	printf("\n");
}

void redirection( room* r , int dir )// 1N | 2S | 3W | 4E
{

}