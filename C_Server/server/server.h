#ifndef SERVER_SERVER_H
#define SERVER_SERVER_H
/*
   gcc server.c -lpthread -o server
*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <pthread.h>
#include <netinet/in.h>
#include "../client/client.h"
#include "../variables/variables.h"

int socket_desc , client_sock , c;
struct sockaddr_in server , client;
pthread_mutex_t locker;
struct client* clients_arr[4];
int current_client;
int matrix_length;

void init_server();
void run();
void *connection_handler(void *);
void send_to_all(char* message);
void set_client(int* sock);
void send_matrix();
void set_list(char* h);

#endif //SERVER_SERVER_H
