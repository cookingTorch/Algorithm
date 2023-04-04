#include <stdio.h>

int main(void) {
	int a, b;
	char str1[8];
	scanf("%[^\n]s", str1);
	a = (str1[2] - '0') * 100 + (str1[1] - '0') * 10 + (str1[0] - '0');
	b = (str1[6] - '0') * 100 + (str1[5] - '0') * 10 + (str1[4] - '0');
	if (a > b) {
		printf("%d", a);
	}
	else {
		printf("%d", b);
	}
	return 0;
}