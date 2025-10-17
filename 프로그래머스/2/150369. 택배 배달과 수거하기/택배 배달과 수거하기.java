class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int pIdx;
        int dIdx;
        int remain;
        long dist;

        for (dIdx = n - 1; dIdx >= 0 && deliveries[dIdx] == 0; dIdx--);
        for (pIdx = n - 1; pIdx >= 0 && pickups[pIdx] == 0; pIdx--);
        dist = 0;
        while (dIdx >= 0 || pIdx >= 0) {
            dist += Math.max(dIdx, pIdx) + 1 << 1;
            for (remain = cap; dIdx >= 0; remain -= deliveries[dIdx--]) {
                if (deliveries[dIdx] > remain) {
                    deliveries[dIdx] -= remain;
                    break;
                }
            }
            for (remain = cap; pIdx >= 0; remain -= pickups[pIdx--]) {
                if (pickups[pIdx] > remain) {
                    pickups[pIdx] -= remain;
                    break;
                }
            }
        }
        return dist;
    }
}
