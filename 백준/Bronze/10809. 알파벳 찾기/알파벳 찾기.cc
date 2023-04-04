#include <stdio.h>

int main(void) {
	int i, j, k;
	char str[100];
	scanf("%s", str);
	for (i = 'a'; i <= 'z'; i++) {
		j = 0;
		k = -1;
		while (str[j]) {
			if (i == str[j]) {
				k = j;
				break;
			}
			j++;
		}
		printf("%d ", k);
	}
	return 0;
}