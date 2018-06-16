#include "server.h"

void init_server()
{
    memset(clients_arr, 0, 4);
    current_client = 0;
    matrix_length = 168;

    socket_desc = socket(AF_INET , SOCK_STREAM , 0);

    if (socket_desc == -1)
    {
        printf("Could not create socket");
    }
    puts("Socket created");

    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons( 16060 );


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

    while( (client_sock = accept(socket_desc, (struct sockaddr *)&client, (socklen_t*)&c)) )
    {
        puts("Connection accepted");

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
    int sock = *(int *) socket_desc;
    int read_size;
    char message[50], client_message[2000];

    set_client(&sock);
    sprintf(message, "You are client number %d \n", current_client);
    write(sock, message, strlen(message));

    send_matrix();

    while ((read_size = recv(sock, client_message, 2000, 0)) > 0)
    {
        client_message[read_size] = '\0';
        set_list(client_message);
        write(sock, client_message, strlen(client_message));
        memset(client_message, 0, 2000);
    }

    if (read_size == 0)
    {
        puts("Client disconnected");
        fflush(stdout);
    }
    else if (read_size == -1)
    {
        perror("recv failed");
    }
}

void send_to_all(char* message)
{
    for (int i = 0; i < 4; ++i)
    {
        if (clients_arr[i] != 0)
            write(*clients_arr[i]->sock , message, strlen(message));
    }
}

void set_client(int* sock)
{
    pthread_mutex_lock(&locker);

    struct client *ptr_one;
    ptr_one = (struct client *)malloc(sizeof(struct client));
    ptr_one->sock = sock;

    clients_arr[current_client] = ptr_one;

    pthread_mutex_unlock(&locker);
}

void set_list(char* h)
{
    pthread_mutex_lock(&locker);

    printf("%s \n", h);

    pthread_mutex_unlock(&locker);
}

void send_matrix()
{
    char msg[matrix_length];

    for (int i = 0; i < matrix_length; ++i)
    {

        for (int j = 0; j < matrix_length; ++j)
        {
            msg[j] = main_matrix[i][j];
        }

        msg[matrix_length - 1] = '\n';

        for (int k = 0; k < 4; ++k)
        {
            if (clients_arr[k] != 0) {
                puts("sended");
                send(*clients_arr[k]->sock , msg, strlen(msg), 0);
            }
        }
    }
}


