#include <string.h>
#include <stdio.h>
#include <stdlib.h>

struct node {
    struct node *next;
    int hash;
    int id;
    char name[20];
    char buffer[100];
    unsigned int timestamp;
    char acl;
    short flow;
    char *parent;
    void *fifo;
};

struct node* pair_filter(struct node* head){
    if(head == NULL) return NULL;
    struct node* cur = head->next;
    struct node* h = malloc(sizeof(struct node));
    if(h == NULL) return NULL;
    memcpy(h, head, sizeof(struct node));
    struct node* ret = h;
    h->next = NULL;
    for(int i = 1; cur != NULL; i++){
        if(i%2 == 0){
            ret->next = malloc(sizeof(struct node));
            ret = ret->next;
            if(ret == NULL) return NULL;
            memcpy(ret, cur, sizeof(struct node));
            if(h->next == NULL) h->next = ret;
        }
        cur = cur->next;
    }
    return h;
}

void print(struct node* head){
    struct node* next = head;
    while(next != NULL){
        printf("%d\t", next->id);
        next = next->next;
    }
    printf("\n");
}

int main(){
    struct node* head = malloc(sizeof(struct node));
    if(head == NULL) return 1;
    head->id = 0;
    struct node* next = head;
    for(int i = 0; i < 10; i++){
        next->next = malloc(sizeof(struct node));
        next = next->next;
        if(next == NULL) return 1;
        next->id = i+1;
    }
    print(head);
    struct node* test = pair_filter(head);
    print(test);
    return 0;
}