#include <string>
#include <vector>

using namespace std;

int roots[201];

int find(int v) {
    if (roots[v] <= 0) {
        return v;
    }
    return roots[v] = find(roots[v]);
}

bool merge(int u, int v) {
    u = find(u);
    v = find(v);
    if (u == v) {
        return false;
    }
    if (roots[u] > roots[v]) {
        roots[u] = v;
    } else {
        if (roots[u] == roots[v]) {
            roots[u]--;
        }
        roots[v] = u;
    }
    return true;
}

int solution(int n, vector<vector<int>> computers) {
    int i;
    int j;
    int cnt;

    for (i = 1; i <= n; i++) {
        roots[i] = 0;
    }
    cnt = n;
    for (i = 1; i < n; i++) {
        for (j = 0; j < i; j++) {
            if (computers[i][j] && merge(i + 1, j + 1)) {
                cnt--;
            }
        }
    }
    return cnt;
}