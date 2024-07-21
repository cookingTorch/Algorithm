#include <stdio.h>
#define MAX_PQ_SIZE 100001

typedef struct priority_queue_s priority_queue_t;
struct priority_queue_s {
	int size;
	int arr[MAX_PQ_SIZE];
	int (*cmp)(int, int);
};

void
priority_queue_offer(priority_queue_t *pq, int val)
{
	int idx;
	int parent_idx;
	int parent_val;
	int *arr;
	int (*cmp)(int, int);

	arr = pq->arr;
	cmp = pq->cmp;
	idx = ++pq->size;
	pq->arr[idx] = val;
	while (idx > 1) {
		parent_idx = idx >> 1;
		parent_val = pq->arr[parent_idx];
		if (cmp(val, parent_val) >= 0)
			break;
		pq->arr[idx] = parent_val;
		idx = parent_idx;
	}
	pq->arr[idx] = val;
}

int
priority_queue_poll(priority_queue_t *pq)
{
	int val;
	int size;
	int last;
	int child;
	int right;
	int parent;
	int *arr;
	int (*cmp)(int, int);

	if (!pq->size) {
		return 0;
	}
	arr = pq->arr;
	cmp = pq->cmp;
	val = arr[1];
	last = pq->arr[pq->size];
	pq->arr[pq->size] = 0;
	pq->size--;
	size = pq->size;
	parent = 1;
	child = parent << 1;
	while (child <= size) {
		right = child + 1;
		if (right <= size && cmp(arr[child], arr[right]) > 0)
			child = right;
		if (cmp(last, arr[child]) <= 0)
			break;
		arr[parent] = arr[child];
		parent = child;
		child = parent << 1;
	}
	arr[parent] = last;
	return val;
}

int
cmp_reverse_order(int num1, int num2)
{
	if (num1 == num2) {
		return 0;
	}
	if (num1 > num2) {
		return -1;
	}
	return 1;
}

int
main()
{
	int n;
	int x;
	priority_queue_t pq;

	pq.size = 0;
	pq.cmp = &cmp_reverse_order;
	scanf("%d", &n);
	while (n-- > 0) {
		scanf("%d", &x);
		if (x == 0) {
			printf("%d\n", priority_queue_poll(&pq));
		}
		else
			priority_queue_offer(&pq, x);
	}
	return 0;
}
