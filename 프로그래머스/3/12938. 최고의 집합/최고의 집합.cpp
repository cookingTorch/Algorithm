#include <string>
#include <vector>

using namespace std;

constexpr int FAIL = -1;

vector<int> solution(int n, int s) {
    int i;
    int num;
    int thr;
    vector<int> ans;

    if (n > s) {
        ans.resize(1, FAIL);
        return ans;
    }
    ans.resize(n, (s - 1) / n);
    for (i = n - ((s - 1) % n + 1); i < n; i++) {
        ans[i]++;
    }
    return ans;
}