#include <stdio.h>

int main(void) {
	int n, m, i, j, k, l, arr1[100], arr2[100];
	scanf("%d %d", &n, &m);
	for (k = 0; k < n; k++) {
		arr1[k] = k + 1;
	}
	for (k = 0; k < m; k++) {
		scanf("%d %d", &i, &j);
		for (l = 0; l <= j - i; l++) {
			arr2[l] = arr1[j - l - 1];
		}
		for (l = 0; l <= j - i; l++) {
			arr1[i - 1 + l] = arr2[l];
		}
	}
	for (k = 0; k < n; k++) {
		printf("%d ", arr1[k]);
	}
	return 0;
}