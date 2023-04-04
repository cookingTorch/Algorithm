#include <stdio.h>

int main(void) {
	int m, n, arr[300][300], i, j, k, l, a, b, sum, ans = 0;
	scanf("%d %d", &m, &n);
	for (i = 0; i < m; i++) {
		for (j = 0; j < n; j++) {
			scanf("%d", &arr[i][j]);
		}
	}
	for (i = 0; i < m; i++) {
		for (j = 0; j < n; j++) {
			for (k = 0; k < 10; k++) {
				for (l = 0; l < 10; l++) {
					if ((k + 1) * (l + 1) <= 10 && i + k < m && j + l < n) {
						sum = 0;
						for (a = 0; a <= k; a++) {
							for (b = 0; b <= l; b++) {
								sum += arr[i + a][j + b];
							}
						}
						if (sum == 10) {
							ans++;
						}
					}
				}
			}
		}
	}
	printf("%d", ans);
	return 0;
}