#include <stdio.h>

int main(void) {
	int n, i, v, a = 0, arr[100];
	scanf("%d", &n);
	for (i = 0; i < n; i++) {
		scanf("%d ", &arr[i]);
	}
	scanf("%d", &v);
	for (i = 0; i < n; i++) {
		if (arr[i] == v) {
			a++;
		}
	}
	printf("%d", a);
	return 0;
}