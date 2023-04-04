#include <stdio.h>
#include <stdlib.h>

int cmp1(const void* a, const void* b) {
	int* c = *(int**)a;
	int* d = *(int**)b;
	if (c[1] > d[1]) {
		return 1;
	}
	else if (c[1] < d[1]) {
		return -1;
	}
	else {
		return 0;
	}
}

int cmp2(const void* a, const void* b) {
	int* c = *(int**)a;
	int* d = *(int**)b;
	if (c[0] > d[0]) {
		return 1;
	}
	else if (c[0] < d[0]) {
		return -1;
	}
	else {
		return 0;
	}
}

int main(void) {
	int n, m, k, i, j;
	long long ans = 0;
	int **an;
	scanf("%d %d %d", &n, &m, &k);
	an = (int**)malloc(n * sizeof(int*));
	for (i = 0; i < n; i++) {
		an[i] = (int*)malloc(2 * sizeof(int));
		scanf("%d %d", &an[i][0], &an[i][1]);
	}
	qsort(an, n, sizeof(int*), cmp1);
	qsort(an, n - k, sizeof(int*), cmp2);
	for (i = 0; i < m + k; i++) {
		ans += an[n - 1 - i][0];
	}
	printf("%lld", ans);
	return 0;
}