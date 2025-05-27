class Solution {
    private static final int MOD = 1_000_000_007;

    public int solution(int n, int[] money) {
        int i;
        int j;
        int len;
        int cost;
        int[] dp;

        len = money.length;
        dp = new int[n + 1];
        dp[0] = 1;
        for (i = 0; i < len; i++) {
            cost = money[i];
            for (j = cost; j <= n; j++) {
                if ((dp[j] += dp[j - cost]) >= MOD) {
                    dp[j] %= MOD;
                }
            }
        }
        return dp[n];
    }
}
