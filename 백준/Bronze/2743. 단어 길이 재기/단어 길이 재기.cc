#include <stdio.h>

int main(void) {
	int i = 0;
	char str[100];
	scanf("%s", str);
	while (str[i]) {
		i++;
	}
	printf("%d", i);
}