#include <stdio.h>
#include "server/server.h"
#include "logic/logic.h"

void* run_server(void* h)
{
    run();
}

int main()
{
    pthread_t thread_id;
    char b[100];

    init_server();
    init_matrix();
    pthread_create(&thread_id, NULL, run_server, NULL);

    while (b[0] != 'x')
    {
        printf("Ingrese el obstaculo que desee agregar a la pista: ");
        fgets (b, 100, stdin);

        send_to_all(b);
    }

    return 0;
}