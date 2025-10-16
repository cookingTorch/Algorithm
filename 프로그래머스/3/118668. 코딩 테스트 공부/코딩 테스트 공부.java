class Solution {
	private static final int ALP_REQ = 0;
	private static final int COP_REQ = 1;
	private static final int ALP_RWD = 2;
	private static final int COP_RWD = 3;
	private static final int COST = 4;

	public int solution(int alp, int cop, int[][] problems) {
		int i;
		int j;
		int k;
		int ni;
		int nj;
		int len;
		int maxAlp;
		int maxCop;
		int[] problem;
		int[][] dp;

		len = problems.length;
		maxAlp = 0;
		maxCop = 0;
		for (i = 0; i < len; i++) {
			problem = problems[i];
			problem[ALP_REQ] = Math.max(0, problem[ALP_REQ] - alp);
			problem[COP_REQ] = Math.max(0, problem[COP_REQ] - cop);
			maxAlp = Math.max(maxAlp, problem[ALP_REQ]);
			maxCop = Math.max(maxCop, problem[COP_REQ]);
		}
		dp = new int[maxAlp + 1][maxCop + 1];
		for (i = 0; i <= maxAlp; i++) {
			for (j = 0; j <= maxCop; j++) {
				dp[i][j] = i + j;
			}
		}
		for (i = 0; i <= maxAlp; i++) {
			for (j = 0; j <= maxCop; j++) {
				for (k = 0; k < len; k++) {
					problem = problems[k];
					if (i >= problem[ALP_REQ] && j >= problem[COP_REQ]) {
						ni = Math.min(maxAlp, i + problem[ALP_RWD]);
						nj = Math.min(maxCop, j + problem[COP_RWD]);
						dp[ni][nj] = Math.min(dp[ni][nj], dp[i][j] + problem[COST]);
					}
				}
			}
		}
		return dp[maxAlp][maxCop];
	}
}
