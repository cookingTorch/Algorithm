class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int i;
        int j;
        int ni;
        int nj;
        int len;
        int maxAlp;
        int maxCop;
        int[][] dp;

        len = problems.length;
        maxAlp = 0;
        maxCop = 0;
        for (i = 0; i < len; i++) {
            maxAlp = Math.max(maxAlp, problems[i][0]);
            maxCop = Math.max(maxCop, problems[i][1]);
        }
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        dp = new int[maxAlp + 1][maxCop + 1];
        for (i = alp; i <= maxAlp; i++) {
            for (j = cop; j <= maxCop; j++) {
                dp[i][j] = i - alp + j - cop;
            }
        }
        for (i = alp; i <= maxAlp; i++) {
            for (j = cop; j <= maxCop; j++) {
                for (int[] problem : problems) {
                    if (i >= problem[0] && j >= problem[1]) {
                        ni = Math.min(maxAlp, i + problem[2]);
                        nj = Math.min(maxCop, j + problem[3]);
                        dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}
