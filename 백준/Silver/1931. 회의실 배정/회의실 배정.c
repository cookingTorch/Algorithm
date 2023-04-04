#include <stdio.h>
#include <stdlib.h>

int cmp(const void* a, const void* b) {
	int* c = *(int**)a;
	int* d = *(int**)b;
	if (c[1] > d[1]) {
		return 1;
	}
	else if (c[1] < d[1]) {
		return -1;
	}
	else {
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
}

int main(void) {
	int n, i, end, cnt = 1;
	int **an;
	scanf("%d", &n);
	an = (int**)malloc(n * sizeof(int*));
	for (i = 0; i < n; i++) {
		an[i] = (int*)malloc(2 * sizeof(int));
		scanf("%d %d", &an[i][0], &an[i][1]);
	}
	qsort(an, n, sizeof(int*), cmp);
	end = an[0][1];
	for (i = 1; i < n; i++) {
		if (an[i][0] >= end) {
			cnt++;
			end = an[i][1];
		}
	}
	printf("%d", cnt);
	return 0;
}