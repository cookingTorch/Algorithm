#include <stdio.h>
#include <stdlib.h>

#define TOMATO_FAIL         -1
#define TOMATO_RIPE         2
#define TOMATO_UNRIPE       1
#define TOMATO_DIMENSION    3

typedef struct queue_s queue_t;
struct queue_s {
    int head;
    int tail;
    int *elements;
};

char *_map;
int _cnt;
int _arr[TOMATO_DIMENSION];
int _delta[TOMATO_DIMENSION];
int _reverse[TOMATO_DIMENSION];

void
queue_init(queue_t *q, int size)
{
    q->head = 0;
    q->tail = -1;
    q->elements = (int *) malloc(size * sizeof(int));
}

int
queue_is_empty(queue_t *q)
{
    return q->head > q->tail;
}

int
queue_poll(queue_t *q)
{
    return q->elements[q->head++];
}

void
queue_offer(queue_t *q, int val)
{
    q->elements[++(q->tail)] = val;
}

void
queue_free(queue_t *q)
{
    free(q->elements);
}

void
queue_swap(queue_t *q1, queue_t *q2)
{
    queue_t temp;

    temp = *q1;
    *q1 = *q2;
    *q2 = temp;
}

void
tomato_recursive_input(int pos, int dimension, queue_t *q)
{
    int i;

    if (dimension == TOMATO_DIMENSION) {
        scanf("%hhd", &_map[pos]);
        if (++_map[pos] == TOMATO_RIPE)
            queue_offer(q, pos);
        else if (_map[pos] == TOMATO_UNRIPE)
            _cnt++;
        return;
    }
    for (i = 0; i < _arr[dimension]; i++) {
        tomato_recursive_input(pos, dimension + 1, q);
        pos += _delta[dimension];
    }
}

int
tomato_bfs(queue_t *q, int size)
{
    int d;
    int rc;
    int pos;
    int next;
    queue_t temp;

    queue_init(&temp, size);
    for (rc = 0; _cnt && !queue_is_empty(q); rc++) {
        while (!queue_is_empty(q)) {
            pos = queue_poll(q);
            for (d = 0; d < TOMATO_DIMENSION; d++) {
                if ((d == 0 ? pos : (pos % _delta[d - 1])) / _delta[d] > 0) {
                    next = pos - _delta[d];
                    if (_map[next] == TOMATO_UNRIPE) {
                        queue_offer(&temp, next);
                        _map[next]++;
                        _cnt--;
                    }
                }
                if ((d == 0 ? pos : (pos % _delta[d - 1])) / _delta[d] < _arr[d] - 1) {
                    next = pos + _delta[d];
                    if (_map[next] == TOMATO_UNRIPE) {
                        queue_offer(&temp, next);
                        _map[next]++;
                        _cnt--;
                    }
                }
            }
        }
        queue_swap(&temp, q);
    }
    queue_free(&temp);
    return _cnt == 0 ? rc : TOMATO_FAIL;
}

int
main()
{
    int i;
    int size;
    queue_t q;

    size = 1;
    for (i = TOMATO_DIMENSION - 1; i >= 0; i--) {
        scanf("%d", &_arr[i]);
        size *= _arr[i];
    }
    _delta[TOMATO_DIMENSION - 1] = 1;
    for (i = TOMATO_DIMENSION - 1; i > 0; i--)
        _delta[i - 1] = _delta[i] * _arr[i];
    _map = (char *) malloc(size * sizeof(char));
    _cnt = 0;
    queue_init(&q, size);
    tomato_recursive_input(0, 0, &q);
    printf("%d", tomato_bfs(&q, size));
    queue_free(&q);
    free(_map);
    return 0;
}