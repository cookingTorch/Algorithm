#include <stdio.h>

int main(void) {
	int n, i;
	scanf("%d", &n);
	for (i = 1; i <= n / 4; i++) {
		printf("long ");
	}
	printf("int");
	return 0;
}