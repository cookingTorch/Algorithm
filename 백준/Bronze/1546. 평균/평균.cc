#include <stdio.h>

int main(void) {
	int n, m, i;
	double max = 0, avg = 0, arr[1000];
	scanf("%d", &n);
	for (i = 0; i < n; i++) {
		scanf("%lf", &arr[i]);
		if (arr[i] > max) {
			max = arr[i];
		}
	}
	for (i = 0; i < n; i++) {
		avg += (arr[i] / max) * 100 / n;
	}
	printf("%f", avg);
	return 0;
}