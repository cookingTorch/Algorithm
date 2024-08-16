#include <iostream>
#include <unordered_set>
#include <unordered_map>
#include <vector>
#include <stack>
#include <sstream>

using namespace std;

constexpr char kLineBreak = '\n';
constexpr int kMaxN = 100'001;
constexpr int kMaxM = 100'001;
constexpr int kTreeSize = kMaxM << 2;
constexpr long long kMod = 1'000'000LL;

struct Range {
public:
    int left;
    int right;
    Range *next;

    Range(const int left, Range *next) : left(left), right(kMaxM), next(next) {};
};

int depth[kMaxN];
int roots[kMaxN];
int queries[kMaxM][2];
stack<int *> undo_logs;
unordered_set<long long> tree[kTreeSize];
ostringstream os;

void Update(int node, int start, int end, int left, int right, long long key) {
    int mid;

    if (right < start || end < left) {
        return;
    }
    if (left <= start && end <= right) {
        tree[node].insert(key);
        return;
    }
    mid = start + end >> 1;
    Update(node << 1, start, mid, left, right, key);
    Update(node << 1 | 1, mid + 1, end, left, right, key);
}

int find(int v) {
    for (; roots[v] != v; v = roots[v]);
    return v;
}

void Undo() {
    int u;
    int v;
    int *undo_log;

    undo_log = undo_logs.top();
    u = undo_log[0];
    v = undo_log[1];
    depth[u] = undo_log[2];
    roots[v] = v;
    undo_logs.pop();
}

bool Union(int u, int v) {
    int temp;

    if (u == v) {
        return false;
    }
    if (depth[u] < depth[v]) {
        temp = u;
        u = v;
        v = temp;
    }
    undo_logs.push(new int[3] {u, v, depth[u]});
    if (depth[u] == depth[v]) {
        depth[u]++;
    }
    roots[v] = u;
    return true;
}

int Connected(int query[]) {
    return find(query[0]) == find(query[1]);
}

void Divide(int node, int start, int end) {
    int cnt;
    int mid;

    cnt = 0;
    for (long long key : tree[node]) {
        if (Union(find(key / kMod), find(key % kMod))) {
            cnt++;
        }
    }
    if (start == end) {
        os << Connected(queries[start]) << kLineBreak;
    } else {
        mid = start + end >> 1;
        Divide(node << 1, start, mid);
        Divide(node << 1 | 1, mid + 1, end);
    }
    while (cnt--) {
        Undo();
    }
}

int main() {
    int n;
    int m;
    int i;
    int idx;
    int query;
    long long a;
    long long b;
    long long key;
    long long temp;
    Range *range;
    unordered_map<long long, Range *> um;

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    idx = 0;
    while (m--) {
        cin >> query >> a >> b;
        if (a > b) {
            temp = a;
            a = b;
            b = temp;
        }
        key = a * kMod + b;
        switch (query) {
            case 1:
                if (!(range = um[key]) || range->right < idx) {
                    um[key] = new Range(idx + 1, range);
                } else {
                    range->right = kMaxM;
                }
                break;
            case 2:
                um[key]->right = idx;
                break;
            case 3:
                queries[++idx][0] = a;
                queries[idx][1] = b;
                break;
            default:
                break;
        }
    }
    for (i = 1; i <= n; i++) {
        depth[i] = 1;
        roots[i] = i;
    }
    for (auto& entry : um) {
        key = entry.first;
        for (range = entry.second; range; range = range->next) {
            if (range->left > range->right) {
                continue;
            }
            Update(1, 1, idx, range->left, range->right, key);
        }
    }
    Divide(1, 1, idx);
    cout << os.str();
}