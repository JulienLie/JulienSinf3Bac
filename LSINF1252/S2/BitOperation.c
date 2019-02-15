#include <stdint.h>
#include <stdio.h>

uint32_t Cycle_bits(uint32_t x, uint8_t n){
    uint32_t ret = 0;
    uint32_t slct = 1;
    for(int i = 0; i < 32; i++){
        uint32_t bit = x & slct;
        bit >>= i;
        bit <<= ((i+n)%32);
        ret ^= bit;
        slct <<= 1;
    }
    return ret;
}

int main(int argc, char const *argv[])
{
    if(argc < 1) return -1;
    uint32_t prec = 0b10110000111001110001101011001010;
    uint32_t val = Cycle_bits(prec, 3);
    for (int i = 32; 0 <= i; i--) {
        printf("%c", (prec & (1 << i)) ? '1' : '0');
    }
    printf("\n");
    for (int i = 32; 0 <= i; i--) {
        printf("%c", (val & (1 << i)) ? '1' : '0');
    }
    printf("\n");
    return 0;
}
