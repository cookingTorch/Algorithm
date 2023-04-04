#include <stdio.h>

int main(void) {
	int t, i, j;
	char str[1000];
	scanf("%d", &t);
	for (i = 0; i < t; i++) {
		scanf("%s", str);
		j = 0;
		while (str[j]) {
			j++;
		}
		printf("%c%c\n", str[0], str[j - 1]);
	}
	return 0;
}