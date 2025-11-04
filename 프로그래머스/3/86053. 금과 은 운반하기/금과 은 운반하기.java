class Solution {
    private static final long MAX = 399_999_999_900_000L;

    private static long lowerBound(long ab, long a, long b, int len, long[] gs, long[] g, long[] s, long[] w, long[] t, long r) {
        int i;
        long l;
        long mid;
        long sum;
        long gold;
        long silver;
        long weight;

        l = 0L;
        while (l < r) {
            mid = l + r >>> 1;
            sum = 0L;
            gold = 0L;
            silver = 0L;
            for (i = 0; i < len; i++) {
                weight = w[i] * (mid / t[i] + 1L >>> 1);
                sum += Math.min(gs[i], weight);
                gold += Math.min(g[i], weight);
                silver += Math.min(s[i], weight);
                if (sum >= ab && gold >= a && silver >= b) {
                    break;
                }
            }
            if (i == len) {
                l = mid + 1L;
            } else {
                r = mid;
            }
        }
        return r;
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int i;
        int len;
        long ab;
        long[] time;
        long[] gold;
        long[] silver;
        long[] weight;
        long[] goldSilver;

        ab = a + b;
        len = w.length;
        gold = new long[len];
        silver = new long[len];
        goldSilver = new long[len];
        weight = new long[len];
        time = new long[len];
        for (i = 0; i < len; i++) {
            goldSilver[i] = (gold[i] = g[i]) + (silver[i] = s[i]);
            weight[i] = w[i];
            time[i] = t[i];
        }
        return lowerBound(ab, a, b, len, goldSilver, gold, silver, weight, time, MAX);
    }
}
