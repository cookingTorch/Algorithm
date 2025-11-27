class Solution {
    private static final int EMPTY = 0;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int r;
    private static int c;
    private static int size;
    private static int color;
    private static int[][] map;

    private static void dfs(int x, int y) {
        int i;
        int nx;
        int ny;

        size++;
        map[x][y] = EMPTY;
        for (i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] != color) {
                continue;
            }
            dfs(nx, ny);
        }
    }

    public int[] solution(int m, int n, int[][] picture) {
        int i;
        int j;
        int cnt;
        int max;

        r = m;
        c = n;
        map = picture;
        cnt = 0;
        max = 0;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (picture[i][j] != EMPTY) {
                    color = picture[i][j];
                    size = 0;
                    dfs(i, j);
                    cnt++;
                    max = Math.max(max, size);
                }
            }
        }
        return new int[] {cnt, max};
    }
}
