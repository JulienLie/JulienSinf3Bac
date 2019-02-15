#include <string.h>
#include <stdlib.h>
#include <stdio.h>

int palindrome(char* s){
    if(s == NULL) return 0;
    int len = strlen(s);
    int m = 1;
    for(int i = 0; i < len/2; i++){
        if(s[len-m-i] == ' '){
            m++;
            i--;
        }
        else if(s[i] == ' ') m--;
        else if(s[i] != s[len-m-i]){
            printf("s[%d] = %c != s[%d-%d-%d] = %c\n", i, s[i], len, m, i, s[len-m-i]);
            return -1;
        }
    }
    return 0;
}

int main(int argc, char const *argv[])
{
    char* test = NULL;
    //printf("%s\n", test);
    printf("%d\n", palindrome(test));
    return 0;
}
