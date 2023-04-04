#include <stdio.h>

int main(void) {
	int n, m, i, j, k, t, u, arr[100][2];
	scanf("%d %d", &n, &m);
	for (t = 0; t < n; t++) {
		arr[t][0] = t + 1;
	}
	for (t = 0; t < m; t++) {
		for (u = 0; u < n; u++) {
			arr[u][1] = arr[u][0];
		}
		scanf("%d %d %d", &i, &j, &k);
		for (u = 0; u < j - k + 1; u++) {
			arr[i - 1 + u][0] = arr[k - 1 + u][1];
		}
		for (u = 0; u < k - i; u++) {
			arr[i - 1 + j - k + 1 + u][0] = arr[i - 1 + u][1];
		}
	}
	for (t = 0; t < n; t++) {
		printf("%d ", arr[t][0]);
	}
	return 0;
}