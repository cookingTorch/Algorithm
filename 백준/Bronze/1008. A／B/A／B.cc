#include <stdio.h>

int main(void) {
	int a;
	double b;
	scanf("%d %lf", &a, &b);
	printf("%.10f", a / b);
	return 0;
}
