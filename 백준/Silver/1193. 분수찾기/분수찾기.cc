#include <stdio.h>

int main(void) {
	int x, c = 0, i = 0;
	scanf("%d", &x);
	while (1) {
		i++;
		c += i;
		if (c >= x) {
			if (i % 2 != 0) {
				printf("%d/%d", 1 + (c - x), i - (c - x));
			}
			if (i % 2 == 0) {
				printf("%d/%d", i - (c - x), 1 + (c - x));
			}
			break;
		}
	}
	return 0;
}