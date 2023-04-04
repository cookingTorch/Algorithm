#include <stdio.h>

int main(void) {
	int i = 0, j, a = 1;
	char str[100];
	scanf("%s", str);
	while (str[i]) {
		i++;
	}
	for (j = 0; j <= i / 2; j++) {
		if (str[j] != str[i - 1 - j]) {
			a = 0;
		}
	}
	printf("%d", a);
	return 0;
}