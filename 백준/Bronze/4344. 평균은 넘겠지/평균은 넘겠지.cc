#include <stdio.h>

int main(void) {
    int c, n, sum, a, i, j, arr[1000];
    double avg;
    scanf("%d", &c);
    for (i = 0; i < c; i++) {
        sum = 0;
        a = 0;
        scanf("%d", &n);
        for (j = 0; j < n; j++) {
            scanf("%d", &arr[j]);
            sum += arr[j];
        }
        avg = sum / n;
        for (j = 0; j < n; j++) {
            if (arr[j] > avg) {
                a++;
            }
        }
        printf("%.3f%%\n", (double)a / n * 100);
    }
    return 0;
}