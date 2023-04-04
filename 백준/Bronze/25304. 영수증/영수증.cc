#include <stdio.h>

int main(void) {
	int x, n, a, b, i, y = 0;
	scanf("%d\n%d", &x, &n);
	for (i = 1; i <= n; i++) {
		scanf("%d %d", &a, &b);
		y += a * b;
	}
	if (y == x) {
		printf("Yes");
	}
	else {
		printf("No");
	}
	return 0;
}