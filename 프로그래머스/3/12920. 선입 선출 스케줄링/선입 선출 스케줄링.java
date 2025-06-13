import java.util.PriorityQueue;

class Solution {
    private static final int MAX = 500_000_001;
    
    private static final class Core implements Comparable<Core> {
        int end;
        int idx;
        int time;
        
        Core(int end, int idx, int time) {
            this.end = end;
            this.idx = idx;
            this.time = time;
        }
        
        Core update() {
            end += time;
            return this;
        }
        
        @Override
        public int compareTo(Core o) {
            if (this.end == o.end) {
                return this.idx - o.idx;
            }
            return this.end - o.end;
        }
    }

    private int lowerBound(int n, int[] cores) {
        int i;
        int cnt;
        int mid;
        int len;
        int left;
        int right;

        left = 0;
        right = MAX;
        len = cores.length;
        while (left < right) {
            mid = left + right >>> 1;
            cnt = 0;
            for (i = 0; i < len; i++) {
                if ((cnt += mid / cores[i] + 1) >= n) {
                    break;
                }
            }
            if (cnt < n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public int solution(int n, int[] cores) {
        int i;
        int len;
        int cnt;
        int time;
        PriorityQueue<Core> pq;

        time = lowerBound(n, cores) - 1;
        len = cores.length;
        pq = new PriorityQueue<>(len);
        for (i = 0; i < len; i++) {
            n -= cnt = time / cores[i] + 1;
            pq.offer(new Core(cnt * cores[i], i, cores[i]));
        }
        while (--n > 0) {
            pq.offer(pq.poll().update());
        }
        return pq.peek().idx + 1;
    }
}
