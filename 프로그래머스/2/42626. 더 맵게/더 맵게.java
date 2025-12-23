import java.util.PriorityQueue;

class Solution {
    private static final int FAIL = -1;
    
    public int solution(int[] scoville, int K) {
        int i;
        int len;
        int cnt;
        int min;
        PriorityQueue<Integer> pq;

        pq = new PriorityQueue<>();
        len = scoville.length;
        for (i = 0; i < len; i++) {
            pq.offer(scoville[i]);
        }
        cnt = 0;
        while (pq.size() > 1) {
            if ((min = pq.poll()) >= K) {
                return cnt;
            }
            pq.offer(min + (pq.poll() << 1));
            cnt++;
        }
        if (pq.peek() >= K) {
            return cnt;
        }
        return FAIL;
    }
}
