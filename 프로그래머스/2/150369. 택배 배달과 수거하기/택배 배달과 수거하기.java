class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int i;
        int cnt;
        int pickupCap;
        int deliveryCap;
        long dist;
        
        for (dist = 0L, deliveryCap = 0, pickupCap = 0, i = n - 1; i >= 0; i--) {
            deliveryCap -= deliveries[i];
            pickupCap -= pickups[i];
            if (deliveryCap < 0 || pickupCap < 0) {
                cnt = (-Math.min(deliveryCap, pickupCap) + cap - 1) / cap;
                deliveryCap += cnt * cap;
                pickupCap += cnt * cap;
                dist += (i + 1) * cnt;
            }
        }
        return dist << 1;
    }
}