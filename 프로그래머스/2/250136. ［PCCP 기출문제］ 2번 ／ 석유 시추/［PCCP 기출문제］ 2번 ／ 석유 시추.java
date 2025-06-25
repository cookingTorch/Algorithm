import java.util.ArrayDeque;
import java.util.ArrayList;

class Solution {
    private static final int SHIFT = 9;
    private static final int FULL = (1 << SHIFT) - 1;
    private static final int PETROLEUM = 1;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int n;
    private static int m;
    private static int[][] map;
    private static ArrayDeque<Integer> q;

    private static int bfs(int x, int y, int idx) {
        int i;
        int nx;
        int ny;
        int cur;
        int res;

        res = 0;
        map[x][y] = idx;
        q.addLast(x << SHIFT | y);
        while (!q.isEmpty()) {
            cur = q.pollFirst();
            x = cur >> SHIFT;
            y = cur & FULL;
            res++;
            for (i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] != PETROLEUM) {
                    continue;
                }
                map[nx][ny] = idx;
                q.addLast(nx << SHIFT | ny);
            }
        }
        return res;
    }

    public int solution(int[][] land) {
        int i;
        int j;
        int idx;
        int cnt;
        int max;
        int[] visited;
        ArrayList<Integer> petroleums;

        map = land;
        petroleums = new ArrayList<>();
        petroleums.add(0);
        petroleums.add(0);
        idx = 2;
        n = land.length;
        m = land[0].length;
        q = new ArrayDeque<>();
        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                if (land[i][j] == PETROLEUM) {
                    petroleums.add(bfs(i, j, idx++));
                }
            }
        }
        max = 0;
        visited = new int[idx];
        for (i = 0; i < m; i++) {
            cnt = 0;
            for (j = 0; j < n; j++) {
                idx = land[j][i];
                if (visited[idx] != ~i) {
                    cnt += petroleums.get(idx);
                    visited[idx] = ~i;
                }
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}