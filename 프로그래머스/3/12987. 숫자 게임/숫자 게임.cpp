#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> A, vector<int> B) {
    int i;
    int j;
    int sum;
    int size;

    sort(A.begin(), A.end());
    sort(B.begin(), B.end());
    size = A.size();
    sum = 0;
    for (i = 0, j = -1; i < size; i++) {
        for (++j; j < size && A[i] >= B[j]; j++);
        if (j == size) {
            break;
        }
    }
    return i;
}