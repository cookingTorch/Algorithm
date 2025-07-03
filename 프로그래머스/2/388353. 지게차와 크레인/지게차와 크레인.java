import java.util.ArrayDeque;

class Solution {
    private static final int ROW = 6;
    private static final int COL = (1 << ROW) - 1;
    private static final int DIFF = 'A' - 1;
    private static final int ALPHA = 26;
    private static final int EMPTY = 0;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int cnt;
    private static int[][] map;
    private static boolean[][] isOuter;
    private static ArrayDeque<Integer> q;
    private static ArrayDeque<Integer>[] total;
    private static ArrayDeque<Integer>[] outer;

    private static void bfs() {
        int x;
        int y;
        int i;
        int nx;
        int ny;
        int cur;

        while (!q.isEmpty()) {
            cur = q.pollFirst();
            x = cur >>> ROW;
            y = cur & COL;
            for (i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (!isOuter[nx][ny]) {
                    if (map[nx][ny] == EMPTY) {
                        q.addLast(nx << ROW | ny);
                    } else {
                        outer[map[nx][ny]].addLast(nx << ROW | ny);
                    }
                    isOuter[nx][ny] = true;
                }
            }
        }
    }

    private static void lift(int val) {
        int x;
        int y;
        int cur;
        ArrayDeque<Integer> pos;

        pos = outer[val];
        while (!pos.isEmpty()) {
            cur = pos.pollFirst();
            x = cur >>> ROW;
            y = cur & COL;
            if (map[x][y] != EMPTY) {
                map[x][y] = EMPTY;
                cnt--;
                q.addLast(cur);
            }
        }
    }

    private static void crane(int val) {
        int x;
        int y;
        int cur;
        ArrayDeque<Integer> pos;

        pos = total[val];
        while (!pos.isEmpty()) {
            cur = pos.pollFirst();
            x = cur >>> ROW;
            y = cur & COL;
            if (map[x][y] != EMPTY) {
                map[x][y] = EMPTY;
                cnt--;
                if (isOuter[x][y]) {
                    q.addLast(cur);
                }
            }
        }
    }

    public int solution(String[] storage, String[] requests) {
        int n;
        int m;
        int i;
        int j;
        int val;

        total = new ArrayDeque[ALPHA + 1];
        outer = new ArrayDeque[ALPHA + 1];
        for (i = 1; i <= ALPHA; i++) {
            total[i] = new ArrayDeque<>();
            outer[i] = new ArrayDeque<>();
        }
        n = storage.length + 4;
        m = storage[0].length() + 4;
        map = new int[n][m];
        isOuter = new boolean[n][m];
        for (i = 2; i < n - 2; i++) {
            for (j = 2; j < m - 2; j++) {
                val = storage[i - 2].charAt(j - 2) - DIFF;
                map[i][j] = val;
                total[val].addLast(i << ROW | j);
            }
        }
        for (i = 0; i < m; i++) {
            isOuter[0][i] = true;
        }
        System.arraycopy(isOuter[0], 0, isOuter[n - 1], 0, m);
        for (i = 0; i < n; i++) {
            isOuter[i][0] = true;
            isOuter[i][m - 1] = true;
        }
        cnt = (n - 4) * (m - 4);
        q = new ArrayDeque<>();
        q.addLast(1 << ROW | 1);
        isOuter[1][1] = true;
        for (String request : requests) {
            val = request.charAt(0) - DIFF;
            if (request.length() == 1) {
                bfs();
                lift(val);
            } else {
                bfs();
                crane(val);
            }
        }
        return cnt;
    }
}
