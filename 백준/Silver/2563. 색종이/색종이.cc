#include <stdio.h>

int main(void) {
    int n, i, j, k, a, b, black = 0, arr[100][100] = { 0 };
    scanf("%d", &n);
    for (i = 0; i < n; i++) {
        scanf("%d %d", &a, &b);
        for (j = 0; j < 10; j++) {
            for (k = 0; k < 10; k++) {
                arr[a + j][b + k] = 1;
            }
        }
    }
    for (j = 0; j < 100; j++) {
        for (k = 0; k < 100; k++) {
            black += arr[j][k];
        }
    }
    printf("%d", black);
    return 0;
}