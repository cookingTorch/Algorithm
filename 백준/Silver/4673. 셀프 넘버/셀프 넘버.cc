#include <stdio.h>

int main(void) {
	int i, j, arr[10001];
	for (i = 1; i <= 10000; i++) {
		arr[i] = i;
	}
	for (i = 1; i <= 10000; i++) {
		if (arr[i] == i) {
			j = arr[i];
			while (j <= 10000) {
				j = j + (j / 10000) + (j / 1000 - (j / 10000 * 10)) + (j / 100 - (j / 1000 * 10)) + (j / 10 - j / 100 * 10) + (j - j / 10 * 10);
				if (arr[j] > 10000) {
					break;
				}
				if (j <= 10000) {
					arr[j] = 10001;
				}
			}
		}
	}
	for (i = 1; i <= 10000; i++) {
		if (arr[i] <= 10000) {
			printf("%d\n", arr[i]);
		}
	}
	return 0;
}