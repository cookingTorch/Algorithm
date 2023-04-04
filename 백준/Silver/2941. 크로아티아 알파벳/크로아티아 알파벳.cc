#include <stdio.h>

int main(void) {
    int i = 0, j, n = 0;
    char str[100];
    scanf("%s", str);
    while (str[i]) {
        i++;
        n++;
    }
    for (j = 0; j < i; j++) {
        if (str[j] == '-' || str[j] == '=' || (j < i - 1 && (str[j] == 'l' || str[j] == 'n') && str[j + 1] == 'j') || (j < i - 2 && (str[j] == 'd' && str[j + 1] == 'z' && str[j + 2] == '='))) {
            n--;
        }
    }
    printf("%d", n);
    return 0;
}