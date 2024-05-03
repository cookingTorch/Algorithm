class Solution {
    private static final int MOD = 20170805;
    private static final int DOWN = 0;
    private static final int RIGHT = 1;
    
    public int solution(int m, int n, int[][] cityMap) {
        int i;
        int j;
        int[][][] dp;
        
        dp = new int[m][n][2];
        dp[0][0][DOWN] = 1;
        dp[0][0][RIGHT] = 1;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                switch(cityMap[i][j]) {
                case 0:
                    if (i > 0) {
                        dp[i][j][DOWN] += dp[i - 1][j][DOWN];
                    }
                    if (j > 0) {
                        dp[i][j][DOWN] += dp[i][j - 1][RIGHT];
                    }
                    dp[i][j][DOWN] %= MOD;
                    dp[i][j][RIGHT] = dp[i][j][DOWN];
                    break;
                case 1:
                    continue;
                case 2:
                    if (i > 0) {
                        dp[i][j][DOWN] = dp[i - 1][j][DOWN];
                    }
                    if (j > 0) {
                        dp[i][j][RIGHT] = dp[i][j - 1][RIGHT];
                    }
                }
            }
        }
        return dp[m - 1][n - 1][DOWN];
    }
}