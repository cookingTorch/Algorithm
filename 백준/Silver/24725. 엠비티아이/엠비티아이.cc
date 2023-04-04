#include <stdio.h>

int main(void) {
	int n, m, i, j, k, cnt = 0;
	char arr[200][201];
	scanf("%d %d", &n, &m);
	for (i = 0; i < n; i++) {
		scanf("%s", &arr[i]);
	}
	for (i = 0; i < n; i++) {
		for (j = 0; j < m; j++) {
			if (j <= m - 4) {
				if (arr[i][j] == 'E' || arr[i][j] == 'I') {
					if (arr[i][j + 1] == 'N' || arr[i][j + 1] == 'S') {
						if (arr[i][j + 2] == 'F' || arr[i][j + 2] == 'T') {
							if (arr[i][j + 3] == 'P' || arr[i][j + 3] == 'J') {
								cnt++;
							}
						}
					}
				}
				else if (arr[i][j] == 'P' || arr[i][j] == 'J') {
					if (arr[i][j + 1] == 'F' || arr[i][j + 1] == 'T') {
						if (arr[i][j + 2] == 'N' || arr[i][j + 2] == 'S') {
							if (arr[i][j + 3] == 'E' || arr[i][j + 3] == 'I') {
								cnt++;
							}
						}
					}
				}
			}
			if (i <= n - 4) {
				if (arr[i][j] == 'E' || arr[i][j] == 'I') {
					if (arr[i + 1][j] == 'N' || arr[i + 1][j] == 'S') {
						if (arr[i + 2][j] == 'F' || arr[i + 2][j] == 'T') {
							if (arr[i + 3][j] == 'P' || arr[i + 3][j] == 'J') {
								cnt++;
							}
						}
					}
				}
				else if (arr[i][j] == 'P' || arr[i][j] == 'J') {
					if (arr[i + 1][j] == 'F' || arr[i + 1][j] == 'T') {
						if (arr[i + 2][j] == 'N' || arr[i + 2][j] == 'S') {
							if (arr[i + 3][j] == 'E' || arr[i + 3][j] == 'I') {
								cnt++;
							}
						}
					}
				}
			}
			if (i <= n - 4 && j <= m - 4) {
				if (arr[i][j] == 'E' || arr[i][j] == 'I') {
					if (arr[i + 1][j + 1] == 'N' || arr[i + 1][j + 1] == 'S') {
						if (arr[i + 2][j + 2] == 'F' || arr[i + 2][j + 2] == 'T') {
							if (arr[i + 3][j + 3] == 'P' || arr[i + 3][j + 3] == 'J') {
								cnt++;
							}
						}
					}
				}
				else if (arr[i][j] == 'P' || arr[i][j] == 'J') {
					if (arr[i + 1][j + 1] == 'F' || arr[i + 1][j + 1] == 'T') {
						if (arr[i + 2][j + 2] == 'N' || arr[i + 2][j + 2] == 'S') {
							if (arr[i + 3][j + 3] == 'E' || arr[i + 3][j + 3] == 'I') {
								cnt++;
							}
						}
					}
				}
				if (arr[i][j + 3] == 'E' || arr[i][j + 3] == 'I') {
					if (arr[i + 1][j + 2] == 'N' || arr[i + 1][j + 2] == 'S') {
						if (arr[i + 2][j + 1] == 'F' || arr[i + 2][j + 1] == 'T') {
							if (arr[i + 3][j] == 'P' || arr[i + 3][j] == 'J') {
								cnt++;
							}
						}
					}
				}
				else if (arr[i][j + 3] == 'P' || arr[i][j + 3] == 'J') {
					if (arr[i + 1][j + 2] == 'F' || arr[i + 1][j + 2] == 'T') {
						if (arr[i + 2][j + 1] == 'N' || arr[i + 2][j + 1] == 'S') {
							if (arr[i + 3][j] == 'E' || arr[i + 3][j] == 'I') {
								cnt++;
							}
						}
					}
				}
			}
		}
	}
	printf("%d", cnt);
	return 0;
}