#include <stdio.h>
#include <stdlib.h>

#define ROBOT_WALL  '1'
#define ROBOT_EMPTY '0'

const int _dr[4] = {-1, 0, 1, 0};
const int _dc[4] = {0, 2, 0, -2};

int _r;
int _c;
int _d;
int _cnt;
char **_map;

void
robot_clean() {
    if (_map[_r][_c] == ROBOT_EMPTY) {
        _map[_r][_c]--;
        _cnt++;
    }
}

int
robot_all_cleaned() {
    int i;

    for (i = 0; i < 4; i++) {
        _d = (_d + 3) % 4;
        if (_map[_r + _dr[_d]][_c + _dc[_d]] == ROBOT_EMPTY)
            break;
    }
    return i == 4;
}

int
robot_reverse() {
    return _map[_r += _dr[(_d + 2) % 4]][_c += _dc[(_d + 2) % 4]] != ROBOT_WALL;
}

void
robot_forward() {
    _r += _dr[_d];
    _c += _dc[_d];
}

int
main()
{
    int n;
    int m;
    int i;

    scanf("%d", &n);
    scanf("%d", &m);
    scanf("%d", &_r);
    scanf("%d", &_c);
    scanf("%d", &_d);
    m <<= 1;
    _map = (char **) malloc(n * sizeof(char *));
    for (i = 0; i < n; i++) {
        _map[i] = (char *) malloc(m * sizeof(char));
        scanf(" %[^\n]", _map[i]);
    }
    _c <<= 1;
    _cnt = 0;
    for (;;) {
        robot_clean();
        if (robot_all_cleaned()) {
            if (!robot_reverse())
                break;
        }
        else
            robot_forward();
    }
    while (n-- > 0)
        free(_map[n]);
    free(_map);
    printf("%d", _cnt);
}