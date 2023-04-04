#include <stdio.h>

int main(void) {
	int i, j, count, n, max = -1;
	char a, s[1000000];
	scanf("%s", s);
	for (i = 'A'; i <= 'Z'; i++) {
		j = 0;
		count = 0;
		while (s[j]) {
			if (i == s[j] || i + 'a' - 'A' == s[j]) {
				count++;
			}
			j++;
		}
		if (count > max) {
			max = count;
			n = 0;
			a = i;
		}
		else if (count == max) {
			n++;
		}
	}
	if (n == 0) {
		printf("%c", a);
	}
	else {
		printf("?");
	}
	return 0;
}