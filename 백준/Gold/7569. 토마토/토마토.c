#include <stdio.h>
#include <stdlib.h>

#define TOMATO_SIZE     100
#define TOMATO_FAIL     -1
#define TOMATO_RIPE     1
#define TOMATO_UNRIPE   0

typedef struct queue_s queue_t;
struct queue_s {
    int head;
    int tail;
    int *x;
    int *y;
    int *z;
    int *time;
};

const int dx[] = {-1, 1, 0, 0, 0, 0};
const int dy[] = {0, 0, -1, 1, 0, 0};
const int dz[] = {0, 0, 0, 0, -1, 1};

int _cnt;
int _map[TOMATO_SIZE][TOMATO_SIZE][TOMATO_SIZE];

queue_t *
queue_new(int height, int row, int col)
{
    int size;
    queue_t *q;

    q = (queue_t *) malloc(sizeof(queue_t));
    q->head = 0;
    q->tail = -1;
    size = height * row * col;
    q->x = (int *) malloc(size * sizeof(int));
    q->y = (int *) malloc(size * sizeof(int));
    q->z = (int *) malloc(size * sizeof(int));
    q->time = (int *) malloc(size * sizeof(int));
    return q;
}

int
queue_is_empty(queue_t *q)
{
    return q->head > q->tail;
}

void
queue_offer(queue_t *q, int x, int y, int z, int time)
{
    q->x[++(q->tail)] = x;
    q->y[q->tail] = y;
    q->z[q->tail] = z;
    q->time[q->tail] = time;
}

void
queue_poll(queue_t *q, int *x, int *y, int *z, int *time)
{
    *x = q->x[q->head];
    *y = q->y[q->head];
    *z = q->z[q->head];
    *time = q->time[q->head++];
}

void
queue_free(queue_t *q)
{
    free(q->x);
    free(q->y);
    free(q->z);
    free(q->time);
    free(q);
}

int
tomato_bfs(queue_t *q, int h, int n, int m)
{
    int d;
    int x;
    int y;
    int z;
    int nx;
    int ny;
    int nz;
    int rc;

    rc = 0;
    while(_cnt && !queue_is_empty(q)) {
        queue_poll(q, &x, &y, &z, &rc);
        rc++;
        for (d = 0; d < 6; d++) {
            nx = x + dx[d];
            ny = y + dy[d];
            nz = z + dz[d];
            if (nx < 0 || nx >= h || ny < 0 || ny >= n || nz < 0 || nz >= m)
                continue;
            if (_map[nx][ny][nz] == TOMATO_UNRIPE) {
                queue_offer(q, nx, ny, nz, rc);
                _map[nx][ny][nz]++;
                _cnt--;
            }
        }
    }
    return _cnt ? TOMATO_FAIL : rc;
}

int
main()
{
    int h;
    int n;
    int m;
    int i;
    int j;
    int k;
    queue_t *q;

    scanf("%d %d %d\n", &m, &n, &h);
    _cnt = 0;
    q = queue_new(h, n, m);
    for (i = 0; i < h; i++) {
        for (j = 0; j < n; j++) {
            for (k = 0; k < m; k++) {
                scanf("%d", &(_map[i][j][k]));
                if (_map[i][j][k] == TOMATO_RIPE)
                    queue_offer(q, i, j, k, 0);
                else if (_map[i][j][k] == TOMATO_UNRIPE)
                    _cnt++;
            }
        }
    }
    printf("%d", tomato_bfs(q, h, n, m));
    queue_free(q);
    return 0;
}