#include <stdio.h>

int main(void) {
	int h, m, c, t;
	scanf("%d %d\n%d", &h, &m, &c);
	t = h * 60 + m;
	t = t + c;
	if (t >= 60 * 24) {
		t = t - 60 * 24;
	}
	printf("%d %d", t / 60, t % 60);
	return 0;
}
