#include "server.h"


void init()
{
    memset(clients_arr, 0, 4);

    socket_desc = socket(AF_INET , SOCK_STREAM , 0);

    if (socket_desc == -1)
    {
        printf("Could not create socket");
    }
    puts("Socket created");

    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons( 8888 );


    if( bind(socket_desc,(struct sockaddr *)&server , sizeof(server)) < 0)
    {
        perror("bind failed. Error");
        return;
    }
    puts("bind done");

    listen(socket_desc , 3);

    c = sizeof(struct sockaddr_in);

    puts("Waiting for incoming connections...");
    c = sizeof(struct sockaddr_in);
}



void run()
{
    pthread_t thread_id;

    int current_client = 0;

    while( (client_sock = accept(socket_desc, (struct sockaddr *)&client, (socklen_t*)&c)) )
    {
        puts("Connection accepted");

        clients_arr[current_client] = client_sock;

        if( pthread_create( &thread_id , NULL ,  connection_handler , (void*) &client_sock) < 0)
        {
            perror("could not create thread");
            return;
        }

        puts("Handler assigned");
        current_client++;
    }

    if (client_sock < 0)
    {
        perror("accept failed");
        return;
    }
}


void *connection_handler(void *socket_desc)
{
    //printf("Thread number, %d \n", (int) pthread_self());

    int sock = *(int *) socket_desc;
    int read_size;
    char *message, client_message[2000];

    message = "Greetings! I am your connection handler\n";
    write(sock, message, strlen(message));

    message = "Now type something and i shall repeat what you type \n";
    write(sock, message, strlen(message));

    while ((read_size = recv(sock, client_message, 2000, 0)) > 0) {
        //end of string marker
        client_message[read_size] = '\0';

        write(sock, client_message, strlen(client_message));

        memset(client_message, 0, 2000);
    }

    if (read_size == 0) {
        puts("Client disconnected");
        fflush(stdout);
    } else if (read_size == -1) {
        perror("recv failed");
    }

    return 0;
}

void send_to_all(char* message)
{
    for (int i = 0; i < 4; ++i)
    {
        if (clients_arr[i] != 0)
            write(clients_arr[i] , message, strlen(message));
    }
}

