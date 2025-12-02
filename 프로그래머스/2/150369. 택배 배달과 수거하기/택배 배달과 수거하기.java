class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int i;
        int cnt;
        int max;
        int diff;
        int dCnt;
        int pCnt;
        int dTmp;
        int pTmp;
        long dist;

        dist = 0L;
        cnt = 0;
        dCnt = 0;
        dTmp = 0;
        pCnt = 0;
        pTmp = 0;
        for (i = n - 1; i >= 0; i--) {
            dTmp -= deliveries[i];
            if (dTmp < 0) {
                dTmp = -dTmp;
                diff = (dTmp + cap - 1) / cap;
                dCnt += diff;
                dTmp = diff * cap - dTmp;
            }
            pTmp -= pickups[i];
            if (pTmp < 0) {
                pTmp = -pTmp;
                diff = (pTmp + cap - 1) / cap;
                pCnt += diff;
                pTmp = diff * cap - pTmp;
            }
            max = Math.max(dCnt, pCnt);
            dist += (long) (max - cnt) * (i + 1 << 1);
            cnt = max;
        }
        return dist;
    }
}
