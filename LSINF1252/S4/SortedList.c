#include <stdlib.h>
#include <stdio.h>

typedef struct node{
    char val;
    struct node *next;
} node_t;

int insert(node_t** head, char val, int (*cmp)(char, char)){
    if(*head == NULL){
    node_t* new = malloc(sizeof(node_t));
    if(new == NULL) return -1;
    new->val = val;
    *head = new;
    return 0;
}
node_t* cur = *head;
if(cmp(val, cur->val) < 0){
    node_t* new = malloc(sizeof(node_t));
    if(new == NULL) return -1;
    new->val = val;
    *head = new;
    return 0;
}
else if(cmp(val, cur->val) == 0){
    return 0;
}
while(cur->next != NULL){
    if(cmp(val, cur->next->val) < 0){
        node_t* new = malloc(sizeof(node_t));
        if(new == NULL) return -1;
        new->val = val;
        new->next = cur->next;
        cur->next = new;
        return 0;
    }
    else if(cmp(val, cur->next->val) == 0){
        return 0;
    }
    cur = cur->next;
}
node_t* new = malloc(sizeof(node_t));
if(new == NULL) return -1;
new->val = val;
cur->next = new;
return 0;
}

int cmp(char a, char b){
    if(a > b) return 1;
    else if(a < b) return -1;
    else return 0;
}

void print(node_t** head){
    node_t* cur = *head;
    while(cur != NULL){
        printf("%c\t", cur->val);
        cur = cur->next;
    }
    printf("\n");
}

int main(){
    node_t** head = malloc(sizeof(node_t*));
    if(head == NULL) return 1;
    for(int i = 0; i < 100; i++){
        char c = 'A' + (random() % 26);
        printf("%c\t", c);
        insert(head, c, cmp);
    }
    free(head);
    printf("\n");
    print(head);
}