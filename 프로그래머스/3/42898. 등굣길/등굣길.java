class Solution {
    private static final int PUDDLE = 1;
    private static final int MOD = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        int i;
        int j;
        int len;
        int[][] dp;

        dp = new int[m + 1][n + 1];
        len = puddles.length;
        for (i = 0; i < len; i++) {
            dp[puddles[i][0]][puddles[i][1]] = PUDDLE;
        }
        dp[0][1] = 1;
        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
                if (dp[i][j] == PUDDLE) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
                }
            }
        }
        return dp[m][n];
    }
}
