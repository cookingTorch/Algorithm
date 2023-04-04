#include <stdio.h> 
int main(void) {
    char a[10001], b[10001];
    int alen = 0, blen = 0, slen, llen, i, j = 0, c[10001] = { 0 };
    scanf("%s %s", a, b);
    while (a[alen]) {
        a[alen] -= '0';
        alen++;
    }
    while (b[blen]) { 
        b[blen] -= '0';
        blen++;
    }
    if (alen < blen) {
        slen = alen;
        llen = blen;
    }
    else {
        slen = blen;
        llen = alen;
    }
    for (i = 1; i <= slen; i++) {
        c[llen - i] += (c[llen + 1 - i] + a[alen - i] + b[blen - i]) / 10;
        c[llen + 1 - i] = (c[llen + 1 - i] + a[alen - i] + b[blen - i]) % 10;
        
    }
    for (i = llen - slen; i > 0; i--) {
        if (alen > blen) {
            c[i - 1] += (c[i] + a[i - 1]) / 10;
            c[i] = (c[i] + a[i - 1]) % 10; 
        }
        else {
            c[i - 1] += (c[i] + b[i - 1]) / 10;
            c[i] = (c[i] + b[i - 1]) % 10;
        }
    }
    for (i = 0; i <= llen; i++) {
        if (c[i] != 0) {
            j++;
        }
        if (j > 0) {
            printf("%d", c[i]);
        }
    }
    return 0;
}