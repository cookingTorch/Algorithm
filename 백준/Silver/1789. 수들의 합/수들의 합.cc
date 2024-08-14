#include <iostream>
#include <cmath>

using namespace std;

int main() {
    long long s;

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cin >> s;
    cout << static_cast<int>((-1.0 + sqrt(1LL + (8LL * s))) / 2.0);
}