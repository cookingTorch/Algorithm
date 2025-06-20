class Solution {
    private static final int MAX = 10_000_000;

    public int[] solution(long begin, long end) {
        int i;
        int j;
        int len;
        int num;
        int thr;
        int sqrt;
        int start;
        int[] ans;

        start = (int) begin;
        len = (int) end - start + 1;
        ans = new int[len];
        loop:
        for (i = start == 1 ? 1 : 0; i < len; i++) {
            ans[i] = 1;
            num = start + i;
            sqrt = (int) Math.sqrt(num);
            thr = Math.min(sqrt, (num + MAX - 1) / MAX - 1);
            for (j = Math.max(thr + 1, 2); j <= sqrt; j++) {
                if (num % j == 0) {
                    ans[i] = num / j;
                    continue loop;
                }
            }
            for (j = thr; j > 1; j--) {
                if (num % j == 0) {
                    ans[i] = j;
                    break;
                }
            }
        }
        return ans;
    }
}