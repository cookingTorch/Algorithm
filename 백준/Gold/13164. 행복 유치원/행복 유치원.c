#include <stdio.h>
#include <stdlib.h>

int cmp(const void* a, const void* b) {
	return(*(int*)a - *(int*)b);
}

int main(void) {
	int n, k, i, j, t, t0, sum = 0, max = 0;
	int* x;
	x = malloc(300000 * sizeof(int));
	scanf("%d %d", &n, &k);
	for (i = 0; i < n; i++) {
		scanf("%d", &t);
		if (i != 0) {
			x[i - 1] = t - t0;
		}
		t0 = t;
	}
	qsort(x, n - 1, sizeof(int), cmp);
	for (i = 0; i < n - k; i++) {
		sum += x[i];
	}
	printf("%d", sum);
	free(x);
	return 0;
}