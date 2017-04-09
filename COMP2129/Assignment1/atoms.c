#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "atoms.h"

void print_help();
void appened(player_t **head, player_t **n);
void player_init(player_t *p, const int *n, const int *progress);
void start(char *input, int *progress, int *w, int *h, int *n);
int command_check(const char *input);
void take_input(char *input);
void place(player_t **curr, char *input, int *width, int *height, grid_t **board, int *move_made, int *n_player, int *progress);
grid_t **board_alloc(int w, int h);
void board_init(grid_t **board, int *width, int *height);
void expand(int w, int h, grid_t **board, player_t *curr, int *width, int *height);
void print_bound(int *width);
void display(grid_t **board, int *width, int *height);
void clean(grid_t **board, int *move_made, int *progress, int *width, int *height, int *n_player, player_t *Red);
void stats(char *input, player_t *current, int progress, int n_player, int move_made);
void load( char *input, int *progress, int *n_player, grid_t **board, player_t **current, int *width, int *height);

grid_t **board;

int main(int argc, char **argv)
{
  enum commands
  {
    HELP,
    QUIT,
    DISPLAY,
    START,
    PLACE,
    UNDO,
    STAT,
    SAVE,
    LOAD,
    PLAYFROM
  } command;

  char input[255];
  int p = 0;
  int *progress;
  progress = &p; // 1 means yes, zero means no

  int *width, *height, *n_player, *move_made;
  //initiate those pointer first
  int a, b, c, d;
  a = 1;
  b = 2;
  c = 3;
  d = 0;
  width = &a;
  height = &b;
  n_player = &c;
  move_made = &d;

  player_t *Red = (player_t *)malloc(sizeof(player_t));
  Red->colour = "Red\0";
  Red->grids_owned = 0;
  Red->next = NULL;
  Red->prev = NULL;
  player_t *current = Red;
  while (1)
  {
    take_input(input);
    command = command_check(input);
    switch (command)
    {
    case HELP:
      print_help();
      break;
    case QUIT:
      clean(board, move_made, progress, width, height, n_player, Red);
      printf("Bye!\n");
      return 0;
    case DISPLAY:
      display(board, width, height);
      break;
    case START:
      start(input, progress, width, height, n_player);
      player_init(Red, n_player, progress);
      board = board_alloc(*width, *height);
      board_init(board, width, height);
      break;
    case PLACE:
      place(&current, input, width, height, board, move_made, n_player,progress);
      break;
    case UNDO:
      return 0;
    case STAT:
      stats(input, Red, *progress, *n_player, *move_made);
      break;
    case SAVE:
      return 0;
    case LOAD:
      load( input, progress,n_player, board, &current, width, height);
      break;
    case PLAYFROM:
      return 0;
    default:
      printf("Invalid command\n");
      //printf("%lu",sizeof(struct grid_t));
      //printf("%lu",sizeof(player_t));
      break;
    }
    /*if (command == QUIT)
    {
      break;
    }*/
  }

  return 0;
}

void clean(grid_t **board, int *move_made, int *progress, int *width, int *height, int *n_player, player_t *Red)
{
  if (board != NULL)
  {
    player_t *temp;
    for (int i = 0; i < *n_player; i++)
    {
      temp = Red;
      Red = Red->next;
      free(temp);
    }

    for (int i = 0; i < *height; i++)
    {
      free(board[i]);
    }
    free(board);
  }
  else
  {
    free(Red);
  }
}
void print_help()
{
  printf("\n");
  printf("HELP displays this help message\n");
  printf("QUIT quits the current game\n");
  printf("\n");
  printf("DISPLAY draws the game board in terminal\n");
  printf("START <number of players> <width> <height> starts the game\n");
  printf("PLACE <x> <y> places an atom in a grid space\n");
  printf("UNDO undoes the last move made\n");
  printf("STAT displays game statistics\n");
  printf("\n");
  printf("SAVE <filename> saves the state of the game\n");
  printf("LOAD <filename> loads a save file\n");
  printf("PLAYFROM <turn> plays from n steps into the game\n");
  printf("\n");
}

void start(char *input, int *progress, int *width, int *height, int *n_player)
{
  int array[3];
  int w, h, np, count;
  count = 0;
  char *token;
  char *ptr;
  token = strtok(input, " ");
  //passing 3 times to assign variable w,h,np,count
  while (token != NULL)
  {

    token = strtok(NULL, " ");
    if (token == NULL && count != 3)
    {
      printf("Missing Arugments\n");
      return;
    }
    ptr = token;
    array[count] = strtol(token, &ptr, 10);
    if (ptr == token)
    {
      printf("Invalid command arguments\n");
      return;
    }
    count++;
    if (count == 3)
    {
      token = strtok(NULL, " ");
      if (token != NULL)
      {
        printf("Too Many Aruguments\n");
        return;
      }
    }
  }

  np = array[0];
  w = array[1];
  h = array[2];

  if (w < 2 || w > 255 || h < 2 || h > 255 || np < 2 || np > 6)
  {
    printf("Invalid command argumetns\n");
    return;
  }

  if (w * h < np)
  {
    printf("Cannot Start Game\n");
    return;
  }

  *progress = 1;
  printf("Game Ready\n");
  printf("Red's Turn\n");
  printf("\n");

  *width = w;
  *height = h;
  *n_player = np;
}

int command_check(const char *input)
{
  char *token;
  char *cp = (char *)malloc(255 * sizeof(char));
  strcpy(cp, input);
  token = strtok(cp, " ");
  if (strcmp(token, "HELP") == 0)
  {
    free(cp);
    return 0;
  }

  if (strcmp(token, "QUIT") == 0)
  {
    free(cp);
    return 1;
  }

  if (strcmp(token, "DISPLAY") == 0)
  {
    free(cp);
    return 2;
  }

  if (strcmp(token, "START") == 0)
  {
    free(cp);
    return 3;
  }

  if (strcmp(token, "PLACE") == 0)
  {
    free(cp);
    return 4;
  }

  if (strcmp(token, "UNDO") == 0)
  {
    free(cp);
    return 5;
  }

  if (strcmp(token, "STAT") == 0)
  {
    free(cp);
    return 6;
  }

  if (strcmp(token, "SAVE") == 0)
  {
    free(cp);
    return 7;
  }

  if (strcmp(token, "LOAD") == 0)
  {
    free(cp);
    return 8;
  }

  if (strcmp(token, "PLAYFROM") == 0)
  {
    free(cp);
    return 9;
  }

  free(cp);
  return 10;
}

void take_input(char *input)
{
  int c;
  int count = 0;
  while ((c = getchar()) != EOF)
  {
    if (c == '\n')
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

void player_init(player_t *p, const int *n, const int *progress)
{
  if (*progress == 1)
  {

    if (*n == 2)
    {
      player_t *Green = (player_t *)malloc(sizeof(struct player_t));
      Green->colour = "Green\0";
      Green->grids_owned = 0;
      Green->next = NULL;
      Green->prev = NULL;

      appened(&p, &Green);
      appened(&Green, &p);
    }
    if (*n >= 3)
    {
      player_t *Green = (player_t *)malloc(sizeof(player_t));
      Green->colour = "Green\0";
      Green->grids_owned = 0;
      Green->next = NULL;
      Green->prev = NULL;

      player_t *Purple = (player_t *)malloc(sizeof(player_t));
      Purple->colour = "Purple\0";
      Purple->grids_owned = 0;
      Purple->next = NULL;
      Purple->prev = NULL;

      appened(&p, &Green);
      appened(&Green, &Purple);
      appened(&Purple, &p);
    }
    if (*n == 4)
    {
      player_t *Green = (player_t *)malloc(sizeof(player_t));
      Green->colour = "Green\0";
      Green->grids_owned = 0;
      Green->next = NULL;
      Green->prev = NULL;

      player_t *Purple = (player_t *)malloc(sizeof(player_t));
      Purple->colour = "Purple\0";
      Purple->grids_owned = 0;
      Purple->next = NULL;
      Purple->prev = NULL;

      player_t *Blue = (player_t *)malloc(sizeof(player_t));
      Blue->colour = "Blue\0";
      Blue->grids_owned = 0;
      Blue->next = NULL;
      Blue->prev = NULL;

      appened(&p, &Green);
      appened(&Green, &Purple);
      appened(&Purple, &Blue);
      appened(&Blue, &p);
    }
    if (*n == 5)
    {

      player_t *Green = (player_t *)malloc(sizeof(player_t));
      Green->colour = "Green\0";
      Green->grids_owned = 0;
      Green->next = NULL;
      Green->prev = NULL;

      player_t *Purple = (player_t *)malloc(sizeof(player_t));
      Purple->colour = "Purple\0";
      Purple->grids_owned = 0;
      Purple->next = NULL;
      Purple->prev = NULL;

      player_t *Blue = (player_t *)malloc(sizeof(player_t));
      Blue->colour = "Blue\0";
      Blue->grids_owned = 0;
      Blue->next = NULL;
      Blue->prev = NULL;

      player_t *Yellow = (player_t *)malloc(sizeof(player_t));
      Yellow->colour = "Yellow\0";
      Yellow->grids_owned = 0;
      Yellow->next = NULL;
      Yellow->prev = NULL;

      appened(&p, &Green);
      appened(&Green, &Purple);
      appened(&Purple, &Blue);
      appened(&Blue, &Yellow);
      appened(&Yellow, &p);
    }

    if (*n == 6)
    {

      player_t *Green = (player_t *)malloc(sizeof(player_t));
      Green->colour = "Green\0";
      Green->grids_owned = 0;
      Green->next = NULL;
      Green->prev = NULL;

      player_t *Purple = (player_t *)malloc(sizeof(player_t));
      Purple->colour = "Purple\0";
      Purple->grids_owned = 0;
      Purple->next = NULL;
      Purple->prev = NULL;

      player_t *Blue = (player_t *)malloc(sizeof(player_t));
      Blue->colour = "Blue\0";
      Blue->grids_owned = 0;
      Blue->next = NULL;
      Blue->prev = NULL;

      player_t *Yellow = (player_t *)malloc(sizeof(player_t));
      Yellow->colour = "Yellow\0";
      Yellow->grids_owned = 0;
      Yellow->next = NULL;
      Yellow->prev = NULL;

      player_t *White = (player_t *)malloc(sizeof(player_t));
      White->colour = "White\0";
      White->grids_owned = 0;
      White->next = NULL;
      White->prev = NULL;

      appened(&p, &Green);
      appened(&Green, &Purple);
      appened(&Purple, &Blue);
      appened(&Blue, &Yellow);
      appened(&Yellow, &White);
      appened(&White, &p);
    }
  }
}
void appened(player_t **head, player_t **n)
{
  (*head)->next = *n;
  (*n)->prev = *head;
}

void place(player_t **curr, char *input, int *width, int *height, grid_t **board, int *move_made, int *n_player, int *progress)
{
  int array[2];
  int w, h;
  char *token;
  char *ptr;
  int count = 0;
  token = strtok(input, " ");
  while (token != NULL)
  {
    token = strtok(NULL, " ");
    if (token == NULL && count != 2)
    {
      printf("Missing Arguments\n");
      return;
    }
    ptr = token;
    array[count] = strtol(token, &ptr, 10);
    if (ptr == token)
    {
      printf("Invalid command argumetens\n");
      return;
    }
    count++;
    if (count == 2)
    {
      token = strtok(NULL, " ");
      if (token != NULL)
      {
        printf("Too Mnay Argumetens\n");
        return;
      }
    }
  }

  w = array[0];
  h = array[1];
  if (w < 0 || h < 0 || w >= *width || h >= *height)
  {
    printf("Invalid Coordiates\n");
    return;
  }
  if (*progress == 0)
  {
    printf("Ganme Not In Progress\n");
    printf("\n");
    return;
  }

  if (board[h][w].owner == NULL)
  {
    board[h][w].owner = *curr;
    board[h][w].atom_count++;
    (*curr)->grids_owned++;
  }
  else if (board[h][w].owner != *curr)
  {
    printf("Cannot Place Atom Here\n");
    return;
  }
  else if (board[h][w].limit > (board[h][w].atom_count + 1))
  {
    board[h][w].atom_count++;
  }
  else
  {
    board[h][w].atom_count = 0;
    board[h][w].owner = NULL;
    (*curr)->grids_owned--;
    if (w == 0 && h == 0)
    {
      expand(w + 1, h, board, *curr, width, height);
      expand(w, h + 1, board, *curr, width, height);
    }
    else if (w == *width - 1 && h == *height - 1)
    {
      expand(w, h - 1, board, *curr, width, height);
      expand(w - 1, h, board, *curr, width, height);
    }
    else if (w == *width - 1 && h == 0)
    {
      expand(w, h + 1, board, *curr, width, height);
      expand(w - 1, h, board, *curr, width, height);
    }
    else if (w == 0 && h == *height - 1)
    {
      expand(w, h - 1, board, *curr, width, height);
      expand(w + 1, h, board, *curr, width, height);
    }
    else if (h == 0 && w != 0 && w != *width - 1)
    {
      expand(w + 1, h, board, *curr, width, height);
      expand(w, h + 1, board, *curr, width, height);
      expand(w - 1, h, board, *curr, width, height);
    }
    else if (h == *height - 1 && w != 0 && w != *width - 1)
    {
      expand(w, h - 1, board, *curr, width, height);
      expand(w + 1, h, board, *curr, width, height);
      expand(w - 1, h, board, *curr, width, height);
    }
    else if (w == 0 && h != 0 && h != *height - 1)
    {
      expand(w, h - 1, board, *curr, width, height);
      expand(w + 1, h, board, *curr, width, height);
      expand(w, h + 1, board, *curr, width, height);
    }
    else if (w == *width - 1 && h != *height - 1 && h != 0)
    {
      expand(w, h - 1, board, *curr, width, height);
      expand(w, h + 1, board, *curr, width, height);
      expand(w - 1, h, board, *curr, width, height);
    }
    else
    {
      expand(w, h - 1, board, *curr, width, height);
      expand(w + 1, h, board, *curr, width, height);
      expand(w, h + 1, board, *curr, width, height);
      expand(w - 1, h, board, *curr, width, height);
    }
  }
  *move_made += 1;
  /*for ( int i = 0; i < *move_made; i++ )
  {
    *curr = (*curr)->next;
  }*/

  int out_player = 0; // number of player doesn't own any grids
  if (*move_made <= *n_player)
  {
    (*curr) = (*curr)->next;
    printf("%s's Turn\n", (*curr)->colour);
    printf("\n");
  }
  else
  {
    player_t *temp = *curr;
    do
    {
      if (temp->grids_owned <= 0)
      {
        out_player++;
      }
      temp = temp->next;
    } while (temp != *curr);
    if (out_player >= *n_player - 1)
    {
      printf("%s Wins!\n", (*curr)->colour);
      exit(0);
    }
    temp = (*curr)->next;
    for (int i = 0; i < *n_player; i++)
    {
      if (temp->grids_owned > 0)
      {
        printf("%s's Turn\n", temp->colour);
        printf("\n");
        (*curr) = temp;
        return;
      }
    }
  }
}

void expand(int w, int h, grid_t **board, player_t *curr, int *width, int *height)
{
  if (board[h][w].owner == NULL)
  {
    board[h][w].owner = curr;
    board[h][w].atom_count++;
    curr->grids_owned++;
  }
  else if (board[h][w].owner == curr && board[h][w].limit > (board[h][w].atom_count + 1))
  {
    board[h][w].atom_count++;
  }
  else if (board[h][w].owner != curr && board[h][w].limit > (board[h][w].atom_count + 1))
  {
    board[h][w].owner->grids_owned--;
    board[h][w].owner = curr;
    board[h][w].atom_count = 1;
  }
  else
  {
    board[h][w].owner->grids_owned--;
    board[h][w].owner = NULL;
    board[h][w].atom_count = 0;
    if (w == 0 && h == 0)
    {
      expand(w + 1, h, board, curr, width, height);
      expand(w, h + 1, board, curr, width, height);
    }
    else if (w == *width - 1 && h == *height - 1)
    {
      expand(w, h - 1, board, curr, width, height);
      expand(w - 1, h, board, curr, width, height);
    }
    else if (w == *width - 1 && h == 0)
    {
      expand(w, h + 1, board, curr, width, height);
      expand(w - 1, h, board, curr, width, height);
    }
    else if (w == 0 && h == *height - 1)
    {
      expand(w, h - 1, board, curr, width, height);
      expand(w + 1, h, board, curr, width, height);
    }
    else if (h == 0 && w != 0 && w != *width - 1)
    {
      expand(w + 1, h, board, curr, width, height);
      expand(w, h + 1, board, curr, width, height);
      expand(w - 1, h, board, curr, width, height);
    }
    else if (h == *height - 1 && w != 0 && w != *width - 1)
    {
      expand(w, h - 1, board, curr, width, height);
      expand(w + 1, h, board, curr, width, height);
      expand(w - 1, h, board, curr, width, height);
    }
    else if (w == 0 && h != 0 && h != *height - 1)
    {
      expand(w, h - 1, board, curr, width, height);
      expand(w + 1, h, board, curr, width, height);
      expand(w, h + 1, board, curr, width, height);
    }
    else if (w == *width - 1 && h != *height - 1 && h != 0)
    {
      expand(w, h - 1, board, curr, width, height);
      expand(w, h + 1, board, curr, width, height);
      expand(w - 1, h, board, curr, width, height);
    }
    else
    {
      expand(w, h - 1, board, curr, width, height);
      expand(w + 1, h, board, curr, width, height);
      expand(w, h + 1, board, curr, width, height);
      expand(w - 1, h, board, curr, width, height);
    }
  }
}

grid_t **board_alloc(int w, int h)
{
  grid_t **board;
  board = malloc(h * sizeof(grid_t));
  for (int i = 0; i < h; i++)
  {
    board[i] = malloc(w * sizeof(grid_t));
  }
  return board;
}

void board_init(grid_t **board, int *width, int *height)
{
  for (int i = 0; i < *height; i++)
  {
    for (int j = 0; j < *width; j++)
    {
      board[i][j].owner = NULL;
      board[i][j].atom_count = 0;
      if (i == 0 && j == 0)
      {
        board[i][j].limit = 2;
      }
      else if (i == *height - 1 && j == *width - 1)
      {
        board[i][j].limit = 2;
      }
      else if (i == 0 && j == *width - 1)
      {
        board[i][j].limit = 2;
      }
      else if (i == *height - 1 && j == 0)
      {
        board[i][j].limit = 2;
      }
      else if (i == 0 && j != 0 && j != *width - 1)
      {
        board[i][j].limit = 3;
      }
      else if (i == *height - 1 && j != 0 && j != *width - 1)
      {
        board[i][j].limit = 3;
      }
      else if (j == 0 && i != 0 && i != *height - 1)
      {
        board[i][j].limit = 3;
      }
      else if (j == *width - 1 && i != *height - 1 && i != 0)
      {
        board[i][j].limit = 3;
      }
      else
      {
        board[i][j].limit = 4;
      }
    }
  }
}

void display(grid_t **board, int *width, int *height)
{
  printf("\n");
  print_bound(width);
  for (int i = 0; i < *height; i++)
  {
    printf("|");
    for (int j = 0; j < *width; j++)
    {
      if (board[i][j].owner != NULL)
      {
        printf("%c%d", board[i][j].owner->colour[0], board[i][j].atom_count);
      }
      else
      {
        printf("  ");
      }
      printf("|");
    }
    printf("\n");
  }
  print_bound(width);
  printf("\n");
}

void print_bound(int *width)
{
  printf("+");
  for (int i = 0; i < (*width) * 3 - 1; i++)
  {
    printf("-");
  }
  printf("+\n");
}

void stats(char *input, player_t *current, int progress, int n_player, int move_made)
{
  char *token;
  token = strtok(input, " ");
  token = strtok(NULL, " ");
  if (token != NULL)
  {
    printf("\n");
    printf("Invalid Aruguments\n");
    printf("\n");
    return;
  }
  else if (progress == 0)
  {
    printf("\n");
    printf("Game Not in Progress\n");
    printf("\n");
    return;
  }
  else
  {
    for (int i = 0; i < n_player; i++)
    {
      printf("Player %s:\n", current->colour);
      if (current->grids_owned <= 0)
      {
        if (move_made <= n_player)
        {
          printf("Grid Count: %d\n", current->grids_owned);
          printf("\n");
        }
        else
        {
          printf("Lost\n");
          printf("\n");
        }
      }
      else
      {
        printf("Grid Count: %d\n", current->grids_owned);
        printf("\n");
      }
      current = current->next;
    }
  }
}

void load( char *input, int *progress, int *n_player, grid_t **board, player_t **current, int *width, int *height)
{
  char *token;
  token = strtok(input, " ");
  token = strtok(NULL, " ");
  if ( token == NULL )
  {
    printf("Missing Arguments\n");
    printf("\n");
    return;
  }
  else
  {
    FILE *fp;
    fp = fopen(token, "r");
    if ( fp == NULL )
    {
      printf("Cannot Load Save\n");
      printf("\n");
      return;
    }
    else if( *progress == 1 )
    {
      printf("Restart Application To Load Save\n");
      printf("\n");
      return;
    }
    else
    {
      fread(width, 3, 1, fp);
      fread(height, 3, 1, fp);
      fread(n_player, 1 ,1 ,fp);
      player_init(*current, n_player, progress );
      board = board_alloc(*width, *height);
      board_init(board,width, height);
      fclose(fp);
      *progress = 1;
      printf("Game Loaded\n");
      printf("\n");
      printf("Game Ready\n");
      printf("%s Turn\n", (*current)->colour);
    }
  }
}