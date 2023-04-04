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
	int k, n, i;
	long long right, left, mid, sum;
	int* ak;
	scanf("%d %d", &k, &n);
	ak = (int*)malloc(k * sizeof(int));

	for (i = 0; i < k; i++) {
		scanf("%d", &ak[i]);
	}

	qsort(ak, k, sizeof(int), cmp);

	left = 1;
	right = ak[k - 1];
	while (1) {
		mid = (left + right) / 2;
		sum = 0;
		for (i = 0; i < k; i++) {
			sum += ak[i] / mid;
		}
		if (left >= right) {
			if (sum >= n) {
				printf("%lld", mid);
				break;
			}
			else {
				printf("%lld", mid - 1);
				break;
			}
		}
		else if (sum >= n) {
			left = mid + 1;
		}
		else {
			right = mid - 1;
		}
	}

	free(ak);
	return 0;
}