#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> routes) {
    int i;
    int cnt;
    int size;
    int last;

    sort(routes.begin(), routes.end(), [](auto& a, auto& b) {
        return a[1] < b[1];
    });
    cnt = 1;
    last = routes[0][1];
    size = routes.size();
    for (i = 1; i < size; i++) {
        if (routes[i][0] > last) {
            cnt++;
            last = routes[i][1];
        }
    }
    return cnt;
}