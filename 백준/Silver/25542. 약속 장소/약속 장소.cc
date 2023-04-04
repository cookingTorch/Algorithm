#include <stdio.h>

int main(void) {
	int n, l, i, j, c, d, t = 1, cnt, a = -1, b = -1;
	char place[20][21], ans[21];
	scanf("%d %d", &n, &l);
	for (i = 0; i < n; i++) {
		scanf("%s", &place[i]);
	}
	
	if (n >= 2) {
		cnt = 0;
		for (j = 0; j < l; j++) {
			if (place[0][j] == place[1][j]) {
				ans[j] = place[0][j];
			}
			else {
				cnt++;
				if (cnt == 1) {
					a = j;
				}
				else if (cnt == 2) {
					b = j;
				}
				else {
					t = 0;
					break;
				}
			}
		}
		if (t == 1) {
			for (c = 0; c < n; c++) {
				for (d = 0; d < n; d++) {
					t = 1;
					if (a != -1) {
						ans[a] = place[c][a];
					}
					if (b != -1) {
						ans[b] = place[d][b];
					}
					for (i = 0; i < n; i++) {
						cnt = 0;
						for (j = 0; j < l; j++) {
							if (ans[j] != place[i][j]) {
								cnt++;
							}
						}
						if (cnt > 1) {
							t = 0;
							break;
						}
					}
					if (t == 1) {
						break;
					}
				}
				if (t == 1) {
					break;
				}
			}
		}
	}
	else if (n == 1) {
		for (j = 0; j < l; j++) {
			ans[j] = place[0][j];
		}
	}
	if (t == 1) {
		ans[l] = 0;
		printf("%s", ans);
	}
	else {
		printf("CALL FRIEND");
	}
	return 0;
}