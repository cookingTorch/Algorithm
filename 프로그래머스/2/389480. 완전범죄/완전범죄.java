class Solution {
    public int solution(int[][] info, int n, int m) {
        int i;
        int j;
        int len;
        int sum;
        int value;
        int weight;
        int[] dp;
        
        m--;
        sum = 0;
        dp = new int[m + 1];
        len = info.length;
        for (i = 0; i < len; i++) {
            value = info[i][0];
            weight = info[i][1];
            sum += value;
            for (j = m; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }
        sum -= dp[m];
        return sum >= n ? -1 : sum;
    }
}
