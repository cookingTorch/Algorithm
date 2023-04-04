#include <stdio.h>

int main(void) {
    int n, i, j, k, l, m, p, count = 0;
    char str[100], t[100];
    scanf("%d", &n);
    for (i = 0; i < n; i++) {
        scanf("%s", str);
        j = 0;
        p = 0;
        while (str[j]) {
            j++;
        }
        l = 0;
        t[0] = str[0];
        for (k = 1; k < j; k++) {
            if (str[k] != str[k - 1]) {
                l++;
                t[l] = str[k];
            }
        }
        for (k = 0; k <= l; k++) {
            for (m = k + 1; m <= l; m++) {
                if (t[k] == t[m]) {
                    p++;
                }
            }
        }
        if (p > 0) {
            count++;
        }
    }
    printf("%d", n - count);
    return 0;
}