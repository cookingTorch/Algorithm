class Solution {
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int r;
    private static int c;
    private static int[][] map;

    private static int dfs(int depth, int x, int y, int ox, int oy) {
        int i;
        int nx;
        int ny;
        int res;
        int cnt;
        boolean win;

        win = false;
        cnt = depth;
        for (i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == 0) {
                continue;
            }
            if (x == ox && y == oy) {
                return depth + 1 << 1 | 1;
            }
            map[x][y] = 0;
            res = dfs(depth + 1, ox, oy, nx, ny);
            map[x][y] = 1;
            if (win) {
                if ((res & 1) == 0) {
                    cnt = Math.min(cnt, res >> 1);
                }
            } else {
                if ((res & 1) == 0) {
                    win = true;
                    cnt = res >> 1;
                } else {
                    cnt = Math.max(cnt, res >> 1);
                }
            }
        }
        return win ? cnt << 1 | 1: cnt << 1;
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        map = board;
        r = board.length;
        c = board[0].length;
        return dfs(0, aloc[0], aloc[1], bloc[0], bloc[1]) >> 1;
    }
}
