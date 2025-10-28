class Solution {
    private static final int U = 'U';
    private static final int R = 'R';
    private static final int D = 'D';
    private static final int OFFSET = 5;
    private static final int SIZE = OFFSET << 1 | 1;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public int solution(String dirs) {
        int x;
        int y;
        int i;
        int nx;
        int ny;
        int len;
        int dir;
        int cnt;
        boolean[][][] visited;

        cnt = 0;
        x = OFFSET;
        y = OFFSET;
        visited = new boolean[SIZE][SIZE][2];
        len = dirs.length();
        for (i = 0; i < len; i++) {
            dir = switch (dirs.charAt(i)) {
                case U -> 0;
                case R -> 1;
                case D -> 2;
                default -> 3;
            };
            nx = x + dx[dir];
            ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= SIZE || ny >= SIZE) {
                continue;
            }
            if (dir < 2) {
                if (!visited[x][y][dir]) {
                    visited[x][y][dir] = true;
                    cnt++;
                }
            } else {
                if (!visited[nx][ny][dir + 2 & 3]) {
                    visited[nx][ny][dir + 2 & 3] = true;
                    cnt++;
                }
            }
            x = nx;
            y = ny;
        }
        return cnt;
    }
}
