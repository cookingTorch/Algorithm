#include <stdio.h>

int main(void) {
	int i, j, n, m = 0, arr[10];
	for (i = 0; i < 10; i++) {
		scanf("%d", &n);
		arr[i] = n % 42;
	}
	for (i = 0; i < 42; i++) {
		n = 0;
		for (j = 0; j < 10; j++) {
			if (i == arr[j]) {
				n++;
			}
		}
		if (n == 0) {
			m++;
		}
	}
	printf("%d", 42 - m);
	return 0;
}