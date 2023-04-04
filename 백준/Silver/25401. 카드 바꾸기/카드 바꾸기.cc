#include <stdio.h>
#include <math.h>

int main(void) {
	int i, j, k, t;
	double n, d, a, card[500], cnt, ans;
	scanf("%lf", &n);
	ans = n - 1;
	for (i = 0; i < n; i++) {
		scanf("%lf", &card[i]);
	}
	for (i = 0; i < n - 1; i++) {
		for (j = i + 1; j < n; j++) {
			cnt = 0;
			if (fmod((card[j] - card[i]) / (j - i), 1) == 0) {
				d = (card[j] - card[i]) / (j - i);
				a = card[i] - (i * d);
				for (k = 0; k < n; k++) {
					if (card[k] != a + (k * d)) {
						cnt++;
					}
				}
				if (cnt < ans) {
					ans = cnt;
				}
			}
		}
	}
	printf("%.0f", ans);
	return 0;
}