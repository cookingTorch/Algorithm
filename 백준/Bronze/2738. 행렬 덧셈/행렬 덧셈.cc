#include <stdio.h>

int main(void) {
    int n, m, i, j, t, arr[100][100];
    scanf("%d %d", &n, &m);
    for (j = 0; j < m; j++) {
        for (i = 0; i < n; i++) {
            scanf("%d", &arr[i][j]);
        }
    }
    for (j = 0; j < m; j++) {
        for (i = 0; i < n; i++) {
            scanf("%d", &t);
            arr[i][j] += t;
            printf("%d", arr[i][j]);
            if (i < n - 1) {
                printf(" ");
            }
        }
        if (j < m - 1) {
            printf("\n");
        }
    }
    return 0;
}