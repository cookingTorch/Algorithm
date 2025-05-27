import java.util.ArrayDeque;

class Solution {
    private static final int INF = Integer.MAX_VALUE >>> 1;
    private static final int WALL = 1;
    private static final int CORNER = 600;
    private static final int STRAIGHT = 100;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public int solution(int[][] board) {
        int n;
        int i;
        int x;
        int y;
        int nx;
        int ny;
        int dir;
        int ndir;
        int cost;
        int[] cur;
        int[] init;
        int[][][] costs;
        boolean[][][] inQueue;
        ArrayDeque<int[]> q;

        n = board.length;
        costs = new int[2][n][n];
        init = costs[0][0];
        for (i = 0; i < n; i++) {
            init[i] = INF;
        }
        System.arraycopy(init, 0, costs[1][0], 0, n);
        for (i = 1; i < n; i++) {
            System.arraycopy(init, 0, costs[0][i], 0, n);
            System.arraycopy(init, 0, costs[1][i], 0, n);
        }
        q = new ArrayDeque<>();
        inQueue = new boolean[2][n][n];
        for (dir = 0; dir < 2; dir++) {
            costs[dir][0][0] = 0;
            q.addLast(new int[]{dir, 0, 0});
            inQueue[dir][0][0] = true;
        }
        while (!q.isEmpty()) {
            cur = q.pollFirst();
            dir = cur[0];
            x = cur[1];
            y = cur[2];
            inQueue[dir][x][y] = false;
            for (i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == WALL) {
                    continue;
                }
                ndir = i & 1;
                cost = dir == ndir ? STRAIGHT : CORNER;
                if (costs[dir][x][y] + cost < costs[ndir][nx][ny]) {
                    costs[ndir][nx][ny] = costs[dir][x][y] + cost;
                    if (!inQueue[ndir][nx][ny]) {
                        q.addLast(new int[]{ndir, nx, ny});
                        inQueue[ndir][nx][ny] = true;
                    }
                }
            }
        }
        return Math.min(costs[0][n - 1][n - 1], costs[1][n - 1][n - 1]);
    }
}
