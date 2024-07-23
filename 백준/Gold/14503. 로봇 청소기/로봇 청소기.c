#include <stdio.h>

#define ROBOT_WALL      '1'
#define ROBOT_EMPTY     '0'
#define ROBOT_MAX_ROW   50
#define ROBOT_MAX_COL   100

const int _dr[4] = {-1, 0, 1, 0};
const int _dc[4] = {0, 2, 0, -2};

char _map[ROBOT_MAX_ROW][ROBOT_MAX_COL];

int
main()
{
    int n;
    int m;
    int r;
    int c;
    int d;
    int i;
    int cnt;

    scanf("%d", &n);
    scanf("%d", &m);
    scanf("%d", &r);
    scanf("%d", &c);
    scanf("%d", &d);
    for (i = 0; i < n; i++) {
        scanf(" %[^\n]", _map[i]);
    }
    c <<= 1;
    cnt = 0;
    for (;;) {
        if (_map[r][c] == ROBOT_EMPTY) {
            _map[r][c]--;
            cnt++;
        }
        for (i = 0; i < 4; i++) {
            d = (d + 3) % 4;
            if (_map[r + _dr[d]][c + _dc[d]] == ROBOT_EMPTY)
                break;
        }
        if (i == 4) {
            r += _dr[(d + 2) % 4];
            c += _dc[(d + 2) % 4];
            if (_map[r][c] == ROBOT_WALL)
                break;
        } else {
            r += _dr[d];
            c += _dc[d];
        }
    }
    printf("%d", cnt);
}