#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct room room;
struct room
{
    char name[100];
    room *N;
    room *S;
    room *E;
    room *W;
};
typedef struct list list;
struct list
{
    room *curr;
    list *next;
    list *prev;
};
list* rlist;
int count = 0;
room *current_room;
int set_start = 0;

void append(list *a, list *b);
void take_command(char *comm);
int command_check(char *comm);
void print_room();
void redirection(int dir);

int main(int argc, char **argv)
{
	rlist = malloc( sizeof ( struct list ));
	list* warden = rlist;
    FILE *fp;
    fp = fopen(argv[1], "r");
    if (fp == NULL)
    {
		//perror("Error");
		printf("No Level File Specified\n");
		free(rlist);
		return 1;
    }
    char input[8192];
    fgets(input, 8192, fp);
    char *token;
	token = strtok(input, " ");
    while ( token != NULL )
    {
		list *temp = malloc(sizeof(struct list));
		room *r_temp = malloc(sizeof(struct room));
		r_temp->N = NULL;
		r_temp->S = NULL;
		r_temp->E = NULL;
		r_temp->W = NULL;
		//finished intialize of room
		temp->curr = r_temp;
		int ln = strlen( token );
		if ( token[ln - 1 ] == '\n')
		{
			token[ln - 1 ] = '\0';
		}
		strcpy(r_temp->name, token );
		if ( set_start == 0 )
		{
		    current_room = r_temp;
			set_start = 1;
		}
		append(rlist, temp);
		rlist = temp;
		count++;
		token = strtok(NULL, " ");
    }
    append(rlist, warden); // finished reading room list

    while (feof(fp) == 0)
    {
		fgets(input, 8192, fp);
		token = strtok(input, " > ");
		list* temp = warden->next;
		for (int i = 0; i < count; i++)
		{
		    if (strcmp(temp->curr->name, token) != 0)
		    {
				temp = temp->next;
		    }
		    else
		    {
				break;
		    }
		}
		token = strtok(NULL, " > ");
		if ( token == NULL )
		{
			break;
		}
		char direction = token[0];
		token = strtok(NULL, " > ");
		int nl = strlen( token );
		token[ nl - 1 ] = '\0';
		list* temp2 = warden->next;
		for (int i = 0; i < count; i++)
		{
			if (strcmp(temp2->curr->name, token )!= 0)
		    {
				temp2 = temp2->next;
		    }
		    else
		    {
				break;
		    }
		}
		if (direction == 'N')
		{
		    temp->curr->N = temp2->curr;
		}
		else if (direction == 'S')
		{
		    temp->curr->S = temp2->curr;
		}
		else if (direction == 'E')
		{
		    temp->curr->E = temp2->curr;
		}
		else
		{
		    temp->curr->W = temp2->curr;
		}
 	}

	    char command[100];
	 print_room();   
		while (1)
	    {
		take_command(command);
		int comm = command_check(command);
		switch (comm) // 0Q | 1N | 2S | 3E | 4W
		{
		case 0:
		    return 0;
		case 1:
		    redirection(1);
		    print_room();
		    break;
		case 2:
		    redirection(2);
		    print_room();
			break;
		case 3:
		    redirection(3);
		    print_room();
			break;
		case 4:
		    redirection(4);
		    print_room();
			break;
		default:
		    printf("What?\n");
		    print_room();
		    break;
		}
	    }

		for( int i = 0; i < count; i++ )	
		{
			list* temp3 = warden;
			warden = warden->next;
			free( temp3->curr );
			free( temp3);
		}

	    return 0;
}

void take_command(char *comm)
{
    char c;
    int count = 0;
    while ((c = getchar()) != EOF)
    {
		if (c == '\n')
		{
		    comm[count] = '\0';
		    break;
		}
		else
		{
		    comm[count] = c;
		    count++;
		}
	}
}

int command_check(char *command)
{
    if (strcmp(command, "NORTH") == 0)
    {
		return 1;
    }
    else if (strcmp(command, "SOUTH") == 0)
    {
		return 2;
    }
    else if (strcmp(command, "WEST") == 0)
    {
		return 3;
    }
    else if (strcmp(command, "EAST") == 0)
    {
		return 4;
    }
    else if (strcmp(command, "QUIT") == 0)
    {
		return 0;
    }
    else
    {
		return 5;
    }
}

void print_room()
{
	printf("\n");
	printf("%s\n", current_room->name);
    printf(" ");
    for (int i = 0; i < 7; i++)
    {
	//printf("-");
		if (i == 3 && current_room->N != NULL)
		{
		    printf("N");
		}
		else
		{
		    printf("-");
		}
	}
    printf(" \n");

    for (int i = 0; i < 5; i++)
    {
		if (i == 2 && current_room->W != NULL)
		{
		    printf("W");
		}
		else
		{
		    printf("|");
		}
		for (int j = 0; j < 7; j++)
		{
		    printf(" ");
		}	
		if (i == 2 && current_room->E != NULL)
		{
		    printf("E\n");
		}
		else
		{
		    printf("|\n");
		}
    }

    printf(" ");
    for (int i = 0; i < 7; i++)
    {
		if (i == 3 && current_room->S != NULL)
		{
		    printf("S");
		}
		else
		{
		    printf("-");
		}
    }
    printf(" \n");
    printf("\n");
}

void redirection(int dir) // 1N | 2S | 3W | 4E
{
    switch (dir)
    {
    case 1:
		if (current_room->N != NULL)
		{
		    current_room = current_room->N;
		}
		else
		{
		    printf("No Path This Way\n");
		}
		return;
    case 2:
		if (current_room->S != NULL)
		{
		    current_room = current_room->S;
		}
		else
		{
		    printf("No Path This Way\n");
		}
	return;
    case 3:
		if (current_room->W != NULL)
		{
		    current_room = current_room->W;
		}
		else
		{
		    printf("No Path This Way\n");
		}
		return;
    case 4:
		if (current_room->E != NULL)
		{
		    current_room = current_room->E;
		}
		else
		{
	   		printf("No Path This Way\n");
		}
		return;
	    }
}

void append(list *a, list *b)
{
    a->next = b;
    b->prev = a;
}