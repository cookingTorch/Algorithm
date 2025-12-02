class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int i;
        int cnt;
        int max;
        int prev;
        int dCnt;
        int pCnt;
        int dTmp;
        int pTmp;
        long dist;

        dist = 0L;
        prev = 0;
        dCnt = 0;
        dTmp = 0;
        pCnt = 0;
        pTmp = 0;
        for (i = n - 1; i >= 0; i--) {
            if ((dTmp -= deliveries[i]) < 0) {
                cnt = (-dTmp + cap - 1) / cap;
                dCnt += cnt;
                dTmp = cnt * cap + dTmp;
            }
            if ((pTmp -= pickups[i]) < 0) {
                cnt = (-pTmp + cap - 1) / cap;
                pCnt += cnt;
                pTmp = cnt * cap + pTmp;
            }
            max = Math.max(dCnt, pCnt);
            dist += (long) (max - prev) * (i + 1 << 1);
            prev = max;
        }
        return dist;
    }
}
