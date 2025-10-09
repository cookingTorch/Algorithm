#include <iostream>
#include <vector>

using namespace std;

int solution(int n, vector<int> stations, int w) {
    int i;
    int cnt;
    int gap;
    int size;
    int range;

    range = w << 1 | 1;
    size = stations.size();
    cnt = 0;
    if ((gap = stations[0] - w - 1) > 0) {
        cnt += (gap - 1) / range + 1;
    }
    for (i = 1; i < size; i++) {
        if ((gap = stations[i] - stations[i - 1] - range) > 0) {
            cnt += (gap - 1) / range + 1;
        }
    }
    if ((gap = n - stations[size - 1] - w) > 0) {
        cnt += (gap - 1) / range + 1;
    }
    return cnt;
}