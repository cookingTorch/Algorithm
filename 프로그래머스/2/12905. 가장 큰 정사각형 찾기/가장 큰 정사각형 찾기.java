class Solution {
    public int solution(int[][] board) {
        int r;
        int c;
        int i;
        int j;
        int max;

        r = board.length;
        c = board[0].length;
        max = 0;
        for (i = 1; i < r; i++) {
            for (j = 1; j < c; j++) {
                if (board[i][j] == 1) {
                    max = Math.max(max, board[i][j] = Math.min(Math.min(board[i - 1][j], board[i][j - 1]), board[i - 1][j - 1]) + 1);
                }
            }
        }
        if (max == 0) {
            for (i = 0; i < c; i++) {
                if (board[0][i] == 1) {
                    max = 1;
                    break;
                }
            }
        }
        if (max == 0) {
            for (i = 1; i < r; i++) {
                if (board[i][0] == 1) {
                    max = 1;
                    break;
                }
            }
        }
        return max * max;
    }
}
