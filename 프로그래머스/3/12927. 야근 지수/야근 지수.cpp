#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long solution(int n, vector<int> works) {
    int i;
    int l;
    int r;
    int len;
    int mid;
    int sum;
    int work;
    long long ans;

    len = works.size();
    r = 0;
    sum = 0;
    for (i = 0; i < len; i++) {
        sum += works[i];
        r = max(r, works[i]);
    }
    if (n >= sum) {
        return 0LL;
    }
    work = 0;
    l = 0;
    r++;
    while (l < r) {
        mid = l + r >> 1;
        sum = 0;
        for (i = 0; i < len; i++) {
            if (works[i] > mid) {
                sum += works[i] - mid;
            }
        }
        if (sum > n) {
            l = mid + 1;
        } else {
            r = mid;
            work = sum;
        }
    }
    ans = 0LL;
    for (i = 0; i < len; i++) {
        if (works[i] >= r) {
            if (work == n) {
                ans += (long long) r * r;
            } else {
                ans += (long long) (r - 1) * (r - 1);
                work++;
            }
        } else {
            ans += (long long) works[i] * works[i];
        }
    }
    return ans;
}