#include <stdio.h>

int main(void) {
	char chess[51][51], w[8][8], b[8][8];
	int n, m, i, j, k, l, wd, bd, min = 64;
	for (i = 0; i < 8; i++) {
		for (j = 0; j < 8; j++) {
			if (i % 2 == 0) {
				if (j % 2 == 0) {
					w[i][j] = 'W';
					b[i][j] = 'B';
				}
				else {
					w[i][j] = 'B';
					b[i][j] = 'W';
				}
			}
			else {
				if (j % 2 == 0) {
					w[i][j] = 'B';
					b[i][j] = 'W';
				}
				else {
					w[i][j] = 'W';
					b[i][j] = 'B';
				}
			}
		}
	}
	scanf("%d %d", &n, &m);
	for (i = 0; i < n; i++) {
		scanf("%s", &chess[i]);
	}
	for (i = 0; i <= n - 8; i++) {
		for (j = 0; j <= m - 8; j++) {
			wd = 0;
			bd = 0;
			for (k = 0; k < 8; k++) {
				for (l = 0; l < 8; l++) {
					if (chess[k + i][l + j] != w[k][l]) {
						wd++;
					}
					if (chess[k + i][l + j] != b[k][l]) {
						bd++;
					}
				}
			}
			if (wd < min) {
				min = wd;
			}
			if (bd < min) {
				min = bd;
			}
		}
	}
	printf("%d", min);
	return 0;
}