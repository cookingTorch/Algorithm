#include <stdio.h>

int main(void) {
	char a;
	while (scanf("%c", &a) != EOF) {
		printf("%c", a);
	}
	return 0;
}