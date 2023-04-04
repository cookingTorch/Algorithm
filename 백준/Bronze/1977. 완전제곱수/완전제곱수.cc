#include <stdio.h>

int main(void) {
	int m, n, i, min = 10001, sum = 0;
	scanf("%d\n%d", &m, &n);
	for (i = 1; i <= 100; i++) {
		if (i * i >= m && i * i <= n) {
			if (i * i < min) {
				min = i * i;
			}
			sum += i * i;
		}
		if (i * i > n) {
			break;
		}
	}
	if (min <= 10000) {
		printf("%d\n%d", sum, min);
	}
	else {
		printf("-1");
	}
	return 0;
}