#include <stdio.h>

int main(void) {
    int i, j, a, b, max = -1, arr[9][9];
    for (j = 0; j < 9; j++) {
        for (i = 0; i < 9; i++) {
            scanf("%d", &arr[i][j]);
            if (arr[i][j] > max) {
                max = arr[i][j];
                a = i + 1;
                b = j + 1;
            }
        }
    }
    printf("%d\n%d %d", max, b, a);
    return 0;
}