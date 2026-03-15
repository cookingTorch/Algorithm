#include <stdio.h>

int main() {
    int i, mod, odd;
    char s[14];

    scanf("%s", s);
    mod = 0;
    if (s[0] != '*') {
        mod = (mod + s[0] - '0') % 10;
    } else {
        odd = 1;
    }
    for (i = 1; i < 13; i++) {
        if (s[i] != '*') {
            mod = (mod + (s[i] - '0') * 3) % 10;
        } else {
            odd = 0;
        }
        if (s[++i] != '*') {
            mod = (mod + s[i] - '0') % 10;
        } else {
            odd = 1;
        }
    }
    mod = (10 - mod) % 10;
    if (!odd && mod % 3 != 0) {
        if ((mod += 10) % 3 != 0) {
            mod += 10;
        }
        mod /= 3;
    }
    printf("%d", mod);
}