#include <stdio.h>

int main(void) {
	int t, k, n, i, a, b, c, arr[15][14] = { 0 };
	scanf("%d", &t);
	for (a = 0; a <= 14; a++) {
		for (b = 0; b < 14; b++) {
			if (a == 0) {
				arr[a][b] =  b + 1;
			}
			else {
				for (c = 0; c <= b; c++) {
					arr[a][b] += arr[a - 1][c];
				}
			}
		}
	}
	for (i = 0; i < t; i++) {
		scanf("%d\n%d", &k, &n);
		printf("%d", arr[k][n - 1]);
		if (i < t - 1) {
			printf("\n");
		}
	}
	
	return 0;
}