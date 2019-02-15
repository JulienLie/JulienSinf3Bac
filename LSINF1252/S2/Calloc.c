#include <stdlib.h>
#include <stdio.h>

struct calloc_t
{
    char* rien;
};


void* calloc2(size_t nmemb, size_t size){
    char* ret = malloc(nmemb*size);
    for(int i = 0; i < nmemb; i++){
        ret[i] = 0;
    }
    return ret;
}

int main(int argc, char const *argv[])
{
    if(argc < 2) return -1;
    void* c = calloc2(argv[1][0], sizeof(struct calloc_t));
    if(c == NULL) return -1;
    free(c);
    printf("ok\n");
    return 0;
}
