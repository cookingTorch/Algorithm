class Solution {
    private static final int FAIL = -1;

    public int solution(int[][] info, int n, int m) {
        int i;
        int j;
        int len;
        int sum;
        int val;
        int weight;
        int[] dp;

        sum = 0;
        dp = new int[m];
        len = info.length;
        for (i = 0; i < len; i++) {
            val = info[i][0];
            weight = info[i][1];
            sum += val;
            for (j = m - 1; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + val);
            }
        }
        return sum - dp[m - 1] < n ? sum - dp[m - 1] : FAIL;
    }
}
