class Solution {
    private static final int INF = Integer.MAX_VALUE;

    public int solution(int[][] matrix_sizes) {
        int l;
        int r;
        int mid;
        int len;
        int size;
        int[][] dp;

        len = matrix_sizes.length;
        dp = new int[len][len];
        for (size = 2; size <= len; size++) {
            for (r = size - 1; r < len; r++) {
                l = r - size + 1;
                dp[l][r] = INF;
                for (mid = l; mid < r; mid++) {
                    dp[l][r] = Math.min(dp[l][r], dp[l][mid] + dp[mid + 1][r] + matrix_sizes[l][0] * matrix_sizes[mid][1] * matrix_sizes[r][1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
