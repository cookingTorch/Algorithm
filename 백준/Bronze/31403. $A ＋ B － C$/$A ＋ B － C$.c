#include <stdio.h>

int add(int n, char s[]) {
    int i;

    for (i = 0; s[i] != '\0'; i++) {
        n = n * 10 + s[i] - '0';
    }
    return n;
}

int main() {
    int a, c;
    char sa[5], sb[5], sc[5];

    scanf("%s %s %s", sa, sb, sc);
    printf("%d\n", (a = add(0, sa)) + add(0, sb) - (c = add(0, sc)));
    printf("%d", add(a, sb) - c);
}