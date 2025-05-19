class Solution {
    public int solution(int sticker[]) {
        int i;
        int len;
        int max;
        int[] dp;

        len = sticker.length;
        if (len == 1) {
            return sticker[0];
        } else if (len == 2) {
            return Math.max(sticker[0], sticker[1]);
        } else if (len == 3) {
            return Math.max(Math.max(sticker[0], sticker[1]), sticker[2]);
        }
        dp = new int[len];
        dp[0] = sticker[0];
        dp[1] = 0;
        dp[2] = dp[0] + sticker[2];
        len--;
        for (i = 3; i < len; i++) {
            dp[i] = Math.max(dp[i - 3], dp[i - 2]) + sticker[i];
        }
        len++;
        max = Math.max(dp[len - 3], dp[len - 2]);
        dp[0] = 0;
        dp[1] = sticker[1];
        dp[2] = sticker[2];
        for (i = 3; i < len; i++) {
            dp[i] = Math.max(dp[i - 3], dp[i - 2]) + sticker[i];
        }
        max = Math.max(max, Math.max(dp[len - 2], dp[len - 1]));
        return max;
    }
}
