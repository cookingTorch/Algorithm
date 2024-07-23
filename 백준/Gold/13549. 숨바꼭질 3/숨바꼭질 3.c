#include <stdio.h>
#include <stdlib.h>

#define INT_MAX          2147483647
#define ARRAY_DEQUE_SIZE 20000

typedef struct array_deque_s array_deque_t;
struct array_deque_s {
    int head;
    int tail;
    int elements[ARRAY_DEQUE_SIZE];
};

void
array_deque_init(array_deque_t *dq)
{
    dq->head = 0;
    dq->tail = 0;
}

void
array_deque_add_last(array_deque_t *dq, int val)
{
    dq->tail = (dq->tail + 1) % ARRAY_DEQUE_SIZE;
    dq->elements[dq->tail] = val;
}

void
array_deque_add_first(array_deque_t *dq, int val)
{
    dq->elements[dq->head] = val;
    dq->head = (dq->head + ARRAY_DEQUE_SIZE - 1) % ARRAY_DEQUE_SIZE;
}

int
array_deque_poll_first(array_deque_t *dq)
{
    return dq->elements[dq->head = (dq->head + 1) % ARRAY_DEQUE_SIZE];
}

int
hide_and_seek_bfs_0_1(int n, int k)
{
    int i;
    int size;
    int curr;
    int *dist;
    array_deque_t dq;
    
    if (n >= k)
        return n - k;
    size = k + 2;
    dist = (int *) malloc(size * (sizeof(int)));
    for (i = 0; i <= size; i++)
        dist[i] = INT_MAX;
    dist[n] = 0;
    array_deque_init(&dq);
    array_deque_add_last(&dq, n);
    for (;;) {
        curr = array_deque_poll_first(&dq);
        if (curr == k)
            break;
        if (curr != 0 && (curr << 1) < size && dist[curr] < dist[curr << 1]) {
            dist[curr << 1] = dist[curr];
            array_deque_add_first(&dq, curr << 1);
        }
        if (curr > 0 && dist[curr] + 1 < dist[curr - 1]) {
            dist[curr - 1] = dist[curr] + 1;
            array_deque_add_last(&dq, curr - 1);
        }
        if (curr <= k && dist[curr] + 1 < dist[curr + 1]) {
            dist[curr + 1] = dist[curr] + 1;
            array_deque_add_last(&dq, curr + 1);
    
        }
    }
    return dist[k];
}

int
main()
{
    int n;
    int k;

    scanf("%d %d", &n, &k);
    printf("%d", hide_and_seek_bfs_0_1(n, k));
    return 0;
}