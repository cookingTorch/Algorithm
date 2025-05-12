class Solution {
    public int solution(int[][] triangle) {
        int i;
        int j;
        int size;
        int[] dp;
        int max;

        size = triangle.length;
        dp = new int[size];
        dp[0] = triangle[0][0];
        size--;
        for (i = 0; i < size; i++) {
            for (j = i; j >= 0; j--) {
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + triangle[i + 1][j + 1]);
                dp[j] += triangle[i + 1][j];
            }
        }
        max = 0;
        for (i = 0; i <= size; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
