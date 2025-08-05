class Solution {
    private static final long MOD = 1_000_000_007L;

    public int solution(int n) {
        int i;
        long[] dp0;
        long[] dp1;

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 3;
        }
        dp0 = new long[n + 1];
        dp1 = new long[n + 1];
        dp0[0] = 1L;
        dp0[1] = 1L;
        dp0[2] = 3L;
        dp1[2] = 2L;
        for (i = 3; i <= n; i++) {
            dp1[i] = (dp1[i - 3] + ((dp0[i - 3] + dp0[i - 2]) << 1)) % MOD;
            dp0[i] = (dp0[i - 3] + dp0[i - 1] + dp1[i - 1] + dp1[i]) % MOD;
        }
        return (int) dp0[n];
    }
}
