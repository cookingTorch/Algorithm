#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int cmp(const void* a, const void* b) {
	if (strlen((const char*)a) != strlen((const char*)b)) {
		return strlen((const char*)a) - strlen((const char*)b);
	}
	else {
		return strcmp((char*)a, (char*)b);
	}
}

int main(void) {
	int i, n;
	char p[20000][51];

	scanf("%d", &n);
	for (i = 0; i < n; i++) {
		scanf("%s", p[i]);
	}

	qsort(p, n, sizeof(p[0]), cmp);

	for (i = 0; i < n; i++) {
		if (i == 0 || strcmp(p[i], p[i - 1]) != 0) {
			printf("%s\n", p[i]);
		}
	}

	return 0;
}