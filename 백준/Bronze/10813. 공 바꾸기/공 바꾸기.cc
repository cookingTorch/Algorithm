#include <stdio.h>

int main(void) {
	int n, m, k, i, j, temp, arr[100];
	scanf("%d %d", &n, &m);
	for (k = 0; k < n; k++) {
		arr[k] = k + 1;
	}
	for (k = 0; k < m; k++) {
		scanf("%d %d", &i, &j);
		temp = arr[i - 1];
		arr[i - 1] = arr[j - 1];
		arr[j - 1] = temp;
	}
	for (k = 0; k < n; k++) {
		printf("%d ", arr[k]);
	}
	return 0;
}