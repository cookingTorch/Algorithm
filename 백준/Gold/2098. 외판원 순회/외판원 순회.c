#include <stdio.h>
#include <stdlib.h>

#define TSP_INF 1073741823

int _n;
int **_dp;
int **_cost;

int
tsp_get_dp(int curr, int visited)
{
    int rc;
    int sum;
    int next;

    if (_dp[curr][visited])
        return _dp[curr][visited];
    rc = TSP_INF;
    for (next = 1; next < _n; next++) {
        if (!(visited & (1 << next)) && _cost[curr][next]) {
            if ((sum = tsp_get_dp(next, visited | (1 << next)) + _cost[curr][next]) < rc)
                rc = sum;
        }
    }
    return _dp[curr][visited] = rc;
}

int
main()
{
    int i;
    int j;

    scanf("%d", &_n);
    _cost = (int **) malloc(_n * sizeof(int *));
    _dp = (int **) malloc(_n * sizeof(int *));
    for (i = 0; i < _n; i++) {
        _cost[i] = (int *) malloc(_n * sizeof(int));
        _dp[i] = (int *) calloc(1 << _n, sizeof(int));
        scanf("%d", _cost[i]);
        _dp[i][(1 << _n) - 1] = (_cost[i][0] ? _cost[i][0] : TSP_INF);
        for (j = 1; j < _n; j++)
            scanf("%d", &(_cost[i][j]));
    }
    printf("%d", tsp_get_dp(0, 1));
}