#include <stdio.h>
#include <stdlib.h>

int cmp(const void* a, const void* b) {
	return *(int*)a - *(int*)b;
}

int main(void) {
	int n, i;
	int* p;
	p = malloc(1000 * sizeof(int));
	scanf("%d", &n);
	for (i = 0; i < n; i++) {
		scanf("%d", &p[i]);
	}
	qsort(p, n, sizeof(int), cmp);
	for (i = 0; i < n; i++) {
		printf("%d\n", p[i]);
	}
	free(p);
	return 0;
}