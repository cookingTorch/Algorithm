#include <stdio.h>

int main(void) {
	int t, h, w, n, i;
	scanf("%d", &t);
	for (i = 0; i < t; i++) {
		scanf("%d %d %d", &h, &w, &n);
		printf("%d", ((n - 1) % h + 1) * 100 + ((n - 1) / h + 1));
		if (i < t - 1) {
			printf("\n");
		}
	}
	return 0;
}