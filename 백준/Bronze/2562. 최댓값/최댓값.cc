#include <stdio.h>

int main(void) {
	int i, max = 0, n, arr[9];
	for (i = 0; i < 9; i++) {
		scanf("%d", &arr[i]);
		if (arr[i] > max) {
			max = arr[i];
			n = i + 1;
		}
	}
	printf("%d\n%d", max, n);
	return 0;
}