#include <stdio.h>

int main(void) {
	int i, j, a, b, r, c, k, w, x, y, t, end = 0, chocolate = 0, room[21][21] = { 0 }, cnt[21][21], temp[21][21] = { 0 }, fan[21][21] = { 0 }, wall[21][21] = { 0 };
	int sum1 = 0, sum2;
	scanf("%d %d %d", &r, &c, &k);
	for (i = 1; i <= r; i++) {
		for (j = 1; j <= c; j++) {
			scanf("%d", &fan[i][j]);
		}
	}
	scanf("%d", &w);
	for (i = 1; i <= w; i++) {
		scanf("%d %d %d", &x, &y, &t);
		if (t == 0) {
			wall[x][y] += 1;
		}
		else if (t == 1) {
			wall[x][y] += 10;
		}
	}
	while (end == 0) {
		for (i = 1; i <= r; i++) {
			for (j = 1; j <= c; j++) {
				if (fan[i][j] == 1) {
					cnt[i][j + 1] = 1;
					for (a = 0; a <= 3; a++) {
						for (b = -1 * a; b <= a; b++) {
							if (i + b > 0 && i + b <= r && j + 1 + a > 0 && j + 1 + a + 1 <= c && cnt[i + b][j + 1 + a] == 1) {
								if (i + b - 1 > 0 && i + b - 1 <= r && j + 1 + a + 1 > 0 && j + 1 + a + 1 <= c && wall[i + b][j + 1 + a] % 10 == 0 && wall[i + b - 1][j + 1 + a] / 10 == 0) {
									cnt[i + b - 1][j + 1 + a + 1] = 1;
								}
								if (wall[i + b][j + 1 + a] / 10 == 0) {
									cnt[i + b][j + 1 + a + 1] = 1;
								}
								if (i + b + 1 > 0 && i + b + 1 <= r && wall[i + b + 1][j + 1 + a] % 10 == 0 && wall[i + b + 1][j + 1 + a] / 10 == 0) {
									cnt[i + b + 1][j + 1 + a + 1] = 1;
								}
							}
						}
					}
					for (a = 1; a <= r; a++) {
						for (b = 1; b <= c; b++) {
							if (cnt[a][b] == 1) {
								room[a][b] += 6 - (b - j);
							}
						}
					}
					for (a = 1; a <= r; a++) {
						for (b = 1; b <= c; b++) {
							cnt[a][b] = 0;
						}
					}
				}
				else if (fan[i][j] == 2) {
					cnt[i][j - 1] = 1;
					for (a = 0; a <= 3; a++) {
						for (b = -1 * a; b <= a; b++) {
							if (i + b > 0 && i + b <= r && j - 1 - a - 1 > 0 && j - 1 - a <= c && cnt[i + b][j - 1 - a] == 1) {
								if (i + b - 1 > 0 && i + b - 1 <= r && wall[i + b][j - 1 - a] % 10 == 0 && wall[i + b - 1][j - 1 - a - 1] / 10 == 0) {
									cnt[i + b - 1][j - 1 - a - 1] = 1;
								}
								if (wall[i + b][j - 1 - a - 1] / 10 == 0) {
									cnt[i + b][j - 1 - a - 1] = 1;
								}
								if (i + b + 1 > 0 && i + b + 1 <= r && wall[i + b + 1][j - 1 - a] % 10 == 0 && wall[i + b + 1][j - 1 - a - 1] / 10 == 0) {
									cnt[i + b + 1][j - 1 - a - 1] = 1;
								}
							}
						}
					}
					for (a = 1; a <= r; a++) {
						for (b = 1; b <= c; b++) {
							if (cnt[a][b] == 1) {
								room[a][b] += 6 - (j - b);
							}
						}
					}
					for (a = 1; a <= r; a++) {
						for (b = 1; b <= c; b++) {
							cnt[a][b] = 0;
						}
					}
				}
				else if (fan[i][j] == 3) {
					cnt[i - 1][j] = 1;
					for (a = 0; a <= 3; a++) {
						for (b = -1 * a; b <= a; b++) {
							if (j + b > 0 && j + b <= c && i - 1 - a <= r && i - 1 - a - 1 > 0 && cnt[i - 1 - a][j + b] == 1) {
								if (j + b - 1 > 0 && j + b - 1 <= c && wall[i - 1 - a][j + b - 1] / 10 == 0 && wall[i - 1 - a][j + b - 1] % 10 == 0) {
									cnt[i - 1 - a - 1][j + b - 1] = 1;
								}
								if (wall[i - 1 - a][j + b] % 10 == 0) {
									cnt[i - 1 - a - 1][j + b] = 1;
								}
								if (j + b + 1 > 0 && j + b + 1 <= c && wall[i - 1 - a][j + b] / 10 == 0 && wall[i - 1 - a][j + b + 1] % 10 == 0) {
									cnt[i - 1 - a - 1][j + b + 1] = 1;
								}
							}
						}
					}
					for (a = 1; a <= r; a++) {
						for (b = 1; b <= c; b++) {
							if (cnt[a][b] == 1) {
								room[a][b] += 6 - (i - a);
							}
						}
					}
					for (a = 1; a <= r; a++) {
						for (b = 1; b <= c; b++) {
							cnt[a][b] = 0;
						}
					}
				}
				else if (fan[i][j] == 4) {
					cnt[i + 1][j] = 1;
					for (a = 0; a <= 3; a++) {
						for (b = -1 * a; b <= a; b++) {
							if (j + b > 0 && j + b <= c && i + 1 + a > 0 && i + 1 + a + 1 <= r && cnt[i + 1 + a][j + b] == 1) {
								if (j + b - 1 > 0 && j + b - 1 <= c && wall[i + 1 + a][j + b - 1] / 10 == 0 && wall[i + 1 + a + 1][j + b - 1] % 10 == 0) {
									cnt[i + 1 + a + 1][j + b - 1] = 1;
								}
								if (wall[i + 1 + a + 1][j + b] % 10 == 0) {
									cnt[i + 1 + a + 1][j + b] = 1;
								}
								if (j + b + 1 > 0 && j + b + 1 <= c && wall[i + 1 + a][j + b] / 10 == 0 && wall[i + 1 + a + 1][j + b + 1] % 10 == 0) {
									cnt[i + 1 + a + 1][j + b + 1] = 1;
								}
							}
						}
					}
					for (a = 1; a <= r; a++) {
						for (b = 1; b <= c; b++) {
							if (cnt[a][b] == 1) {
								room[a][b] += 6 - (a - i);
							}
						}
					}
					for (a = 1; a <= r; a++) {
						for (b = 1; b <= c; b++) {
							cnt[a][b] = 0;
						}
					}
				}
			}
		}
		for (i = 1; i <= r; i++) {
			for (j = 1; j <= c; j++) {
				temp[i][j] = room[i][j];
				if (i - 1 > 0 && wall[i][j] % 10 == 0) {
					temp[i][j] += (room[i - 1][j] - room[i][j]) / 4;
				}
				if (j - 1 > 0 && wall[i][j - 1] / 10 == 0) {
					temp[i][j] += (room[i][j - 1] - room[i][j]) / 4;
				}
				if (i + 1 <= r && wall[i + 1][j] % 10 == 0) {
					temp[i][j] += (room[i + 1][j] - room[i][j]) / 4;
				}
				if (j + 1 <= c && wall[i][j] / 10 == 0) {
					temp[i][j] += (room[i][j + 1] - room[i][j]) / 4;
				}
			}
		}
		for (i = 1; i <= r; i++) {
			for (j = 1; j <= c; j++) {
				room[i][j] = temp[i][j];
			}
		}
		for (i = 1; i <= r; i++) {
			for (j = 1; j <= c; j++) {
				temp[i][j] = 0;
			}
		}
		for (j = 1; j <= c; j++) {
			if (room[1][j] > 0) {
				room[1][j]--;
			}
			if (room[r][j] > 0) {
				room[r][j]--;
			}
		}
		for (i = 2; i < r; i++) {
			if (room[i][1] > 0) {
				room[i][1]--;
			}
			if (room[i][c] > 0) {
				room[i][c]--;
			}
		}
		chocolate++;
		end = 1;
		for (i = 1; i <= r; i++) {
			for (j = 1; j <= c; j++) {
				if (fan[i][j] == 5) {
					if (room[i][j] < k) {
						end = 0;
					}
				}
			}
		}
		if (chocolate == 101) {
			break;
		}
	}
	printf("%d", chocolate);
	return 0;
}