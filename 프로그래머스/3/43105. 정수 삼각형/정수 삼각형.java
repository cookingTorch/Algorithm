class Solution {
    public int solution(int[][] triangle) {
        int i;
        int size;
        int[] dp;

        size = triangle.length;
        dp = new int[size];
        System.arraycopy(triangle[size - 1], 0, dp, 0, size);
        while (--size > 0) {
            for (i = 0; i < size; i++) {
                dp[i] = Math.max(dp[i], dp[i + 1]) + triangle[size - 1][i];
            }
        }
        return dp[0];
    }
}
