#include <stdio.h>
#include <math.h>

int main(void) {
	double n, m, x = 0, y = -1;
	scanf("%lf", &n);
	m = n;
	while (m >= 3) {
		if (pow(3, x) <= m) {
			x++;
		}
		else {
			x--;
			if (x == y) {
				m = 2;
				break;
			}
			else {
				y = x;
				m -= pow(3, x);
			}
			x = 0;
		}
	}
	if (m == 2 || n == 0) {
		printf("NO");
	}
	else {
		printf("YES");
	}
	return 0;
}