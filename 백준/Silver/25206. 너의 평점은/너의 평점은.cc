#include <stdio.h>
#include <string.h>

int main(void) {
    int i, n = 0;
    double a, s = 0.0, sum = 0, avg;
    char str[51], score[3];
    for (i = 0; i < 20; i++) {
        scanf("%s %lf %s", str, &a, score);
        if (strcmp(score, "A+") == 0) {
            s = 4.5;
        }
        else if (strcmp(score, "A0") == 0) {
            s = 4.0;
        }
        else if (strcmp(score, "B+") == 0) {
            s = 3.5;
        }
        else if (strcmp(score, "B0") == 0) {
            s = 3.0;
        }
        else if (strcmp(score, "C+") == 0) {
            s = 2.5;
        }
        else if (strcmp(score, "C0") == 0) {
            s = 2.0;
        }
        else if (strcmp(score, "D+") == 0) {
            s = 1.5;
        }
        else if (strcmp(score, "D0") == 0) {
            s = 1.0;
        }
        else if (strcmp(score, "F") == 0) {
            s = 0.0;
        }
        if (strcmp(score, "P") != 0) {
            n += a;
            sum += a * s;
        }
    }
    avg = sum / n;
    printf("%f", avg);
    return 0;
}