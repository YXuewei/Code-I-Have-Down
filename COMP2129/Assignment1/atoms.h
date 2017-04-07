#ifndef ATOMS_H
#define ATOMS_H

#include <stdint.h>

#define MAX_LINE 255
#define MAX_PLAYERS 6
#define MIN_PLAYERS 2
#define MAX_WIDTH 255
#define MAX_HEIGHT 255
#define MIN_WIDTH 2
#define MIN_HEIGHT 2

typedef struct move_t move_t;
typedef struct player_t player_t;
typedef struct game_t game_t;
typedef struct grid_t grid_t;
typedef struct save_file_t save_file_t;
typedef struct opd_grid opd_grid;
typedef struct move_list move_list;

struct save_t {
  char* filename;
  save_file_t* data;
};
struct move_t {
  int x;
  int y;
  char is_expand;
  short expand_number;
  //move_t* parent;
  //move_t* extra;
  player_t* old_owner; //NULL if unoccupied
};
struct player_t {
  char *colour;
  int grids_owned;
  player_t *next;
  player_t *prev;
};
struct game_t {
  move_t* moves;
};
struct grid_t {
  player_t* owner;
  int atom_count;
  int limit;
  int x;
  int y;
};
struct save_file_t {
    uint8_t width;
    uint8_t height;
    uint8_t no_players;
    uint32_t* raw_move_data;
};

struct opd_grid {
  grid_t *current;
  opd_grid *next;
  opd_grid *prev;
};

struct move_list{
  move_t *current;
  move_list *next;
  move_list *prev;
}
#endif
