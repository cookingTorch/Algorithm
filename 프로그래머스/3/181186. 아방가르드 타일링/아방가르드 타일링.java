class Solution {
    private static final long MOD = 1_000_000_007L;

    public int solution(int n) {
        int i;
        long[] dp;

        dp = new long[Math.max(6, n + 1)];
        dp[0] = 1L;
        dp[1] = 1L;
        dp[2] = 3L;
        dp[3] = 10L;
        dp[4] = 23L;
        dp[5] = 62L;
        for (i = 6; i <= n; i++) {
            dp[i] = (dp[i - 1] + (dp[i - 2] << 1) + 6 * dp[i - 3] + dp[i - 4] - dp[i - 6] + MOD) % MOD;
        }
        return (int) dp[n];
    }
}
