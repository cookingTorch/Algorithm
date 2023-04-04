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
	int n, m, i, right, left, mid;
	int* an;
	long long sum = 0;
	scanf("%d %d", &n, &m);
	an = (int*)malloc(n * sizeof(int));

	for (i = 0; i < n; i++) {
		scanf("%d", &an[i]);
	}

	qsort(an, n, sizeof(int), cmp);

	left = 0;
	right = an[n - 1];
	while (1) {
		mid = (left + right) / 2;
		sum = 0;
		for (i = 0; i < n; i++) {
			if (an[i] > mid) {
				sum += an[i] - mid;
			}
		}
		if (sum == m) {
			printf("%d", mid);
			break;
		}
		else if (left >= right) {
			if (sum >= m) {
				printf("%d", mid);
				break;
			}
			else {
				printf("%d", mid - 1);
				break;
			}
		}
		else if (sum > m) {
			left = mid + 1;
		}
		else {
			right = mid - 1;
		}
	}

	free(an);
	return 0;
}