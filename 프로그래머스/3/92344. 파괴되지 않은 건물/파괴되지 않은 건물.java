class Solution {
    private static final int ATTACK = 1;

    public int solution(int[][] board, int[][] skill) {
        int n;
        int m;
        int i;
        int j;
        int ans;
        int degree;
        int[][] diff;

        n = board.length;
        m = board[0].length;
        diff = new int[n + 1][m + 1];
        for (int[] query : skill) {
            if (query[0] == ATTACK) {
                degree = query[5];
            } else {
                degree = -query[5];
            }
            diff[query[1]][query[2]] += degree;
            diff[query[1]][query[4] + 1] -= degree;
            diff[query[3] + 1][query[2]] -= degree;
            diff[query[3] + 1][query[4] + 1] += degree;
        }
        ans = n * m;
        if (diff[0][0] >= board[0][0]) {
            ans--;
        }
        for (i = 1; i < m; i++) {
            if ((diff[0][i] += diff[0][i - 1]) >= board[0][i]) {
                ans--;
            }
        }
        for (i = 1; i < n; i++) {
            if ((diff[i][0] += diff[i - 1][0]) >= board[i][0]) {
                ans--;
            }
            for (j = 1; j < m; j++) {
                if ((diff[i][j] += diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1]) >= board[i][j]) {
                    ans--;
                }
            }
        }
        return ans;
    }
}
