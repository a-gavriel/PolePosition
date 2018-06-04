#include <stdio.h>
#include "server.h"

void* run_server(void* h)
{
    run();
}

int main()
{
    pthread_t thread_id;

    int a;
    char b[100];

    init();

    pthread_create(&thread_id, NULL, run_server, NULL);

    while (b[0] != 'q')
    {
        printf("Ingrese el obstaculo que desee agregar a la pista: ");
        fgets (b, 100, stdin);

        send_to_all(b);
    }

    return 0;
}