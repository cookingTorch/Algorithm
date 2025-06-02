class Solution {
    private int cnt;
    private boolean[][] col;
    private boolean[][] beam;

    private boolean isValidCol(int x, int y) {
        return !col[x][y] || y == 0 || col[x][y - 1] || beam[x][y] || (x > 0 && beam[x - 1][y]);
    }

    private boolean isValidBeam(int x, int y) {
        return !beam[x][y] || (y > 0 && (col[x][y - 1] || col[x + 1][y - 1])) || (x > 0 && beam[x - 1][y] && beam[x + 1][y]);
    }

    private void deleteCol(int x, int y) {
        col[x][y] = false;
        if (isValidCol(x, y + 1) && isValidBeam(x, y + 1) && (x == 0 || isValidBeam(x - 1, y + 1))) {
            cnt--;
        } else {
            col[x][y] = true;
        }
    }

    private void deleteBeam(int x, int y) {
        beam[x][y] = false;
        if (isValidCol(x, y) && isValidCol(x + 1, y) && isValidBeam(x + 1, y) && (x == 0 || isValidBeam(x - 1, y))) {
            cnt--;
        } else {
            beam[x][y] = true;
        }
    }

    private void buildCol(int x, int y) {
        col[x][y] = true;
        if (isValidCol(x, y)) {
            cnt++;
        } else {
            col[x][y] = false;
        }
    }

    private void buildBeam(int x, int y) {
        beam[x][y] = true;
        if (isValidBeam(x, y)) {
            cnt++;
        } else {
            beam[x][y] = false;
        }
    }

    public int[][] solution(int n, int[][] build_frame) {
        int i;
        int j;
        int idx;
        int[][] res;

        col = new boolean[n + 2][n + 2];
        beam = new boolean[n + 2][n + 2];
        cnt = 0;
        for (int[] build : build_frame) {
            if (build[2] == 0) {
                if (build[3] == 0) {
                    deleteCol(build[0], build[1]);
                } else {
                    buildCol(build[0], build[1]);
                }
            } else {
                if (build[3] == 0) {
                    deleteBeam(build[0], build[1]);
                } else {
                    buildBeam(build[0], build[1]);
                }
            }
        }
        res = new int[cnt][];
        idx = 0;
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= n; j++) {
                if (col[i][j]) {
                    res[idx++] = new int[] {i, j, 0};
                }
                if (beam[i][j]) {
                    res[idx++] = new int[] {i, j, 1};
                }
            }
        }
        return res;
    }
}
