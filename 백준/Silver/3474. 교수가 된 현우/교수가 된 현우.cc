#include <iostream>
#include <sstream>

using namespace std;

constexpr char kLineBreak = '\n';
constexpr int kFive = 5;

int solution() {
    int n;
    int i;
    int cnt;

    cin >> n;
    cnt = 0;
    for (i = kFive; i <= n; i *= kFive) {
        cnt += n / i;
    }
    return cnt;
}

int main() {
    int t;
    ostringstream os;

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> t;
    while (t--) {
        os << solution() << kLineBreak;
    }
    cout << os.str();
}