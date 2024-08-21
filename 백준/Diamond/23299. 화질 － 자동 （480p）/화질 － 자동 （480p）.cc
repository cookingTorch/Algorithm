#include <iostream>
#include <algorithm>
#include <vector>
#include <cstring>

using namespace std;

constexpr int kMin = INT32_MIN >> 1;
constexpr int kSize = 1 << 10;
constexpr int kMaxN = 501;
constexpr int kMaxW = 5001;
constexpr int kBandWidths = 7;

int w;
int bandwidth[kBandWidths];
int range[kMaxN][2];
int satisfaction[kMaxN][kBandWidths];
int dp[kSize << 1][kMaxW];
vector<int> times;
vector<int> tree[kSize << 1];

void Add(int node, int start, int end, int left, int right, int idx) {
    int mid;

    if(right < start || end < left) {
        return;
    }
    if(left <= start && end <= right) {
        tree[node].push_back(idx);
        return;
    }
    mid = (start + end) / 2;
    Add(node << 1, start, mid, left, right, idx);
    Add(node << 1 | 1, mid + 1, end, left, right, idx);
}

void Divide(int node, int start, int end){
    int j;
    int k;
    int mid;

    for (int i : tree[node]) {
        for (k = w; k >= 0; k--) {
            for (j = 1; j <= 6; j++) {
                if (k - bandwidth[j] >= 0) {
                    dp[node][k] = max(dp[node][k], dp[node][k - bandwidth[j]] + satisfaction[i][j]);
                }
            }
        }
    }
    if (start != end) {
        mid = start + end >> 1;
        memcpy(dp[node << 1], dp[node], sizeof dp[node]);
        memcpy(dp[node << 1 | 1], dp[node], sizeof dp[node]);
        Divide(node << 1, start, mid);
        Divide(node << 1 | 1, mid + 1, end);
    }
}

int main() {
    int n;
    int i;
    int j;
    long long sum;

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> n >> w;
    times.reserve(n << 1);
    for (i = 6; i >= 1; i--) {
        cin >> bandwidth[i];
    }
    for (i = 1; i <= n; i++) {
        cin >> range[i][0] >> range[i][1];
        for(j = 6; j >= 1; j--) {
            cin >> satisfaction[i][j];
        }
    }
    for (i = 1; i <= n; i++) {
        times.push_back(range[i][0]), times.push_back(range[i][1]);
    }
    sort(times.begin(), times.end());
    for (i = 1; i <= n; i++) {
        range[i][0] = lower_bound(times.begin(), times.end(), range[i][0]) - times.begin();
        range[i][1] = lower_bound(times.begin(), times.end(), range[i][1]) - times.begin();
    }
    for (i = 1; i <= n; i++) {
        Add(1, 0, kSize - 1, range[i][0] + 1, range[i][1], i);
    }
    memset(dp, kMin, sizeof dp);
    dp[1][0] = 0;
    Divide(1, 0, kSize - 1);
    sum = 0;
    for(i = 1; i < times.size(); i++) {
        sum += ((long long) *max_element(dp[i | kSize], dp[i | kSize] + w + 1)) * (long long) (times[i] - times[i - 1]);
    }
    cout << sum;
}