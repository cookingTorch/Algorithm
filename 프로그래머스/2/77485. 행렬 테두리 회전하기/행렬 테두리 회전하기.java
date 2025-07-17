class Solution {
    private static int[][] map;

    private static int rotate(int x1, int y1, int x2, int y2) {
        int i;
        int min;
        int tmp;
        int[] row;

        min = tmp = map[x1][y1];
        for (i = x1; i < x2; i++) {
            min = Math.min(min, map[i][y1] = map[i + 1][y1]);
        }
        row = map[x2];
        System.arraycopy(row, y1 + 1, row, y1, y2 - y1);
        for (i = y1; i < y2; i++) {
            min = Math.min(min, row[i]);
        }
        for (i = x2; i > x1; i--) {
            min = Math.min(min, map[i][y2] = map[i - 1][y2]);
        }
        row = map[x1];
        System.arraycopy(row, y1, row, y1 + 1, y2 - y1);
        for (i = y2; i > y1; i--) {
            min = Math.min(min, row[i]);
        }
        map[x1][y1 + 1] = tmp;
        return min;
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int i;
        int j;
        int idx;
        int len;
        int[] q;
        int[] ans;

        idx = 1;
        map = new int[rows][columns];
        for (i = 0; i < rows; i++) {
            for (j = 0; j < columns; j++) {
                map[i][j] = idx++;
            }
        }
        len = queries.length;
        ans = new int[len];
        for (i = 0; i < len; i++) {
            q = queries[i];
            ans[i] = rotate(q[0] - 1, q[1] - 1, q[2] - 1, q[3] - 1);
        }
        return ans;
    }
}
