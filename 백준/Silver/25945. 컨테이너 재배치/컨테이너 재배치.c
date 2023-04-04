#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int n, i, m = 0, low = 0, high = 0;
	int* an;
	scanf("%d", &n);
	an = (int*)malloc(n * sizeof(int));
	for (i = 0; i < n; i++) {
		scanf("%d", &an[i]);
		m += an[i];
	}
	for (i = 0; i < n; i++) {
		if (an[i] >= m / n + 1) {
			high += an[i] - (m / n + 1);
		}
		else if (an[i] <= m / n) {
			low += m / n - an[i];
		}
	}
	if (high >= low) {
		printf("%d", high);
	}
	else {
		printf("%d", low);
	}
	free(an);
	return 0;
}