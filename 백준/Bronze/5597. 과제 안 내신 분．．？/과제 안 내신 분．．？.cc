#include <stdio.h>

int main(void) {
	int i, j, s, arr[28];
	for (i = 0; i < 28; i++) {
		scanf("%d", &arr[i]);
	}
	for (i = 1; i <= 30; i++) {
		s = 0;
		for (j = 0; j < 28; j++) {
			if (i == arr[j]) {
				s++;
			}
		}
		if (s == 0) {
			printf("%d\n", i);
		}
	}
	return 0;
}