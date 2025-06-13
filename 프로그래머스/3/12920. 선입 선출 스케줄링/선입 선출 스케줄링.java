import java.util.PriorityQueue;

class Solution {
    private static final int MAX = 500_000_001;

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
            if (i == len) {
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
        int[] cur;
        PriorityQueue<int[]> pq;

        time = lowerBound(n, cores) - 1;
        pq = new PriorityQueue<>((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        len = cores.length;
        for (i = 0; i < len; i++) {
            n -= cnt = time / cores[i] + 1;
            pq.offer(new int[] {cnt * cores[i], i});
        }
        while (--n > 0) {
            cur = pq.poll();
            cur[0] += cores[cur[1]];
            pq.offer(cur);
        }
        return pq.peek()[1] + 1;
    }
}
