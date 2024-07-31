#include <stdio.h>
#include <stdlib.h>

#define TSP_INF 1073741823

int
main()
{
    int n;
    int i;
    int j;
    int bit;
    int val;
    int min;
    int full;
    int visited;
    int **dp;
    int **cost;

    scanf("%d", &n);
    full = (1 << n) - 1;
    cost = (int **) malloc(n * sizeof(int *));
    dp = (int **) malloc(n * sizeof(int *));
    for (i = 0; i < n; i++) {
        cost[i] = (int *) malloc(n * sizeof(int));
        dp[i] = (int *) malloc((full + 1) * sizeof(int));
        for (j = 0; j < n; j++)
            scanf("%d", &(cost[i][j]));
        for (j = 0; j <= full; j++)
            dp[i][j] = TSP_INF;
    }
    dp[0][1] = 0;
    for (visited = 1; visited <= full; visited++) {
        if (!(visited & 1))
            continue;
        for (i = 1; (1 << i) < visited; i++) {
            if (!(visited & (1 << i)) || !(visited - (1 << i)))
                continue;
            bit = visited ^ (1 << i);
            min = TSP_INF;
            for (j = 0; j < n; j++) {
                if (!cost[j][i])
                    continue;
                val = dp[j][bit] + cost[j][i];
                if (val < min)
                    min = val;
            }
            dp[i][visited] = min;
        }
    }
    min = TSP_INF;
    for (i = 0; i < n; i++) {
        if (!cost[i][0])
            continue;
        val = dp[i][full] + cost[i][0];
        if (val < min)
            min = val;
    }
    printf("%d", min);
}