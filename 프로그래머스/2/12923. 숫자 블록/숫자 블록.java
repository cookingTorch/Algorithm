class Solution {
    private static final int MAX = 10_000_000;

    public int[] solution(long begin, long end) {
        int i;
        int len;
        int[] ans;
        long j;
        long thr;
        long num;
        long start;

        len = (int) (end - begin + 1);
        ans = new int[len];
        for (i = 0; i < len; i++) {
            num = begin + i;
            thr = (long) Math.sqrt(num);
            start = Math.max(2, (num + MAX - 1) / MAX);
            for (j = start; j <= thr; j++) {
                if (num % j == 0L) {
                    ans[i] = (int) (num / j);
                    break;
                }
            }
            if (j > thr) {
                for (j = start - 1; j > 0; j--) {
                    if (num % j == 0L) {
                        ans[i] = (int) j;
                        break;
                    }
                }
            }
        }
        if (begin == 1) {
            ans[0] = 0;
        }
        return ans;
    }
}
