#include <stdio.h>

int main(void) {
	int n, m, b, i, j, h, k, l, arr[501][501], low = 257, high = -1, t, min = -1, ans;
	scanf("%d %d %d", &n, &m, &b);
	for (i = 1; i <= n; i++) {
		for (j = 1; j <= m; j++) {
			scanf("%d", &arr[i][j]);
			if (arr[i][j] < low) {
				low = arr[i][j];
			}
			if (arr[i][j] > high) {
				high = arr[i][j];
			}
		}
	}
	for (h = low; h <= high; h++) {
		k = 0;
		l = 0;
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= m; j++) {
				if (arr[i][j] > h) {
					k += arr[i][j] - h;
				}
				else if (arr[i][j] < h) {
					l += h - arr[i][j];
				}
			}
		}
		if (b + k - l >= 0) {
			t = 2 * k + l;
			if (min == -1 || t <= min) {
				min = t;
				ans = h;
			}
		}
	}
	printf("%d %d", min, ans);
	return 0;
}