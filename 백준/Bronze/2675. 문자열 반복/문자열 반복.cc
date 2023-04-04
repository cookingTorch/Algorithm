#include <stdio.h>

int main(void) {
	int t, r, i, j, k;
	char str[20];
	scanf("%d", &t);
	for (i = 0; i < t; i++) {
		scanf("%d %s", &r, str);
		k = 0;
		while (str[k]) {
			for (j = 0; j < r; j++) {
				printf("%c", str[k]);
			}
			k++;
		}
		printf("\n");
	}
	return 0;
}