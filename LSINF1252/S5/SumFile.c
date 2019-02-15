#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

int sum_file(char* filename){
    int ok = open(filename, O_RDONLY);
    if(ok == -1) return -1;
    char* buf = malloc(sizeof(int));
    if(buf == NULL) return -4;
    int r = read(ok, buf, sizeof(int));
    if(r == -1) {
        int c = close(ok);
        if(c == -1) return -3;
        return -2;
    }
    int sum = 0;
    while(r != 0){
        sum += atoi(buf);
        r = read(ok, buf, sizeof(int));
        if(r == -1) {
            free(buf);
            int c = close(ok);
            if(c == -1) return -3;
            return -2;
        }
    }
    free(buf);
    int c = close(ok);
    if(c == -1) return -3;
    return sum;
}

int main(){
    char* file = "sum.bin";
    printf("%d\n", sum_file(file));
    return 0;
}