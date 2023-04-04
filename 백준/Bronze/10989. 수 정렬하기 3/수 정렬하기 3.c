#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int n, t, i, j;
	int* p;
	p = malloc(10001 * sizeof(int));
	for (i = 1; i <= 10000; i++) {
		p[i] = 0;
	}
	scanf("%d", &n);
	for (i = 0; i < n; i++) {
		scanf("%d", &t);
		p[t]++;
	}
	for (i = 1; i <= 10000; i++) {
		for (j = 0; j < p[i]; j++) {
			printf("%d\n", i);
		}
	}
	return 0;
}