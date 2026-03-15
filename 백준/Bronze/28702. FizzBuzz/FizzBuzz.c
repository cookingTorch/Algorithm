#include <stdio.h>

int stoi(char s[]) {
    int n, i;

    n = 0;
    for (i = 0; s[i] != '\0'; i++) {
        n = n * 10 + s[i] - '0';
    }
    return n;
}

int main() {
    int i;
    char i1[9], i2[9], i3[9];

    scanf("%s %s %s", i1, i2, i3);
    if (i1[2] != 'z') {
        i = stoi(i1) + 3;
    } else if (i2[2] != 'z') {
        i = stoi(i2) + 2;
    } else {
        i = stoi(i3) + 1;
    }
    if (i % 3 == 0) {
        if (i % 5 == 0) {
            printf("FizzBuzz");
        } else {
            printf("Fizz");
        }
    } else if (i % 5 == 0) {
        printf("Buzz");
    } else {
        printf("%d", i);
    }
}