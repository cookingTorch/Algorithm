class Solution {
    private static final int MAX = 10;
    private static final int FAIL = -1;

    public int[] solution(int n, int[] info) {
        int i;
        int j;
        int k;
        int sum;
        int val;
        int cost;
        int score;
        int[] dp;
        int[] tmp;
        int[][] res;

        sum = 0;
        dp = new int[n + 1];
        res = new int[n + 1][MAX + 1];
        tmp = new int[MAX + 1];
        for (i = MAX - 1; i >= 0; i--) {
            cost = info[i] + 1;
            val = 10 - i;
            if (cost != 1) {
                sum += val;
                val <<= 1;
            }
            for (j = n; j >= cost; j--) {
                score = dp[j - cost] + val;
                if (score > dp[j]) {
                    dp[j] = score;
                    System.arraycopy(res[j - cost], 0, res[j], 0, MAX);
                    res[j][i] = cost;
                } else if (score == dp[j]) {
                    System.arraycopy(res[j - cost], 0, tmp, 0, MAX);
                    tmp[i] = cost;
                    for (k = MAX; k >= 0; k--) {
                        if (tmp[k] > res[j][k]) {
                            System.arraycopy(tmp, 0, res[j], 0, MAX);
                            break;
                        } else if (tmp[k] < res[j][k]) {
                            break;
                        }
                    }
                }
            }
        }
        res[n][MAX] = n;
        for (i = 0; i < MAX; i++) {
            res[n][MAX] -= res[n][i];
        }
        return dp[n] > sum ? res[n] : new int[] {FAIL};
    }
}
