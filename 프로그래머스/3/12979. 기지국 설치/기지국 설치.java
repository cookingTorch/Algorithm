class Solution {
    public int solution(int n, int[] stations, int w) {
        int i;
        int len;
        int cnt;
        int gap;
        int range;

        range = w << 1 | 1;
        len = stations.length;
        cnt = 0;
        if ((gap = stations[0] - w - 1) > 0) {
            cnt += (gap - 1) / range + 1;
        }
        for (i = 1; i < len; i++) {
            if ((gap = stations[i] - stations[i - 1] - range) > 0) {
                cnt += (gap - 1) / range + 1;
            }
        }
        if ((gap = n - stations[len - 1] - w) > 0) {
            cnt += (gap - 1) / range + 1;
        }
        return cnt;
    }
}
