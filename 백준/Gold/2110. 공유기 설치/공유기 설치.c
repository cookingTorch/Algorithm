#include <stdio.h>
#include <stdlib.h>

int cmp(const void* a, const void* b) {
	if (*(int*)a > *(int*)b) {
		return 1;
	}
	else if (*(int*)a < *(int*)b) {
		return -1;
	}
	else {
		return 0;
	}
}

int main(void) {
	int n, c, i, left, right, mid, prev, cnt;
	int* an;
	scanf("%d %d", &n, &c);
	an = (int*)malloc(n * sizeof(int));
	for (i = 0; i < n; i++) {
		scanf("%d", &an[i]);
	}
	qsort(an, n, sizeof(int), cmp);
	left = 1;
	right = an[n - 1] - an[0];
	while (left <= right) {
		mid = (left + right) / 2;
		prev = an[0];
		cnt = 1;
		for (i = 1; i < n; i++) {
			if (an[i] - prev >= mid) {
				cnt++;
				prev = an[i];
			}
		}
		if (cnt >= c) {
			left = mid + 1;
		}
		else {
			right = mid - 1;
		}
	}
	printf("%d", right);
	return 0;
}