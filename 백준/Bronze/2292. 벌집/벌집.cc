#include <stdio.h>

int main(void) {
	int n, c = 0, i = 0;
	scanf("%d", &n);
	while (1) {
		c += i;
		if (n <= 1 + 6 * c) {
			break;
		}
		i++;
	}
	printf("%d", i + 1);
	return 0;
}