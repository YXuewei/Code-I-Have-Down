#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct album album;
struct album
{
    char name[50];
    char year[6];
    char genre[20];
    char artist[20];
};

typedef struct list_t list_t;
struct list_t
{
    album *current;
    list_t *prev;
    list_t *next;
};

list_t *list;
list_t *warden;
char input[100];
int count = 0;
int sort_col = -1;
int order = 0; // 0 for ASC 1 for DESC
int sorted = -1;
bool finished = false;
static int wrapper(const void *s1, const void *s2);
void command_take();
void display(char **name, char **year, char **genre, char **artist);
void sort(char **name, char **year, char **genre, char **artist);
int command_check();
void original();
void by_name(char **name_list);
void by_year(char **year_list);
void by_genre(char **genre_list);
void by_artist(char **artist_list);
void appened(list_t *a, list_t *b);

int main(int argc, char **argv)
{
    list = malloc(sizeof(struct list_t));
    list->next = NULL;
    list->prev = NULL;
    warden = list;
    if (argc < 2)
    {
        printf("No File Specified\n");
        return (1);
    }

    FILE *fp;
    fp = fopen(argv[1], "r");
    if (fp == NULL)
    {
        printf("File Does Not Exist\n");
        return (1);
    }
    int len;
    while (feof(fp) == 0)
    {
        fscanf(fp, "%[^\n]\n", input);
        len = strlen(input);
        if (input[len - 1] == '\n')
        {
            input[len - 1] = '\0';
        }
        album *new = malloc(sizeof(struct album));
        char *token;
        token = strtok(input, ",");
        strcpy(new->name, token);
        token = strtok(NULL, ",");
        strcpy(new->year, token);
        token = strtok(NULL, ",");
        strcpy(new->genre, token);
        token = strtok(NULL, ",");
        strcpy(new->artist, token);
        list_t *temp = malloc(sizeof(list_t));
        temp->next = NULL;
        temp->prev = NULL;
        temp->current = new;
        appened(list, temp);
        list = temp;
        count++;
    }
    fclose(fp);

    list_t* temp = warden->next;
    char* name_list[count];
    char* year_list[count];
    char* genre_list[count];
    char* artist_list[count];
    for (int i = 0; i < count; i++)
    {
        name_list[i] = temp->current->name;
        year_list[i] = temp->current->year;
        genre_list[i] = temp->current->genre;
        artist_list[i] = temp->current->artist;
        temp = temp->next;
    }
    int command;
    while (finished != true )
    {
        command_take();
        command = command_check();
        switch (command)
        {
        case 1:
            display(name_list, year_list, genre_list, artist_list);
            break;
        case 2:
            sort(name_list, year_list, genre_list, artist_list);
            break;
        case 3:
            break;
        default:
            break;
        }
    }

    temp = warden->next;
    list_t* to_be_freed = temp;
    for ( int i = 0; i < count; i++ )
    {
        free( to_be_freed->current);
        temp = temp->next;
        free( to_be_freed);
        to_be_freed = temp; 
    }
    free( warden );
    return 0;
}

void command_take()
{
    int command_count = 0;
    char c;
    while ( (c = getchar()) != EOF )
    {
        if (c == '\n')
        {
            input[command_count] = '\0';
            return;
        }
        else
        {
            input[command_count] = (char)c;
            command_count++;
        }
    }
    if ( command_count == 0 )
    {
        finished = true;
    }
}

int command_check()
{
    char *token;
    if (input[0] == 'D')
    {
        return 1;
    }
    else if (input[0] == 'Q')
    {
        finished = true;
        return 3;
    }
    else if ( input[0] == 'N')
    {
        return -1;
    }
    else
    {
        token = strtok(input, " ");
        if (strcmp(token, "SORT") == 0)
        {

            token = strtok(NULL, " ");
            char *endptr;
            int column = -1;
            column = strtol(token, &endptr, 10);
            if (token == endptr || column > 3)
            {
                sort_col = -1;
                return 2;
            }
            token = strtok(NULL, " ");
            if (token == NULL)
            {
                sort_col = column;
                order = 0;
                return 2;
            }
            else if (strcmp(token, "ASC") == 0)
            {
                sort_col = column;
                order = 0;
                return 2;
            }
            else if (strcmp(token, "DESC") == 0)
            {
                sort_col = column;
                order = 1;
                return 2;
            }
        }
    }
    return -1;
}

void display(char **name, char **year, char **genre, char **artist)
{
    switch (sorted)
    {
    case -1:
        original();
        return;
    case 0:
        by_name(name);
        return;
    case 1:
        by_year(year);
        return;
    case 2:
        by_genre(genre);
        return;
    case 3:
        by_artist(artist);
        return;
    }
}

void sort(char **name, char **year, char **genre, char **artist)
{
    switch (sort_col)
    {
    case 0:
        qsort(name, count, 8, wrapper);
        sorted = 0;
        return;
    case 1:
        qsort(year, count, 8, wrapper);
        sorted = 1;
        return;
    case 2:
        qsort(genre, count, 8, wrapper);
        sorted = 2;
        return;
    case 3:
        qsort(artist, count, 8, wrapper);
        sorted = 3;
        return;
    default:
        return;
    }
}

void original()
{
    list_t *temp = warden->next;
    for (int i = 0; i < count; i++)
    {
        printf("%s, %s, %s, %s\n", temp->current->name, temp->current->year, temp->current->genre, temp->current->artist);
        temp = temp->next;
    }
}

void by_name(char **name_list)
{
    bool trace[count];
    for (int i = 0; i < count; i++)
    {
        trace[i] = false;
    }
    if (order == 0)
    {
        list_t *temp = warden->next;
        for (int i = 0; i < count; i++)
        {
            temp = warden->next;
            for (int j = 0; j < count; j++)
            {
                if (name_list[i] == temp->current->name && trace[j] == false)
                {
                    printf("%s, %s, %s, %s\n", temp->current->name, temp->current->year, temp->current->genre, temp->current->artist);
                    trace[j] = true;
                }
                temp = temp->next;
            }
        }
    }
    else
    {
        list_t *temp = warden;
        for (int i = count - 1; i >= 0; i--)
        {
            temp = warden->next;
            for (int j = count - 1; j >= 0; j--)
            {
                if (name_list[i] == temp->current->name && trace[j] == false)
                {
                    printf("%s, %s, %s, %s\n", temp->current->name, temp->current->year, temp->current->genre, temp->current->artist);
                    trace[j] = true;
                }
                temp = temp->next;
            }
        }
    }
}

void by_year(char **year_list)
{
    bool trace[count];
    for (int i = 0; i < count; i++)
    {
        trace[i] = false;
    }
    if (order == 0)
    {
        list_t *temp = warden;
        for (int i = 0; i < count; i++)
        {
            temp = warden->next;
            for (int j = 0; j < count; j++)
            {
                if (year_list[i] == temp->current->year && trace[j] == false)
                {
                    printf("%s, %s, %s, %s\n", temp->current->name, temp->current->year, temp->current->genre, temp->current->artist);
                    trace[j] = true;
                }
                temp = temp->next;
            }
        }
    }
    else
    {
        list_t *temp = warden;
        for (int i = count - 1; i >= 0; i--)
        {
            temp = warden->next;
            for (int j = count - 1; j >= 0; j--)
            {
                if (year_list[i] == temp->current->year && trace[j] == false)
                {
                    printf("%s, %s, %s, %s\n", temp->current->name, temp->current->year, temp->current->genre, temp->current->artist);
                    trace[j] = true;
                }
                temp = temp->next;
            }
        }
    }
}

void by_genre(char **genre_list)
{
    bool trace[count];
    for (int i = 0; i < count; i++)
    {
        trace[i] = false;
    }
    if (order == 0)
    {
        list_t *temp = warden;
        for (int i = 0; i < count; i++)
        {
            temp = warden->next;
            for (int j = 0; j < count; j++)
            {
                if (genre_list[i] == temp->current->genre && trace[j] == false)
                {
                    printf("%s, %s, %s, %s\n", temp->current->name, temp->current->year, temp->current->genre, temp->current->artist);
                    trace[j] = true;
                }
                temp = temp->next;
            }
        }
    }
    else
    {
        list_t *temp = warden;
        for (int i = count - 1; i >= 0; i--)
        {
            temp = warden->next;
            for (int j = count - 1; j >= 0; j--)
            {
                if (genre_list[i] == temp->current->genre && trace[j] == false)
                {
                    printf("%s, %s, %s, %s\n", temp->current->name, temp->current->year, temp->current->genre, temp->current->artist);
                    trace[j] = true;
                }
                temp = temp->next;
            }
        }
    }
}

void by_artist(char **artist_list)
{
    bool trace[count];
    for (int i = 0; i < count; i++)
    {
        trace[i] = false;
    }
    if (order == 0)
    {
        list_t *temp = warden;
        for (int i = 0; i < count; i++)
        {
            temp = warden->next;
            for (int j = 0; j < count; j++)
            {
                if (artist_list[i] == temp->current->artist && trace[j] == false)
                {
                    printf("%s, %s, %s, %s\n", temp->current->name, temp->current->year, temp->current->genre, temp->current->artist);
                    trace[j] = true;
                }
                temp = temp->next;
            }
        }
    }
    else
    {
        list_t *temp;
        for (int i = count - 1; i >= 0; i--)
        {
            temp = warden->next;
            for (int j = count - 1; j >= 0; j--)
            {
                if (artist_list[i] == temp->current->artist && trace[j] == false)
                {
                    printf("%s, %s, %s, %s\n", temp->current->name, temp->current->year, temp->current->genre, temp->current->artist);
                    trace[j] = true;
                }
                temp = temp->next;
            }
        }
    }
}

static int wrapper(const void *s1, const void *s2)
{
    const char *lhs = *(const char **)s1;
    const char *rhs = *(const char **)s2;
    return strcmp(lhs, rhs);
}

void appened(list_t *a, list_t *b)
{
    a->next = b;
    b->prev = a;
}
