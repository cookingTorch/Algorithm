#include <stdio.h>
#include <stdlib.h>

#define ROBOT_WALL  '1'
#define ROBOT_EMPTY '0'

int _m;
int _d;
int _pos;
int _cnt;
int _delta[4];
char *_map;

void
robot_clean()
{
    if (_map[_pos] == ROBOT_EMPTY) {
        _map[_pos]--;
        _cnt++;
    }
}

int
robot_all_cleaned()
{
    int i;

    for (i = 0; i < 4; i++) {
        if (_map[_pos + _delta[_d = (_d + 3) % 4]] == ROBOT_EMPTY)
            break;
    }
    return i == 4;
}

int
robot_reverse()
{
    return _map[_pos += _delta[(_d + 2) % 4]] != ROBOT_WALL;
}

void
robot_forward()
{
    _pos += _delta[_d];
}

void
robot_start()
{
    for (;;) {
        robot_clean();
        if (robot_all_cleaned()) {
            if (!robot_reverse())
                break;
        }
        else
            robot_forward();
    }
}

int
main()
{
    int n;
    int r;
    int c;
    int i;

    scanf("%d %d\n%d %d %d", &n, &_m, &r, &c, &_d);
    _map = (char *) malloc((n * (_m <<= 1) + 1) * sizeof(char));
    scanf(" %[^2]", _map);
    _pos = r * _m + (c << 1);
    _delta[0] = -_m;
    _delta[1] = 2;
    _delta[2] = _m;
    _delta[3] = -2;
    _cnt = 0;
    robot_start();
    free(_map);
    printf("%d", _cnt);
    return 0;
}