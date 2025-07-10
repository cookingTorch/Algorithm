class Solution {
    private static final long MAX = 399_999_999_900_000L;

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int c;
        int i;
        int len;
        int[] gs;
        long l;
        long r;
        long mid;
        long sum;
        long gold;
        long silver;
        long weight;

        len = t.length;
        c = a + b;
        gs = new int[len];
        for (i = 0; i < len; i++) {
            gs[i] = g[i] + s[i];
        }
        l = 0L;
        r = MAX + 1L;
        while (l < r) {
            mid = l + r >>> 1;
            gold = 0L;
            silver = 0L;
            sum = 0L;
            for (i = 0; i < len; i++) {
                weight = w[i] * (mid / t[i] + 1 >>> 1);
                gold += Math.min(g[i], weight);
                silver += Math.min(s[i], weight);
                sum += Math.min(gs[i], weight);
                if (gold >= a && silver >= b && sum >= c) {
                    break;
                }
            }
            if (i == len) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return r;
    }
}
