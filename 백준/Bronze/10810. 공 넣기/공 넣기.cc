#include <stdio.h>

int main(void) {
	int n, m, a, b, i, j, k, arr[100] = { 0 };
	scanf("%d %d", &n, &m);
	for (a = 0; a < m; a++) {
		scanf("%d %d %d", &i, &j, &k);
		for (b = i - 1; b <= j - 1; b++) {
			arr[b] = k;
		}
	}
	for (a = 0; a < n; a++) {
		printf("%d ", arr[a]);
	}
	return 0;
}