import java.util.ArrayList;
import java.util.PriorityQueue;

class Solution {
    private static PriorityQueue<Integer> pq;

    private static int calc(ArrayList<int[]> reqs, int cnt) {
        int i;
        int len;
        int res;
        int prev;
        int[] req;

        len = reqs.size();
        for (i = 0; i < cnt; i++) {
            req = reqs.get(i);
            pq.offer(req[0] + req[1]);
        }
        res = 0;
        for (; i < len; i++) {
            req = reqs.get(i);
            prev = pq.poll();
            if (prev > req[0]) {
                res += prev - req[0];
                pq.offer(prev + req[1]);
            } else {
                pq.offer(req[0] + req[1]);
            }
        }
        pq.clear();
        return res;
    }

    private static int genVal(ArrayList<int[]> reqs, int[] vals) {
        int i;
        int val;
        int thr;

        val = calc(reqs, 1);
        thr = Math.min(vals.length, reqs.size());
        for (i = 2; i <= thr; i++) {
            vals[i - 1] = val - calc(reqs, i);
        }
        return val;
    }

    public int solution(int k, int n, int[][] reqs) {
        int i;
        int j;
        int l;
        int max;
        int len;
        int[] prev;
        int[] vals;
        int[] dp;
        ArrayList<int[]>[] arr;

        arr = new ArrayList[k];
        for (i = 0; i < k; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int[] req : reqs) {
            arr[req[2] - 1].add(req);
        }
        n -= k;
        max = 0;
        vals = new int[n + 1];
        pq = new PriorityQueue<>();
        prev = new int[n + 1];
        dp = new int[n + 1];
        for (i = 0; i < k; i++) {
            len = Math.min(arr[i].size(), n + 1);
            if (len == 0) {
                continue;
            }
            max += genVal(arr[i], vals);
            for (j = 0; j <= n; j++) {
                for (l = 1; l < len && j + l <= n; l++) {
                    dp[j + l] = Math.max(dp[j + l], prev[j] + vals[l]);
                }
            }
            System.arraycopy(dp, 0, prev, 0, n + 1);
        }
        return max - prev[n];
    }
}
