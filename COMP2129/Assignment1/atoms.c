#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "atoms.h"




void print_help();
void start( char *input, int *progress, int *w, int *h, int *n);
int command_check( const char *input );
void take_input( char *input );
void list_init( player_t *p, const int *n );


int main(int argc, char** argv)
{
enum commands{ HELP, QUIT, DISPLAY, START, PLACE, UNDO, STAT,
                        SAVE, LOAD, PLAYFROM} command;

  char input[255];
  int p = 0;
  int *progress;
  progress = &p;
  
  int *width, *height, *n_player;

  player_t *Red = (player_t*)malloc(sizeof(int) + sizeof(char));
  Red->colour = 'R';
  Red->grids_owned = 0;

  player_t *Green = (player_t*)malloc(sizeof(int) + sizeof(char));
  Green->colour = 'G';
  Green->grids_owned = 0;

  player_t *Purple = (player_t*)malloc(sizeof(int) + sizeof(char));
  Purple->colour = 'P';
  Purple->grids_owned = 0;

  player_t *Blue = (player_t*)malloc(sizeof(int) + sizeof(char));
  Blue->colour = 'B';
  Blue->grids_owned = 0;

  player_t *Yellow = (player_t*)malloc(sizeof(int) + sizeof(char));
  Yellow->colour = 'Y';
  Yellow->grids_owned = 0;

  player_t *White = (player_t*)malloc(sizeof(int) + sizeof(char));
  White->colour = 'W';
  White->grids_owned = 0;

  while ( 1)
  {
    take_input( input );
    command = command_check( input );
    switch( command )
    {
      case HELP:
        print_help();
        break;
      case QUIT:
        printf("Bye!\n");
        return 0;
      case DISPLAY:
        return 0;
      case START:
        start( input, progress, width, height, n_player );
        break;
      case PLACE:
        return 0;
      case UNDO:
        return 0;
      case STAT:
        return 0;
      case SAVE:
        return 0;
      case LOAD:
        return 0;
      case PLAYFROM:
        return 0;
      default:
        printf("Invalid command\n");
        break;
    }
  }

  
  return 0;
}

void print_help()
{
  printf("HELP    displays this help message\n");
  printf("Quit    quits the current game\n");
  printf("\n");
  printf("DISPLAY    draws the game board in terminal\n");
  printf("START <width> <height> <number of players>    starts the game\n");
  printf("PLACE <x> <y>    places anatom in a grid space\n");
  printf("UNDO    undoes the last move made\n");
  printf("STAT    displays game statistics\n");
  printf("\n");
  printf("SAVE <filename>    saves the state of the game\n");
  printf("LOAD <filename>    loads a save file\n");
  printf("PLAYFROM <turn>    plays from n steps into the game\n");
}

void start( char *input, int* progress,int *width, int *height, int *n_player)
{
  int array[3];
  int w,h,np,count;
  count = 0;
  char *token;
  char *ptr;
  token = strtok( input, " ");
  //passing 3 times to assign variable w,h,np,count 
  while( token != NULL )
  {
    
    token = strtok( NULL, " ");
    if( token == NULL && count != 3)
    {
      printf("Missing Arugments\n");
      return;
    }
    ptr = token;
    array[count] = strtol(token, &ptr, 10);
    if ( ptr == token )
    {
      printf("Invalid command arguments\n");
      return;
    }
    count++;
    if ( count == 3 )
    {
      break;
    }
  }

  if ( count != 3 )
  {
    printf("Too Many Arguments\n");
    return;
  }

  np = array[0];
  w = array[1];
  h = array[2];

  if ( w <0 || h <0 || np < 2 || np > 6 )
  {
    printf("Invalid command argumetns\n");
    return;
  }

  if ( w * h < np )
  {
    printf("Cannot Start Game\n");
    return;
  }
  
  printf("Game Ready\n");

  *progress = 1;
  *width = w;
  *height = h;
  *n_players = np;
}

int command_check( const char *input )
{
  char *token;
  char *cp = (char*)malloc( 255*sizeof(char));
  strcpy(cp, input );
  token = strtok( cp, " ");
    if ( strcmp( token, "HELP") == 0)
    {
      free(cp);
      return 0;
    }

    if ( strcmp( token, "Quit") == 0 )
    {
      free(cp);
      return 1;
    }

    if ( strcmp( token, "DISPLAY") == 0)
    {
      free(cp);
      return 2;
    }

    if ( strcmp( token, "START") == 0)
    {
      free(cp);
      return 3;
    }

    if ( strcmp( token, "PLACE") == 0)
    {
      free(cp);
      return 4;
    }

    if( strcmp( token, "UNDO") == 0)
    {
      free(cp);
      return 5;
    }

    if ( strcmp( token, "STAT") == 0)
    {
      free(cp);
      return 6;
    }

    if ( strcmp( token, "SAVE") == 0)
    {
      free(cp);
      return 7;
    }

    if ( strcmp( token, "LOAD") == 0)
    {
      free(cp);
      return 8;
    }

    if ( strcmp( token, "PLAYFROM") == 0)
    {
      free(cp);
      return 9;
    }

    free(cp);
    return 10;
}

void take_input( char *input )
{
  int c;
  int count = 0; 
  while ( ( c = getchar() ) != EOF )
  {
    if ( c == '\n')
    {
      input[count] = '\0';
      break;
    }
    else
    {
      input[count] = c;
      count++;
    }
  }
}

void list_init(player_t *p, const int *n )
{
  
}