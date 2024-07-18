#include <stdio.h>
#define SIZE 1001

int main() {
    int n;
    int i;
    int j;
    int num;
    int prev;
    int curr;
    int next;
	int arr[SIZE];
    int left[SIZE];
    int right[SIZE];

    scanf("%d", &n);
	for (i = 1; i < n; i++) {
        scanf("%d", arr + i);
		left[i + 1] = i;
		right[i] = i + 1;
	}
    scanf("%d", arr + n);
	right[n] = 1;
	left[1] = n;
	curr = 1;
    printf("%d ", curr);
	prev = left[curr];
	next = right[curr];
	right[prev] = next;
	left[next] = prev;
	for (i = 0; i < n - 1; i++) {
		prev = left[curr];
		next = right[curr];
		num = arr[curr];
		if (num > 0) {
			for (j = 0; j < num; j++) {
				curr = right[curr];
			}
		} else {
			for (j = 0; j > num; j--) {
				curr = left[curr];
			}
		}
        printf("%d ", curr);
		prev = left[curr];
		next = right[curr];
		right[prev] = next;
		left[next] = prev;
	}
    return 0;
}