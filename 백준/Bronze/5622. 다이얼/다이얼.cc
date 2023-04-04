#include <stdio.h>

int main(void) {
	int i = 0, t = 0;
	char str[16];
	scanf("%s", str);
	while (str[i]) {
		if (str[i] <= 'R') {
			t += (str[i] - 'A') / 3 + 3;
		}
		else if (str[i] == 'S') {
			t += 8;
		}
		else if (str[i] <= 'V') {
			t += 9;
		}
		else {
			t += 10;
		}
		i++;
	}
	printf("%d", t);
	return 0;
}