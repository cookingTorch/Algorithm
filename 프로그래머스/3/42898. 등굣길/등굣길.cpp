#include <string>
#include <vector>

using namespace std;

constexpr int PUDDLE = 1;
constexpr int MOD = 1'000'000'007;

int solution(int m, int n, vector<vector<int>> puddles) {
    int i;
    int j;
    int size;
    vector<vector<int>> map;

    map.resize(m + 1, vector<int>(n + 1, 0));
    size = puddles.size();
    for (i = 0; i < size; i++) {
        map[puddles[i][0]][puddles[i][1]] = PUDDLE;
    }
    map[0][1] = 1;
    for (i = 1; i <= m; i++) {
        for (j = 1; j <= n; j++) {
            if (map[i][j] == PUDDLE) {
                map[i][j] = 0;
            } else {
                map[i][j] = (map[i - 1][j] + map[i][j - 1]) % MOD;
            }
        }
    }
    return map[m][n];
}