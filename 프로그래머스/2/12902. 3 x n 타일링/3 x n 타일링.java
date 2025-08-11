class Solution {
    private static final long MOD = 1_000_000_007L;

    public int solution(int n) {
        int i;
        int half;
        long cur;
        long prev1;
        long prev2;

        if ((n & 1) == 1) {
            return 0;
        }
        half = n >>> 1;
        prev2 = 1L;
        prev1 = 3L;
        for (i = 2; i <= half; i++) {
            cur = (4L * prev1 - prev2 + MOD) % MOD;
            prev2 = prev1;
            prev1 = cur;
        }
        return (int) prev1;
    }
}
