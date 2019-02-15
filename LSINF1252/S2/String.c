#include <stdio.h>

int strcasecmpr(const char* s1, const char* s2){
    int i;
    for(i = 0; s1[i] != '\0' && s2[i] != '\0'; i++){
        char a = s1[i] > 96 ? ((int)s1[i])-32 : s1[i];
        char b = s2[i] > 96 ? ((int)s2[i])-32 : s2[i];
        printf("%c %c\n", a, b);
        if(a > b) return 1;
        else if(a < b) return -1;
    }
    if(s1[i] != '\0') return 1;
    else if(s2[i] != '\0') return -1;
    return 0;
}

int main(int argc, char const *argv[])
{
    const char* s1 = "foobarr";
    const char* s2 = "FooBar";
    printf("%d\n", strcasecmpr(s1, s2));
    return 0;
}
