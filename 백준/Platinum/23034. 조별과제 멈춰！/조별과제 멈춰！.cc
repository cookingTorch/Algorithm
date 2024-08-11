#include <bits/stdc++.h>

using namespace std;
using Line = tuple<int, int, int>;
using Edge = tuple<int, int, void *>;

constexpr int kMaxN = 1'001;
constexpr int kMaxM = 100'001;

int n;
int dest;
int max_weight;
int roots[kMaxN];
Edge *adj[kMaxN];

int Find(int v) {
    if (roots[v] <= 0) {
        return v;
    }
    return roots[v] = Find(roots[v]);
}

bool Union(const int u, const int v) {
    const int ru = Find(u);
    const int rv = Find(v);

    if (ru == rv) {
        return false;
    }
    if (roots[ru] > roots[rv]) {
        roots[ru] = rv;
    } else {
        if (roots[ru] == roots[rv]) {
            roots[ru]--;
        }
        roots[rv] = ru;
    }
    return true;
}

bool Dfs(const int parent, const int curr) {
    int to;
    int weight;
    void *next;
    Edge *edge;

    if (curr == dest) {
        return true;
    }
    for (edge = adj[curr]; edge; edge = static_cast<Edge *>(next)) {
        tie(to, weight, next) = *edge;
        if (to == parent) {
            continue;
        }
        if (Dfs(curr, to)) {
            if (max_weight < weight) {
                max_weight = weight;
            }
            return true;
        }
    }
    return false;
}

int main() {
    int m;
    int q;
    int u;
    int v;
    int i;
    int cnt;
    int sum;
    int start;
    int weight;
    Line *lines[kMaxM];
    ostringstream os;

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    for (i = 0; i < m; i++) {
        cin >> u >> v >> weight;
        lines[i] = new Line(weight, u, v);
    }
    sort(lines, lines + m, [](const Line *line1, const Line *line2) {
        return get<0>(*line1) < get<0>(*line2);
    });
    for (i = 1; i <= n; i++) {
        roots[i] = 0;
        adj[i] = nullptr;
    }
    sum = 0;
    cnt = 1;
    for (i = 0;; i++) {
        tie(weight, u, v) = *lines[i];
        if (Union(u, v)) {
            adj[u] = new Edge(v, weight, adj[u]);
            adj[v] = new Edge(u, weight, adj[v]);
            sum += weight;
            if (++cnt == n) {
                break;
            }
        }
    }
    cin >> q;
    while (q--) {
        cin >> start >> dest;
        max_weight = 0;
        Dfs(0, start);
        os << sum - max_weight << '\n';
    }
    cout << os.str();
}