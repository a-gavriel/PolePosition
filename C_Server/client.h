#ifndef SERVER_CLIENT_H
#define SERVER_CLIENT_H

struct client
{
    int* sock;
    int* pthread_id;
};

#endif //SERVER_CLIENT_H
