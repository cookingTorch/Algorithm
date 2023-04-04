#include <stdio.h>

int main(void) {
	int n, i, sum = 0;
	char str[100];
	scanf("%d", &n);
	scanf("%s", str);
	for (i = 0; i < n; i++) {
		str[i] -= '0';
		sum += str[i];
	}
	printf("%d", sum);
	return 0;
}