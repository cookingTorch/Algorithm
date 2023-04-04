#include <stdio.h>

int main(void) {
	int i, b[6] = { 1, 1, 2, 2, 2, 8 }, w;
	for (i = 0; i < 6; i++) {
		scanf("%d", &w);
		printf("%d ", b[i] - w);
	}
	return 0;
}