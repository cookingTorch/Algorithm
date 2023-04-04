#include <stdio.h>

int main(void) {
	int t, a, b, c, min, i;
	scanf("%d", &t);
	for (i = 0; i < t; i++) {
		scanf("%d %d %d", &a, &b, &c);
		min = 60;
		if (a < min) {
			min = a;
		}
		if (b < min) {
			min = b;
		}
		if (c < min) {
			min = c;
		}
		printf("%d\n", min);
	}
	return 0;
}