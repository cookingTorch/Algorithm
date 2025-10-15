class Solution {
    private static final long MOD = 1_000_000_007L;

    public int solution(int n) {
        int i;
        int j;
        long[] dp;
        
        if ((n & 1) != 0) {
            return 0;
        }
        dp = new long[n + 1];
        dp[0] = 1L;
        dp[2] = 3L;
        for (i = 4; i <= n; i += 2) {
            dp[i] = (3L * dp[i - 2]) % MOD;
            for (j = i - 4; j >= 0; j -= 2) {
                dp[i] = (dp[i] + (dp[j] << 1)) % MOD;
            }
        }
        return (int) dp[n];
    }
}