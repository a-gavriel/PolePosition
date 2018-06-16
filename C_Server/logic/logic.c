#include "logic.h"

void init_matrix()
{
    for (int i = 0; i < 168; ++i)
    {
        for (int j = 0; j < 168; ++j)
        {
            main_matrix[i][j] = '0';
        }
    }
}

