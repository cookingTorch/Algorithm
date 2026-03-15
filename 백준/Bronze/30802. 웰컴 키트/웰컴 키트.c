#include <stdio.h>

int main() {
    int n, s, m, l, xl, xxl, xxxl, t, p;

    scanf("%d %d %d %d %d %d %d %d %d", &n, &s, &m, &l, &xl, &xxl, &xxxl, &t, &p);
    printf("%d\n%d %d",
            (s + t - 1) / t
            + (m + t - 1) / t
            + (l + t - 1) / t
            + (xl + t - 1) / t
            + (xxl + t - 1) / t
            + (xxxl + t - 1) / t,
            n / p, n % p);
}