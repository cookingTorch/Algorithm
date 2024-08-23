#include <stdio.h>

int main() {
    int t;
    int n;
    int i;
    int cnt;

    scanf("%d", &t);
    while (t--) {
        scanf("%d", &n);
        cnt = 0;
        for (i = 5; i <= n; i *= 5) {
            cnt += n / i;
        }
        printf("%d\n", cnt);
    }
}