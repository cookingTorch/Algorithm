#include <iostream>
#include <sstream>
#include <queue>

using namespace std;

constexpr int kMaxN = 1'001;

struct Line {
    int u;
    int v;
    int weight;
    Line(const int from, const int to, const int weight) : u(from), v(to), weight(weight) {}
};

struct Cmp {
    bool operator()(const Line *edge1, const Line *edge2) const {
        return edge1->weight > edge2->weight;
    }
};

struct Edge {
    int to;
    int weight;
    Edge *next;
    Edge(const int to, const int weight, Edge *next) : to(to), weight(weight), next(next) {}
};

int n;
int roots[kMaxN];
int path[kMaxN];
int previous[kMaxN];
bool visited[kMaxN];
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

void Bfs(int x, int y) {
    int i;
    int curr;
    int next;
    Edge *edge;
    queue<int> q;

    for (i = 1; i <= n; i++) {
        visited[i] = false;
    }
    q.push(x);
    visited[x] = true;
    for(;;) {
        curr = q.front();
        for (edge = adj[curr]; edge; edge = edge->next) {
            if (visited[next = edge->to]) {
                continue;
            }
            path[next] = edge->weight;
            previous[next] = curr;
            if (next == y) {
                return;
            }
            visited[next] = true;
            q.push(next);
        }
        q.pop();
    }
}

int MaxEdgeWeight(const int x, const int y) {
    int max = 0;

    Bfs(x, y);
    for (int i = y; i != x; i = previous[i]) {
        if (max < path[i]) {
            max = path[i];
        }
    }
    return max;
}

int main() {
    int m;
    int a;
    int b;
    int c;
    int q;
    int u;
    int v;
    int x;
    int y;
    int i;
    int sum;
    int weight;
    Line *line;
    ostringstream os;
    priority_queue<Line *, vector<Line *>, Cmp> pq;

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    while (m--) {
        cin >> a >> b >> c;
        pq.push(new Line(a, b, c));
    }
    for (i = 1; i <= n; i++) {
        roots[i] = 0;
        adj[i] = nullptr;
    }
    sum = 0;
    for (i = 1; i < n;) {
        line = pq.top();
        if (Union(u = line->u, v = line->v)) {
            weight = line->weight;
            adj[u] = new Edge(v, weight, adj[u]);
            adj[v] = new Edge(u, weight, adj[v]);
            sum += weight;
            i++;
        }
        pq.pop();
    }
    cin >> q;
    while (q--) {
        cin >> x >> y;
        os << sum - MaxEdgeWeight(x, y) << '\n';
    }
    cout << os.str();
}