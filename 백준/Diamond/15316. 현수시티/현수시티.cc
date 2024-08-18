#include <iostream>
#include <unordered_set>
#include <sstream>

using namespace std;

constexpr int kMaxNmq = 200'001;
constexpr int kTreeSize = kMaxNmq << 2;
constexpr long long kMod = 1'000'000LL;
const string kYes = "YES\n";
const string kNo = "NO\n";

struct Range {
    int left;
    int right;
    Range *next;

    Range(const int left, Range *next) : left(left), right(kMaxNmq), next(next) {};
};

int roots[kMaxNmq];
int queries[kMaxNmq][2];
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

bool Connected(int query[]) {
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
        if (Connected(queries[start])) {
            os << kYes;
        } else {
            os << kNo;
        }
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
    int q;
    int i;
    int idx;
    int query;
    bool activated[kMaxNmq];
    long long a;
    long long b;
    long long key;
    long long temp;
    long long edges[kMaxNmq];
    Range *range;
    Range *ranges[kMaxNmq];

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> m;
    for (i = 1; i <= m; i++) {
        cin >> a >> b;
        if (a > b) {
            temp = a;
            a = b;
            b = temp;
        }
        edges[i] = a * kMod + b;
    }
    for (i = 1; i <= m; i++) {
        cin >> activated[i];
        if (activated[i]) {
            ranges[i] = new Range(0, nullptr);
        } else {
            ranges[i] = nullptr;
        }
    }
    cin >> q;
    idx = 0;
    while (q--) {
        cin >> query;
        if (query == 1) {
            cin >> i;
            if (!(range = ranges[i]) || range->right < idx) {
                ranges[i] = new Range(idx + 1, range);
            } else if (range->right == kMaxNmq) {
                range->right = idx;
            } else {
                range->right = kMaxNmq;
            }
        } else {
            cin >> a >> b;
            queries[++idx][0] = a;
            queries[idx][1] = b;
        }
    }
    for (i = 1; i <= n; i++) {
        roots[i] = 0;
    }
    for (i = 1; i <= m; i++) {
        key = edges[i];
        for (range = ranges[i]; range; range = range->next) {
            if (range->left > range->right) {
                continue;
            }
            Update(1, 1, idx, range->left, range->right, key);
        }
    }
    Divide(1, 1, idx);
    cout << os.str();
}