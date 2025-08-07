class Solution {
    private static final int INF = Integer.MAX_VALUE >>> 1;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int i;
        int j;
        int len;
        int ans;
        int temp;
        int[] dp;
        int[] tmp;
        int[] prev;

        if (temperature > t2) {
            temp = t1;
            t1 = temperature - t2;
            t2 = temperature - temp;
        } else {
            t1 -= temperature;
            t2 -= temperature;
        }
        prev = new int[t2 + 1];
        dp = new int[t2 + 1];
        for (i = 1; i <= t2; i++) {
            dp[i] = INF;
        }
        for (len = onboard.length - 1; onboard[len] == 0; len--);
        len++;
        for (i = 1; i < len; i++) {
            tmp = dp;
            dp = prev;
            prev = tmp;
            dp[0] = Math.min(prev[1], prev[0]);
            j = 1;
            if (onboard[i] == 1) {
                for (j = 0; j < t1; j++) {
                    dp[j] = INF;
                }
            }
            for (; j < t2; j++) {
                dp[j] = Math.min(prev[j + 1], Math.min(prev[j] + b, prev[j - 1] + a));
            }
            dp[t2] = Math.min(prev[t2] + b, prev[t2 - 1] + a);
        }
        ans = INF;
        for (i = t1; i <= t2; i++) {
            ans = Math.min(ans, dp[i]);
        }
        return ans;
    }
}
