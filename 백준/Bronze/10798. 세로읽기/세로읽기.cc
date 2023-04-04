#include <stdio.h>

int main(void) {
    int i, j[5], k;
    char arr[6][16] = { 0 };
    for (i = 0; i < 5; i++) {
        scanf("%s", arr[i]);
    }
    for (i = 0; i < 5; i++) {
        j[i] = 0;
        while (arr[i][j[i]]) {
            j[i]++;
        }
    }
    for (k = 0; k < 15; k++) {
        for (i = 0; i < 5; i++) {
            if (arr[i][k] != 0) {
                printf("%c", arr[i][k]);
            }
        }
    }
    return 0;
}