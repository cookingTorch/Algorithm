#include <stdio.h>

int main(void) {
	int h, m, t;
	scanf("%d %d", &h, &m);
	t = h * 60 + m;
	t = t - 45;
	if (t < 0) {
		t = t + 60 * 24;
	}
	printf("%d %d", t / 60, t % 60);
	return 0;
}
