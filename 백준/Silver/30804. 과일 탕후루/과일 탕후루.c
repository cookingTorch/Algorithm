#include <stdio.h>

int main() {
    int l, r, n, i, cnt, max;
    int cnts[10], arr[200001];

    for (i = 1; i < 10; i++) {
        cnts[i] = 0;
    }
    scanf("%d", &n);
    cnt = 0;
    max = 0;
    l = 0;
    for (r = 0; r < n; r++) {
        scanf("%d", &arr[r]);
        if (cnts[arr[r]]++ == 0) {
            cnt++;
        }
        while (cnt > 2) {
            if (--cnts[arr[l++]] == 0) {
                cnt--;
            }
        }
        if (r - l + 1 > max) {
            max = r - l + 1;
        }
    }
    printf("%d", max);
}