class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int temp;
        int pickupIdx;
        int pickupCnt;
        int deliveryIdx;
        int deliveryCnt;
        long totalDist;
        
        for (deliveryIdx = n - 1; deliveryIdx >= 0 && deliveries[deliveryIdx] == 0; deliveryIdx--);
        for (pickupIdx = n - 1; pickupIdx >= 0 && pickups[pickupIdx] == 0; pickupIdx--);
        totalDist = 0;
        while (deliveryIdx >= 0 || pickupIdx >= 0) {
            totalDist += Math.max(deliveryIdx, pickupIdx) + 1 << 1;
            for (deliveryCnt = 0; deliveryIdx >= 0 && deliveryCnt < cap;) {
                temp = Math.min(deliveries[deliveryIdx], cap - deliveryCnt);
                deliveryCnt += temp;
                deliveries[deliveryIdx] -= temp;
                for (; deliveryIdx >= 0 && deliveries[deliveryIdx] == 0; deliveryIdx--);
            }
            for (pickupCnt = 0; pickupIdx >= 0 && pickupCnt < cap;) {
                temp = Math.min(pickups[pickupIdx], cap - pickupCnt);
                pickupCnt += temp;
                pickups[pickupIdx] -= temp;
                for (; pickupIdx >= 0 && pickups[pickupIdx] == 0; pickupIdx--);
            }
        }
        return totalDist;
    }
}