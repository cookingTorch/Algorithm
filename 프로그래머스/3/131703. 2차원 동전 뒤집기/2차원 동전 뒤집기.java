class Solution {
    private static final int FAIL = -1;

    public int solution(int[][] beginning, int[][] target) {
        int r;
        int c;
        int i;
        int j;
        int col;
        int row;
        int cnt;
        int mask;

        r = beginning.length;
        c = beginning[0].length;
        cnt = 0;
        col = 0;
        for (i = 0; i < c; i++) {
            if (beginning[0][i] != target[0][i]) {
                col |= 1 << i;
                cnt++;
            }
        }
        mask = (1 << c) - 1;
        for (i = 1; i < r; i++) {
            row = col;
            for (j = 0; j < c; j++) {
                if (beginning[i][j] != target[i][j]) {
                    row ^= 1 << j;
                }
            }
            if (row == mask) {
                cnt++;
            } else if (row != 0) {
                return FAIL;
            }
        }
        return Math.min(cnt, r + c - cnt);
    }
}