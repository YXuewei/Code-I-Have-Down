#include <stddef.h>
#include <stdbool.h>

typedef struct node node;
struct node
{
    int data;
    node *prev;
    node *next;
};

// Initializes an empty circular linked list
void list_init( node *head )
{
    head->prev = NULL;
    head->next = NULL;
}

//Inserts given node before the head
void list_push( node *head, node *n)
{
    if ( head->prev == NULL )
    {
        head->prev = n;
        n->next = head;
    }
    else
    {
        node *temp;
        temp = head->prev;
        head->prev = n;
        n->next = head;
        n->prev = temp;
    }
}

// Inserts given node after the head
void list_append ( node *head, node *n)
{
    if ( head->next == NULL )
    {
        head->next = n;
        n->prev = head;
    }
    else
    {
        node *temp;
        temp = head->next;
        head->next = n;
        n->prev = head;
        n->next = temp;
    }
}

// Remove the given node from the list
void list_delete( node *n )
{
    if ( n->prev != NULL )
    {
        if ( n->next != NULL )
        {
            n->prev->next = n->next;
            n->next->prev = n->prev;
            n->prev = NULL;
            n->next = NULL;            
        }
        else
        {
            n->prev->next = NULL;
            n->prev = NULL;
        }
    }
    else
    {
        if ( n->next != NULL )
        {
            n->next->prev = NULL;
            n->next = NULL;
        }
    }
}

//REturns whether the list is empty
bool list_empty( node *head )
{
    if ( head->next == NULL )
        return true;
    else 
        return false;
}