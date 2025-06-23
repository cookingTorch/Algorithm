class Solution {
    private static final int MOD = 10_007;

    public int solution(int n, int[] tops) {
        int i;
        int len;
        int[] dp;

        len = tops.length + 1 << 1;
        dp = new int[len];
        dp[0] = 1;
        dp[1] = 1;
        for (i = 2; i < len; i += 2) {
            if (tops[(i >>> 1) - 1] == 0) {
                dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
            } else {
                dp[i] = (dp[i - 2] + (dp[i - 1] << 1)) % MOD;
            }
            dp[i + 1] = (dp[i] + dp[i - 1]) % MOD;
        }
        return dp[len - 1];
    }
}
