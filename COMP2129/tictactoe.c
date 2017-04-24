#include <stdio.h>
#include <string.h>

void print_board();
int check();
int board[3][3];

int main()
{
    int turn = 2;
    int count = 0;
    int x, y;
    int win = -1;
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            board[i][j] = -1;
        }
    }
    while (1)
    {
        scanf("%d", &x);
        scanf("%d", &y);
        getchar();
        if (turn % 2 == 0)
        {
            board[y][x] = 0;
            turn += 1;
            count += 1;
        }
        else
        {
            board[y][x] = 1;
            turn += 1;
            count += 1;
        }
        win = check(board);
        if (win < 0 && count != 9)
        {
            print_board(board);
        }
        else if (win >= 0 )
        {
            if (win == 1)
            {
                printf("O wins!\n");
                print_board();
                return 0;
            }
            else
            {
                printf("X wins!\n");
                print_board();
                return 0;
            }
        }
        if ( count == 9 )
        {
            printf("Draw\n");
            print_board();
            return 0;
        }
    }
}

void print_board()
{
    printf("\n");
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (board[i][j] < 0)
            {
                printf(" ");
            }
            else if (board[i][j] == 0)
            {
                printf("X");
            }
            else
            {
                printf("O");
            }
            if (j != 2)
            {
                printf("|");
            }
        }
        if (i != 2)
        {
            printf("\n-----\n");
        }
        else
        {
            printf("\n");
        }
    }
    printf("\n");
}

int check()
{
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (board[i][j] >= 0)
            {
                if (i == 0 && j == 0)
                {
                    if (board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i + 2][j])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i + 1][j + 1] && board[i + 1][j + 1] == board[i + 2][j + 2])
                    {
                        return board[i][j];
                    }
                }
                else if (i == 0 && j == 1)
                {
                    if (board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i + 2][j])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i][j - 1] && board[i][j + 1] == board[i][j - 1])
                    {
                        return board[i][j];
                    }
                }
                else if (i == 0 && j == 2)
                {
                    if (board[i][j] == board[i][j - 1] && board[i][j - 2] == board[i][j - 1])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i + 2][j])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i + 1][j - 1] && board[i + 1][j - 1] == board[i + 2][j - 2])
                    {
                        return board[i][j];
                    }
                }
                else if (i == 1 && j == 0)
                {
                    if (board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i - 1][j])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2])
                    {
                        return board[i][j];
                    }
                }
                else if (i == 1 && j == 1)
                {
                    if (board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i - 1][j])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i - 1][j - 1] && board[i][j] == board[i + 1][j + 1])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i + 1][j - 1] && board[i][j] == board[i - 1][j + 1])
                    {
                        return board[i][j];
                    }
                }
                else if (i == 1 && j == 2)
                {
                    if (board[i][j] == board[i + 1][j] && board[i + 1][j] == board[i - 1][j])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i][j - 1] && board[i][j + 1] == board[i][j - 2])
                    {
                        return board[i][j];
                    }
                }
                else if (i == 2 && j == 0)
                {
                    if (board[i][j] == board[i][j + 1] && board[i][j + 2] == board[i][j + 1])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i - 1][j] && board[i - 1][j] == board[i - 2][j])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i - 1][j + 1] && board[i - 1][j + 1] == board[i - 2][j + 2])
                    {
                        return board[i][j];
                    }
                }
                else if (i == 2 && j == 1)
                {
                    if (board[i][j] == board[i - 1][j] && board[i - 1][j] == board[i - 2][j])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i][j - 1] && board[i][j + 1] == board[i][j - 1])
                    {
                        return board[i][j];
                    }
                }
                else if (i == 2 && j == 2)
                {
                    if (board[i][j] == board[i][j - 1] && board[i][j - 2] == board[i][j - 1])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i - 1][j] && board[i - 1][j] == board[i - 2][j])
                    {
                        return board[i][j];
                    }
                    else if (board[i][j] == board[i - 1][j - 1] && board[i - 1][j - 1] == board[i - 2][j - 2])
                    {
                        return board[i][j];
                    }
                }
            }
        }
    }
    return -1;
}