#include <iostream>
#include <unordered_set>
#include <unordered_map>
#include <sstream>

using namespace std;

constexpr char kLineBreak = '\n';
constexpr int kMaxN = 100'001;
constexpr int kMaxM = 100'001;
constexpr int kTreeSize = kMaxM << 2;
constexpr long long kMod = 1'000'000LL;

struct Range {
    int left;
    int right;
    Range *next;

    Range(const int left, Range *next) : left(left), right(kMaxM), next(next) {};
};

int roots[kMaxN];
int queries[kMaxM][2];
unordered_set<long long> tree[kTreeSize];
ostringstream os;

class UndoLog {
    int u;
    int v;
    int root_u;
    int root_v;

public:
    UndoLog *next;

    UndoLog(int u, int v, int root_u, int root_v, UndoLog *next) : u(u), v(v), root_u(root_u), root_v(root_v), next(next) {};

    void Undo() {
        roots[u] = root_u;
        roots[v] = root_v;
    }
};

int Find(int v) {
    for (; roots[v] > 0; v = roots[v]);
    return v;
}

void Union(int u, int v, UndoLog** undo_log) {
    if (u == v) {
        return;
    }
    *undo_log = new UndoLog(u, v, roots[u], roots[v], *undo_log);
    if (roots[u] > roots[v]) {
        roots[u] = v;
    } else {
        if (roots[u] == roots[v]) {
            roots[u]--;
        }
        roots[v] = u;
    }
}

int Connected(int query[]) {
    return Find(query[0]) == Find(query[1]);
}

void Update(int node, int start, int end, int left, int right, long long key) {
    int mid;

    if (right < start || end < left) {
        return;
    }
    if (left <= start && end <= right) {
        tree[node].insert(key);
        return;
    }
    mid = (start + end) >> 1;
    Update(node << 1, start, mid, left, right, key);
    Update(node << 1 | 1, mid + 1, end, left, right, key);
}

void Divide(int node, int start, int end) {
    int mid;
    UndoLog *undo_log;

    undo_log = nullptr;
    for (long long key : tree[node]) {
        Union(Find(key / kMod), Find(key % kMod), &undo_log);
    }
    if (start == end) {
        os << Connected(queries[start]) << kLineBreak;
    } else {
        mid = (start + end) >> 1;
        Divide(node << 1, start, mid);
        Divide(node << 1 | 1, mid + 1, end);
    }
    for (; undo_log; undo_log = undo_log->next) {
        undo_log->Undo();
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
        roots[i] = 0;
    }
    for (pair<const long long, Range *>& entry : um) {
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