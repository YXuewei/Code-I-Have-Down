#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "atoms.h"

void print_help();
void appened(player_t *head, player_t *n);
void player_init(player_t *p, const int *n, const int *progress);
void start(char *input, int *progress, int *w, int *h, int *n);
int command_check(const char *input);
void take_input(char *input);
void place(player_t *pl, char *input, char *b, int *width, int *height, opd_grid *grid_po, move_list *m_list);
void expand();

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

  int *width, *height, *n_player;
  //initiate those pointer first
  int a, b, c;
  a = 1;
  b = 2;
  c = 3;
  width = &a;
  height = &b;
  n_player = &c;

  char game_board[255][255];
  for (int i = 0; i < 255; i++)
  {
    for (int j = 0; j < 255; j++)
    {
      game_board[i][j] = 'n';
    }
  }
  opd_grid *g_list = (opd_grid *)malloc(sizeof(opd_grid));
  opd_grid *grid_po = g_list;

  move_list *m_list = (move_list *)malloc(sizeof(move_list));
  move_list *m_warden = m_list;

  player_t *Red = (player_t *)malloc(sizeof(player_t));
  Red->colour = 'Red\0';
  Red->grids_owned = 0;
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
      printf("Bye!\n");
      return 0;
    case DISPLAY:
      return 0;
    case START:
      start(input, progress, width, height, n_player);
      player_init(Red, n_player, progress);
      break;
    case PLACE:
      place(current, input, game_board, width, height, grid_po);
      break;
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

  printf("Game Ready\n");
  printf("Red's Turn\n'");

  *progress = 1;
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

  if (strcmp(token, "Quit") == 0)
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
      player_t *Green = (player_t *)malloc(sizeof(player_t));
      Green->colour = 'Green\0';
      Green->grids_owned = 0;

      appened(p, Green);
      appened(Green, p);
    }
    if (*n >= 3)
    {
      player_t *Green = (player_t *)malloc(sizeof(player_t));
      Green->colour = 'Green\0';
      Green->grids_owned = 0;

      player_t *Purple = (player_t *)malloc(sizeof(player_t));
      Purple->colour = 'Purple\0';
      Purple->grids_owned = 0;

      appened(p, Green);
      appened(Green, Purple);
      appened(Purple, p);
    }
    if (*n == 4)
    {
      player_t *Green = (player_t *)malloc(sizeof(player_t));
      Green->colour = 'Green\0';
      Green->grids_owned = 0;

      player_t *Purple = (player_t *)malloc(sizeof(player_t));
      Purple->colour = 'Purple\0';
      Purple->grids_owned = 0;

      player_t *Blue = (player_t *)malloc(sizeof(player_t));
      Blue->colour = 'Blue\0';
      Blue->grids_owned = 0;

      appened(p, Green);
      appened(Green, Purple);
      appened(Purple, Blue);
      appened(Blue, p);
    }
    if (*n == 5)
    {

      player_t *Green = (player_t *)malloc(sizeof(player_t));
      Green->colour = 'Green\0';
      Green->grids_owned = 0;

      player_t *Purple = (player_t *)malloc(sizeof(player_t));
      Purple->colour = 'Purple\0';
      Purple->grids_owned = 0;

      player_t *Blue = (player_t *)malloc(sizeof(player_t));
      Blue->colour = 'Blue\0';
      Blue->grids_owned = 0;

      player_t *Yellow = (player_t *)malloc(sizeof(player_t));
      Yellow->colour = 'Yellow\0';
      Yellow->grids_owned = 0;

      appened(p, Green);
      appened(Green, Purple);
      appened(Purple, Blue);
      appened(Blue, Yellow);
      appened(Yellow, p);
    }

    if (*n == 6)
    {

      player_t *Green = (player_t *)malloc(sizeof(player_t));
      Green->colour = 'Green\0';
      Green->grids_owned = 0;

      player_t *Purple = (player_t *)malloc(sizeof(player_t));
      Purple->colour = 'Purple\0';
      Purple->grids_owned = 0;

      player_t *Blue = (player_t *)malloc(sizeof(player_t));
      Blue->colour = 'Blue\0';
      Blue->grids_owned = 0;

      player_t *Yellow = (player_t *)malloc(sizeof(player_t));
      Yellow->colour = 'Yellow\0';
      Yellow->grids_owned = 0;

      player_t *White = (player_t *)malloc(sizeof(player_t));
      White->colour = 'White\0';
      White->grids_owned = 0;

      appened(p, Green);
      appened(Green, Purple);
      appened(Purple, Blue);
      appened(Blue, Yellow);
      appened(Yellow, White);
      appened(White, p);
    }
  }
}
void appened(player_t *head, player_t *n)
{
  if (head->next == NULL)
  {
    head->next = n;
    n->prev = head;
  }
  else
  {
    player_t *temp;
    temp = head->next;
    head->next = n;
    n->prev = head;
    n->next = temp;
  }
}

void move_list_appened();

void place(player_t *curr, char *input, char *b, int *width, int *height, opd_grid *grid_po, opd_grid *g_list, move_list *m_list)
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

  if (w < 0 || w > *width || h < 0 || h > *height)
  {
    printf("Invalid Coordinates\n");
    return;
  }

  if (b[h][w] == 'n')
  {
    b[h][w] = 'y';
    grid_t *new_grid = malloc(sizeof(grid_t));
    new_grid->owner = curr;
    if (((h == 0) && (w == 0)) || ((h == height - 1) && (w == width - 1)) || ((h == 0) && (w == width - 1)) || ((h == height - 1) && (w == 0)))
    {
      new_grid->limit = 2;
    }
    else if (h == 0 || h == height - 1 || w == 0 || w == width - 1)
    {
      new_grid->limit = 3;
    }
    else
    {
      new_grid->limit = 4;
    }

    new_grid->atom_count = 1;
    opd_grid new_opd = (opd_grid *)malloc(sizeof(opd_grid));
    new_opd->current = new_grid;
    new_grid->x = w;
    new_grid->y = h;
    grid_po->next = new_opd;
    grid_po = new_opd;

    curr->girds_owned += 1;

    move_t *new_move = malloc(sizeof(move_t));
    new_move->x = w;
    new_move->y = y;
    new_move->old_owner = current;
    new_move->is_expand = 'n';
    move_list new_m = (move_list *)malloc(sizeof(move_list));
    new_m->current = new_move;
    m_list->next = new_m;
    m_list = new_m;
  }
  else
  {
    opd_grind *temp = g_list;
    while (1)
    {
      if (temp->current->x == w && temp->current->y == h)
      {
        if (temp->current->owner == curr)
        {
          if (temp->current->atom_count != limit - 1)
          {
            temp->current->atom_count++;
          }
          else
          {
            temp->current->atom_count = 0;
            temp->grid->
            move_t *new_move = (move_t *)malloc(sizeof(move_t));
            new_move->x = w;
            new_move->y = h;
            new_move->is_expand = 'y';
            move_list new_m = (move_list *)malloc(sizeof(move_list));
            new_m->current = new_move;
            m_list->next = new_m;
            m_list = new_m;
            expand();
          }
          break;
        }
        else
        {
          printf("Cannot Place Atom Here\n");
          return;
        }
      }
      else
      {
        temp = temp->next;
      }
    }
  }
  current = current->next;
  printf("%s's Turn\n'", curent->colour);
}

void expand(int w, int h, opd_grid *grid_po, move_list *m)
{
  if ()
  {
  }
}