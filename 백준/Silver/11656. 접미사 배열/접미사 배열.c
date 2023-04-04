#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int cmp(const void* a, const void* b) {
	return strcmp((char*)a, (char*)b);
}

int main(void) {
	int i, j;
	char s[1001], t[1000][1001] = { 0 };

	scanf("%s", s);

	for (i = 0; i < strlen(s); i++) {
		for (j = i; j < strlen(s); j++) {
			t[i][j - i] = s[j];
		}
	}

	qsort(t, strlen(s), sizeof(t[0]), cmp);

	for (i = 0; i < strlen(s); i++) {
		printf("%s\n", t[i]);
	}

	return 0;
}