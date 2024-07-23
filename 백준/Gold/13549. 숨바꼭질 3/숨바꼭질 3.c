#include <stdio.h>

int _n;
int _min;

int
hide_and_seek_min(int val1, int val2)
{
    return val1 < val2 ? val1 : val2;
}

void
hide_and_seek_dfs(int k, int dist)
{
    if (_n >= k) {
        _min = hide_and_seek_min(_min, dist + _n - k);
        return;
    }
    _min = hide_and_seek_min(_min, dist + k - _n);
    if (k & 1) {
        hide_and_seek_dfs(k + 1, dist + 1);
        hide_and_seek_dfs(k - 1, dist + 1);
    } else
        hide_and_seek_dfs(k >> 1, dist);
}

int
main()
{
    int k;

    scanf("%d %d", &_n, &k);
    if (_n >= k) {
        printf("%d", _n - k);
        return 0;
    }
    _min = k - _n;
    if (_n) {
        hide_and_seek_dfs(k, 0);
    }
    else {
        _n++;
        hide_and_seek_dfs(k, 1);
    }
    printf("%d", _min);
    return 0;
}