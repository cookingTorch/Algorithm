#include <stdio.h>
#include <stdlib.h>

int cmp(const void* arg1, const void* arg2) {
	int a = *(int*)arg1;
	int b = *(int*)arg2;
	if (a > b) return 1;
	else if (a < b) return -1;
	else return 0;
}

int main(void) {
	int n, m, i, left, right, mid, flag;
	int* arr1;
	int* arr2;

	scanf("%d", &n);
	arr1 = (int*)malloc(n * sizeof(int));
	for (i = 0; i < n; i++) {
		scanf("%d", &arr1[i]);
	}
	qsort(arr1, n, sizeof(int), cmp);

	scanf("%d", &m);
	arr2 = (int*)malloc(m * sizeof(int));
	for (i = 0; i < m; i++) {
		scanf("%d", &arr2[i]);
	}

	for (i = 0; i < m; i++) {
		left = 0;
		right = n - 1;
		flag = 0;
		while (right >= left) {
			mid = (left + right) / 2;
			if (arr2[i] == arr1[mid]) {
				flag = 1;
				break;
			}
			else if (arr2[i] < arr1[mid]) {
				right = mid - 1;
			}
			else if (arr2[i] > arr1[mid]) {
				left = mid + 1;
			}
		}
		printf("%d\n", flag);
	}

	free(arr1);
	free(arr2);
	return 0;
}