#include <stdio.h>

int main(void) {
	int l, n, i, s[50], a, b, cnt = 0, temp;
	scanf("%d", &l);
	for (i = 0; i < l; i++) {
		scanf("%d", &s[i]);
	}
	scanf("%d", &n);
	for (a = 1; a <= 1000; a++) {
		for (b = 2; b <= 1000; b++) {
			temp = 0;
			for (i = 0; i < l; i++) {
				if (s[i] >= a && s[i] <= b) {
					temp++;
				}
			}
			for (i = a; i <= b; i++) {
				if (i == n && temp == 0 && a < b) {
					cnt++;
					break;
				}
			}
		}
	}
	printf("%d", cnt);
	return 0;
}