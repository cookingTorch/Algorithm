class Solution {
    private static final int FAIL = -1;

    public int solution(int[][] beginning, int[][] target) {
        int r;
        int c;
        int i;
        int j;
        int res;
        int cnt;
        int mask;
        int first;
        int[] src;

        r = beginning.length;
        c = beginning[0].length;
        src = new int[r];
        cnt = 0;
        first = 0;
        for (i = 0; i < c; i++) {
            if (beginning[0][i] != target[0][i]) {
                first |= 1 << i;
                cnt++;
            }
        }
        for (i = 1; i < r; i++) {
            for (j = 0; j < c; j++) {
                if (beginning[i][j] != target[i][j]) {
                    src[i] |= 1 << j;
                }
            }
        }
        mask = (1 << c) - 1;
        for (i = 1; i < r; i++) {
            res = src[i] ^ first;
            if (res == mask) {
                cnt++;
            } else if (res != 0) {
                return FAIL;
            }
        }
        return Math.min(cnt, r + c - cnt);
    }
}
