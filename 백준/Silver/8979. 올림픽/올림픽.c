#include <stdio.h>
#include <stdlib.h>

int main(void) {
	int n, c, k, i, j, rank = 1;
	int** p;
	p = (int**)malloc(1000 * sizeof(int*));
	for (i = 0; i < 1000; i++) {
		p[i] = (int*)malloc(4 * sizeof(int));
	}

	scanf("%d %d", &n, &c);

	for (i = 0; i < n; i++) {
		for (j = 0; j < 4; j++) {
			scanf("%d", &p[i][j]);
		}
		if (p[i][0] == c) {
			k = i;
		}
	}

	for (i = 0; i < n; i++) {
		if (p[i][1] > p[k][1]) {
			rank++;
		}
		else if (p[i][1] == p[k][1] && p[i][2] > p[k][2]) {
			rank++;
		}
		else if (p[i][1] == p[k][1] && p[i][2] == p[k][2] && p[i][3] > p[k][3]) {
			rank++;
		}
	}

	printf("%d", rank);

	for (i = 0; i < 1000; i++) {
		free(p[i]);
	}
	free(p);
	return 0;
}