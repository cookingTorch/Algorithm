class Solution {
    private static final int INF = Integer.MAX_VALUE >>> 1;
    private static final int SHIFT = 11;
    private static final int SINGLE = 1 << SHIFT;
    private static final int MULTI = SINGLE | 1;

    public int[] solution(int target) {
        int i;
        int j;
        int[] dp;

        dp = new int[target + 1];
        for (i = 1; i <= target; i++) {
            dp[i] = INF;
        }
        for (i = 1; i <= 20; i++) {
            for (j = i; j <= target; j++) {
                dp[j] = Math.min(dp[j], dp[j - i] + SINGLE);
            }
        }
        for (j = 50; j <= target; j++) {
            dp[j] = Math.min(dp[j], dp[j - 50] + SINGLE);
        }
        for (i = 21; i <= 40; i++) {
            if ((i & 1) == 0 || i % 3 == 0) {
                for (j = i; j <= target; j++) {
                    dp[j] = Math.min(dp[j], dp[j - i] + MULTI);
                }
            }
        }
        for (i = 42; i <= 60; i += 3) {
            for (j = i; j <= target; j++) {
                dp[j] = Math.min(dp[j], dp[j - i] + MULTI);
            }
        }
        return new int[] {dp[target] >> SHIFT, (dp[target] >> SHIFT) - (SINGLE - 1 & dp[target])};
    }
}
