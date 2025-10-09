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
        ans.emplace_back(FAIL);
        return ans;
    }
    ans.reserve(n);
    num = (s - 1) / n;
    thr = n - ((s - 1) % n + 1);
    for (i = 0; i < thr; i++) {
        ans.emplace_back(num);
    }
    num++;
    for (; i < n; i++) {
        ans.emplace_back(num);
    }
    return ans;
}