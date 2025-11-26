class Solution {
    private static final int MOD = 1_000_000_007;

    public int solution(int n) {
        int i;
        int[] dp;

        dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (i = 2; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
        }
        return dp[n];
    }
}
