import java.util.ArrayDeque;

class Solution {
    private static final int S = 'S';
    private static final int L = 'L';
    private static final int E = 'E';
    private static final int WALL = 'X';
    private static final int FAIL = -1;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int r;
    private static int c;
    private static int ex;
    private static int ey;
    private static char[][] map;

    private static int bfs(int x, int y, int dest, ArrayDeque<int[]> q) {
        int i;
        int nx;
        int ny;
        int dist;
        int[] cur;
        boolean[][] visited;

        visited = new boolean[r][c];
        q.addLast(new int[] {x, y, 0});
        visited[x][y] = true;
        while (!q.isEmpty()) {
            cur = q.pollFirst();
            x = cur[0];
            y = cur[1];
            dist = cur[2] + 1;
            for (i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (nx < 0 || nx >= r || ny < 0 || ny >= c || visited[nx][ny] || map[nx][ny] == WALL) {
                    continue;
                }
                if (map[nx][ny] == dest) {
                    ex = nx;
                    ey = ny;
                    return dist;
                }
                q.addLast(new int[] {nx, ny, dist});
                visited[nx][ny] = true;
            }
        }
        return FAIL;
    }

    public int solution(String[] maps) {
        int i;
        int j;
        int x;
        int y;
        int dist1;
        int dist2;
        char[] row;
        ArrayDeque<int[]> q;

        x = 0;
        y = 0;
        r = maps.length;
        c = maps[0].length();
        map = new char[r][];
        for (i = 0; i < r; i++) {
            map[i] = row = maps[i].toCharArray();
            for (j = 0; j < c; j++) {
                if (row[j] == S) {
                    x = i;
                    y = j;
                }
            }
        }
        q = new ArrayDeque<>();
        if ((dist1 = bfs(x, y, L, q)) == FAIL) {
            return FAIL;
        }
        q.clear();
        if ((dist2 = bfs(ex, ey, E, q)) == FAIL) {
            return FAIL;
        }
        return dist1 + dist2;
    }
}
