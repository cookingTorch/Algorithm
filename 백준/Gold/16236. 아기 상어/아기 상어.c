#include <stdio.h>

int n, l, r, sx, sy, size = 2, cnt = 0, time = 0;
int dx[] = {-1, 0, 0, 1};
int dy[] = {0, -1, 1, 0};
int map[22][22], visited[22][22], q[400][2];

void add(int x, int y) {
    q[++r][0] = x;
    q[r][1] = y;
}

void poll(int* x, int* y) {
    *x = q[l][0];
    *y = q[l++][1];
}

int bfs() {
    int x, y, nx, ny, ex = 21, ey = 21, i, dist, len;

    time++;
    l = 0;
    r = -1;
    add(sx, sy);
    visited[sx][sy] = time;
    for (dist = 1; l <= r; dist++) {
        len = r - l + 1;
        while (len-- > 0) {
            poll(&x, &y);
            for (i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (visited[nx][ny] == time) {
                    continue;
                }
                if (map[nx][ny] == 0 || map[nx][ny] == size) {
                    add(nx, ny);
                    visited[nx][ny] = time;
                } else if (map[nx][ny] < size) {
                    if (nx < ex) {
                        ex = nx;
                        ey = ny;
                    } else if (nx == ex && ny < ey) {
                        ey = ny;
                    }
                }
            }
        }
        if (ex != 21) {
            sx = ex;
            sy = ey;
            map[sx][sy] = 0;
            if (++cnt == size) {
                size++;
                cnt = 0;
            }
            return dist;
        }
    }
    return 0;
}

int main() {
    int i, j, dist, ans;

    scanf("%d", &n);
    for (i = 1; i <= n; i++) {
        map[0][i] = 500;
        map[n + 1][i] = 500;
        map[i][0] = 500;
        map[i][n + 1] = 500;
        for (j = 1; j <= n; j++) {
            scanf("%d", &map[i][j]);
            if (map[i][j] == 9) {
                sx = i;
                sy = j;
                map[i][j] = 0;
            }
            visited[i][j] = 0;
        }
    }
    ans = 0;
    while ((dist = bfs()) != 0) {
        ans += dist;
    }
    printf("%d", ans);
}