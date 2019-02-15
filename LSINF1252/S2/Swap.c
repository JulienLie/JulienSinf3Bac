#include <stdio.h>

struct fract_t
{
    int num;
    int denum;
};


void swap(int* i, int* j){
    int k = *i;
    int l = *j;
    *i = l;
    *j = k;
}

void swap(struct fract_t *a, struct fract_t *b){
    int numa = a->num;
    int denuma = a->denum;
    int numb = b->num;
    int denumb = b->denum;
    a->num = numb;
    a->denum = denumb;
    b->num = numa;
    b->denum = denuma;
    }

int main(int argc, char const *argv[])
{
    int a = 5;
    int b = 6;
    int* i = &a;
    int* j = &b;
    swap(i, j);
    printf("%d %d\n", *i, *j);
    return 0;
}
