#ifndef SERVER_VARIABLES_H
#define SERVER_VARIABLES_H

#include <sys/types.h>

extern int current_client;
extern int socket_desc , client_sock , c;
extern struct sockaddr_in server , client;
extern pthread_mutex_t locker;
extern struct client* clients_arr[4];

extern char main_matrix[168][168];
extern int matrix_length;

#endif //SERVER_VARIABLES_H
