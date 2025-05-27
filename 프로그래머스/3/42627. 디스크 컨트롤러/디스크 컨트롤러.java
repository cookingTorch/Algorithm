import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    private static final int INF = 501_001;

    private static final class Job implements Comparable<Job> {
        int s;
        int l;
        int priority;

        Job(int idx, int s, int l) {
            this.s = s;
            this.l = l;
            priority = (l << 10 | s) << 9 | idx;
        }

        @Override
        public int compareTo(Job o) {
            return priority - o.priority;
        }
    }

    public int solution(int[][] jobs) {
        int i;
        int len;
        int end;
        int ans;
        Job job;
        Job[] arr;
        PriorityQueue<Job> pq;

        len = jobs.length;
        arr = new Job[len + 1];
        pq = new PriorityQueue<>(len);
        for (i = 0; i < len; i++) {
            arr[i] = new Job(i, jobs[i][0], jobs[i][1]);
        }
        arr[len] = new Job(len, INF, INF);
        Arrays.sort(arr, 0, len + 1, (o1, o2) -> o1.s - o2.s);
        ans = 0;
        end = 0;
        i = 0;
        for (;;) {
            while (arr[i].s <= end) {
                pq.offer(arr[i++]);
            }
            if (pq.size() == 0) {
                end = arr[i].s;
                if (end == INF) {
                    break;
                }
                while (arr[i].s <= end) {
                    pq.offer(arr[i++]);
                }
            }
            job = pq.poll();
            end += job.l;
            ans += end - job.s;

        }
        return ans / len;
    }
}
