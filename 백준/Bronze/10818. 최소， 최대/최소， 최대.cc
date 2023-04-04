#include <stdio.h>

int main(void) {
	int n, i, t, min = 1000000, max = -1000000;
	scanf("%d", &n);
	for (i = 1; i <= n; i++) {
		scanf("%d", &t);
		if (t < min) {
			min = t;
		}
		if (t > max) {
			max = t;
		}
	}
	printf("%d %d", min, max);
	return 0;
}