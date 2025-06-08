class Solution {
    private static final int MOD = 20170805;
    private static final int DOWN = 0;
    private static final int RIGHT = 1;

    public int solution(int m, int n, int[][] cityMap) {
        int i;
        int j;
        int[][] dp;

        dp = new int[n][2];
        dp[0][RIGHT] = dp[0][DOWN] = 1;
        for (i = 0; i < m; i++) {
            switch(cityMap[i][0]) {
                case 0:
                    dp[0][RIGHT] = dp[0][DOWN];
                    break;
                case 1:
                    dp[0][RIGHT] = dp[0][DOWN] = 0;
                    break;
                case 2:
                    dp[0][RIGHT] = 0;
            }
            for (j = 1; j < n; j++) {
                switch(cityMap[i][j]) {
                    case 0:
                        dp[j][RIGHT] = dp[j][DOWN] = (dp[j][DOWN] + dp[j - 1][RIGHT]) % MOD;
                        break;
                    case 1:
                        dp[j][RIGHT] = dp[j][DOWN] = 0;
                        break;
                    case 2:
                        dp[j][RIGHT] = dp[j - 1][RIGHT];
                }
            }
        }
        return dp[n - 1][DOWN];
    }
}
