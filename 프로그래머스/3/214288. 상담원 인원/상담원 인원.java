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
        int[] dp;
        int[] val;
        int[] next;
        int[][] vals;
        ArrayList<int[]>[] arr;

        arr = new ArrayList[k];
        for (i = 0; i < k; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int[] req : reqs) {
            arr[req[2] - 1].add(req);
        }
        max = 0;
        n -= k;
        vals = new int[k][];
        pq = new PriorityQueue<>();
        for (i = 0; i < k; i++) {
            vals[i] = new int[Math.min(arr[i].size(), n + 1)];
            if (vals[i].length == 0) {
                continue;
            }
            max += genVal(arr[i], vals[i]);
        }
        dp = new int[n + 1];
        for (i = 0; i < k; i++) {
            val = vals[i];
            if (val.length == 0) {
                continue;
            }
            len = val.length;
            next = new int[n + 1];
            for (j = 0; j <= n; j++) {
                for (l = 0; l < len && j + l <= n; l++) {
                    next[j + l] = Math.max(next[j + l], dp[j] + val[l]);
                }
            }
            dp = next;
        }
        return max - dp[n];
    }
}