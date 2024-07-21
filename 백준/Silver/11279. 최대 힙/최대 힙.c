#include <stdio.h>
#define MAX_PQ_SIZE 100001

typedef struct priority_queue_s priority_queue_t;
struct priority_queue_s {
	int size;
	int heap[MAX_PQ_SIZE];
	int (*cmp)(int, int);
};

void
priority_queue_init(priority_queue_t *pq, int (*cmp)(int, int)) {
    pq->size = 0;
    pq->cmp = cmp;
}

void
priority_queue_offer(priority_queue_t *pq, int val)
{
	int idx;
	int parent_idx;
	int parent_val;
	int *heap;
	int (*cmp)(int, int);

	cmp = pq->cmp;
	heap = pq->heap;
	idx = ++pq->size;
	heap[idx] = val;
	while (idx > 1) {
		parent_idx = idx >> 1;
		parent_val = pq->heap[parent_idx];
		if (cmp(val, parent_val) >= 0)
			break;
		heap[idx] = parent_val;
		idx = parent_idx;
	}
	heap[idx] = val;
}

int
priority_queue_poll(priority_queue_t *pq)
{
	int val;
	int size;
	int last;
	int child;
	int parent;
	int *heap;
	int (*cmp)(int, int);

	if (!pq->size) {
		return 0;
	}
	cmp = pq->cmp;
	heap = pq->heap;
	val = heap[1];
	last = heap[pq->size];
	heap[pq->size] = 0;
	size = --pq->size;
	parent = 1;
	child = parent << 1;
	while (child <= size) {
		if (child < size && cmp(heap[child], heap[child + 1]) > 0)
			child++;
		if (cmp(last, heap[child]) <= 0)
			break;
		heap[parent] = heap[child];
		parent = child;
		child = parent << 1;
	}
	heap[parent] = last;
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

    priority_queue_init(&pq, &cmp_reverse_order);
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
