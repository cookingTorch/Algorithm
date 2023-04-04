#include <stdio.h>

int main(void) {
	int i = 0, count = 0;
	char str[1000000];
	scanf("%[^\n]s", str);
	while (str[i]) {
		if (str[i] == ' ') {
			count++;
		}
		i++;
	}
	if (str[0] == ' ') {
		count--;
	}
	if (str[i - 1] == ' ') {
		count--;
	}
	printf("%d", count + 1);
	return 0;
}