#include <iostream>
#include <sstream>

using namespace std;

constexpr char kLineBreak = '\n';
constexpr int kFive = 5;

int main() {
    int t;
    int n;
    int cnt;
    ostringstream os;

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> t;
    while (t--) {
        cin >> n;
        cnt = 0;
        for (n /= 5; n; n /= 5) {
            cnt += n;
        }
        os << cnt << kLineBreak;
    }
    cout << os.str();
}