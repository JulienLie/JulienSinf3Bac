#include <stdlib.h>
#include <string.h>

int eq(int a, int b){
    return a != b ? 1 : 0;
}

int ge(int a, int b){
    return a < b ? 1 : 0;
}

int gt(int a, int b){
    return a <= b ? 1 : 0;
}

int le(int a, int b){
    return a > b ? 1 : 0;
}

int lt(int a, int b){
    return a >= b ? 1 : 0;
}

int ne(int a, int b){
    return a == b ? 1 : 0;
}

int main(int argc, char const *argv[])
{
    if(argc < 4) return -1;
    int a = atoi(argv[1]);
    int b = atoi(argv[3]);
    if(!strcmp(argv[2],"-eq")) return eq(a, b);
    else if(!strcmp(argv[2],"-ne")) return ne(a, b);
    else if(!strcmp(argv[2],"-ge")) return ge(a, b);
    else if(!strcmp(argv[2],"-gt")) return gt(a, b);
    else if(!strcmp(argv[2],"-le")) return le(a, b);
    else if(!strcmp(argv[2],"-lt")) return lt(a, b);
    else return -1;
    return r;
}
