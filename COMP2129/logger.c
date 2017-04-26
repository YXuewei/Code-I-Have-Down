#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <stdint.h>

#define CONTROLLER_DEV "controller0"
typedef union controller controller;

union controller {
  uint32_t place_holder;
  unsigned int left : 1;
  unsigned int right : 1;
  unsigned int up : 1;
  unsigned int down : 1;
  unsigned int x : 1;
  unsigned int y : 1;
  unsigned int a : 1;
  unsigned int b : 1;
  unsigned int bk : 1;
  unsigned int st : 1;
  unsigned int se : 1;
  unsigned int mo : 1;
  unsigned int z : 1;
  unsigned int w : 1;
  unsigned int lt : 1;
  unsigned int rt : 1;
  unsigned int ltrig : 4;
  unsigned int rtrig : 4;
  uint8_t id;
};

void print_packet(controller control)
{
  printf("#%u - left: %u right: %u up: %u down: %u x: %u y: %u a: %u b: "
         "%u bk: %u st: %u se: %u mo: %u z: %u w: %u lt: %u rt: %u ltrig: %u "
         "rtrig: %u\n",
         control.id, control.left, control.right, control.up, control.down,
         control.x, control.y, control.a, control.b, control.bk, control.st, control.se, control.mo,
         control.z, control.w, control.lt, control.rt, control.ltrig, control.rtrig);
}

int main(int argc, char **argv)
{
  if (argc != 2)
  {
    printf("No Argument Specified\n");
    return 1;
  }
  char *endptr = argv[1];
  int number = strtol(argv[1], &endptr, 10);
  if (endptr == argv[1] || number < 0 )
  {
    printf("Invalid Argument\n");
    return 1;
  }
  FILE *fp = fopen(argv[1], "r+b");
  if ( fp == NULL )
  {
    printf("Controller Does Not Exist\n");
    return 1;
  }
  controller *control = malloc(4);
  for (int i = 0; i < number; i++)
  {
    fread(&(control->place_holder), 4, 1, fp);
    print_packet(*control);
  }
  fclose(fp);
  free(control);
  return 0;
}