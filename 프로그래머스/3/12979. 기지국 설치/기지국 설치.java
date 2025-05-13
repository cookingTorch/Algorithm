class Solution {
    public int solution(int n, int[] stations, int w) {
        int i;
        int len;
        int cnt;
        int range;

        range = w << 1 | 1;
        len = stations.length;
        cnt = 0;
        if (stations[0] - w > 1) {
            cnt += (stations[0] - w - 2) / range + 1;
        }
        for (i = 1; i < len; i++) {
            if (stations[i - 1] + (w << 1) + 1 < stations[i]) {
                cnt += (stations[i] - stations[i - 1] - range - 1) / range + 1;
            }
        }
        if (stations[len - 1] + w < n) {
            cnt += (n - stations[len - 1] - w - 1) / range + 1;
        }
        return cnt;
    }
}
