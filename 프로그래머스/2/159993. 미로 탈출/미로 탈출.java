import java.util.ArrayDeque;

class Solution {
    private static final int S = 'S';
    private static final int L = 'L';
    private static final int E = 'E';
    private static final int WALL = 'X';
    private static final int NULL = -1;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int bfs(char[][] map, int r, int c, int x, int y) {
        int i;
        int nx;
        int ny;
        int dist;
        int size;
        int distS;
        int distE;
        int[] cur;
        ArrayDeque<int[]> q;

        dist = 0;
        distS = 0;
        distE = 0;
        q = new ArrayDeque<>();
        q.addLast(new int[] {x, y});
        while (!q.isEmpty()) {
            dist++;
            size = q.size();
            while (size-- > 0) {
                cur = q.pollFirst();
                x = cur[0];
                y = cur[1];
                for (i = 0; i < 4; i++) {
                    nx = x + dx[i];
                    ny = y + dy[i];
                    if (nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == WALL) {
                        continue;
                    }
                    if (map[nx][ny] == S) {
                        distS = dist;
                        if (distE != 0) {
                            return distS + distE;
                        }
                    }
                    if (map[nx][ny] == E) {
                        distE = dist;
                        if (distS != 0) {
                            return distS + distE;
                        }
                    }
                    q.addLast(new int[] {nx, ny});
                    map[nx][ny] = WALL;
                }
            }
        }
        return NULL;
    }

    public int solution(String[] maps) {
        int i;
        int j;
        int r;
        int c;
        int x;
        int y;
        char[] row;
        char[][] map;

        x = NULL;
        y = NULL;
        r = maps.length;
        c = maps[0].length();
        map = new char[r][];
        for (i = 0; i < r; i++) {
            map[i] = row = maps[i].toCharArray();
            if (x == NULL) {
                for (j = 0; j < c; j++) {
                    if (row[j] == L) {
                        x = i;
                        y = j;
                    }
                }
            }
        }
        return bfs(map, r, c, x, y);
    }
}
