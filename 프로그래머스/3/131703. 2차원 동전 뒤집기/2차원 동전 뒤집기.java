class Solution {
    private static final int FAIL = -1;

    public int solution(int[][] beginning, int[][] target) {
        int r;
        int c;
        int i;
        int j;
        int cnt;
        int cur;
        int mask;
        int first;

        r = beginning.length;
        c = beginning[0].length;
        cnt = 0;
        first = 0;
        for (i = 0; i < c; i++) {
            if (beginning[0][i] != target[0][i]) {
                first |= 1 << i;
                cnt++;
            }
        }
        mask = (1 << c) - 1;
        for (i = 1; i < r; i++) {
            cur = first;
            for (j = 0; j < c; j++) {
                if (beginning[i][j] != target[i][j]) {
                    cur ^= 1 << j;
                }
            }
            if (cur == mask) {
                cnt++;
            } else if (cur != 0) {
                return FAIL;
            }
        }
        return Math.min(cnt, r + c - cnt);
    }
}
