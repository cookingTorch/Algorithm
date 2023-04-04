#include <stdio.h>

int main(void) {
	int a, b, c, d, e;
	scanf("%d\n", &a);
	scanf("%d", &b);
	c = b / 100;
	e = b % 10;
	d = (b - (c * 100) - e) / 10;
	printf("%d\n", a * e);
	printf("%d\n", a * d);
	printf("%d\n", a * c);
	printf("%d", a * e + a * d * 10 + a * c * 100);
	return 0;
}
