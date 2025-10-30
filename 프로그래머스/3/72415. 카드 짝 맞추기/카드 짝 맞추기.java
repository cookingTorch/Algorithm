import java.util.ArrayDeque;

class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private static final int SIZE = 4;
    private static final int MAX_CARD = 6;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    private static int min;
    private static int cards;
    private static int visit;
    private static int[][] dists;
    private static int[][] visited;
    private static int[][][] pos;
    private static int[][][] node;
    private static boolean[] del;
    private static boolean[][] map;
    private static ArrayDeque<int[]> q;

    private static int bfs(int x1, int y1, int x2, int y2) {
        int x;
        int y;
        int i;
        int nx;
        int ny;
        int dist;
        int[] cur;

        if (x1 == x2 && y1 == y2) {
            return 1;
        }
        visit++;
        q.clear();
        dists[x1][y1] = 0;
        q.addLast(node[x1][y1]);
        visited[x1][y1] = visit;
        while (!q.isEmpty()) {
            cur = q.pollFirst();
            x = cur[0];
            y = cur[1];
            dist = dists[x][y] + 1;
            for (i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
                    continue;
                }
                if (visited[nx][ny] != visit) {
                    if (nx == x2 && ny == y2) {
                        return dist + 1;
                    }
                    dists[nx][ny] = dist;
                    q.addLast(node[nx][ny]);
                    visited[nx][ny] = visit;
                }
                while (!map[nx][ny]) {
                    nx += dx[i];
                    ny += dy[i];
                    if (nx < 0 || nx >= SIZE || ny < 0 || ny >= SIZE) {
                        nx -= dx[i];
                        ny -= dy[i];
                        break;
                    }
                }
                if (visited[nx][ny] != visit) {
                    if (nx == x2 && ny == y2) {
                        return dist + 1;
                    }
                    dists[nx][ny] = dist;
                    q.addLast(node[nx][ny]);
                    visited[nx][ny] = visit;
                }
            }
        }
        return INF;
    }

    private static void dfs(int x, int y, int cnt, int depth) {
        int i;
        int x1;
        int y1;
        int x2;
        int y2;
        int sum;

        if (depth == cards) {
            min = Math.min(min, cnt);
            return;
        }
        for (i = 0; i < cards; i++) {
            if (del[i]) {
                continue;
            }
            del[i] = true;
            x1 = pos[i][0][0];
            y1 = pos[i][0][1];
            x2 = pos[i][1][0];
            y2 = pos[i][1][1];
            sum = cnt + bfs(x, y, x1, y1) + bfs(x1, y1, x2, y2);
            if (sum < min) {
                map[x1][y1] = false;
                map[x2][y2] = false;
                dfs(x2, y2, sum, depth + 1);
                map[x1][y1] = true;
                map[x2][y2] = true;
            }
            sum = cnt + bfs(x, y, x2, y2) + bfs(x2, y2, x1, y1);
            if (sum < min) {
                map[x1][y1] = false;
                map[x2][y2] = false;
                dfs(x1, y1, sum, depth + 1);
                map[x1][y1] = true;
                map[x2][y2] = true;
            }
            del[i] = false;
        }
    }

    public int solution(int[][] board, int r, int c) {
        int i;
        int j;
        int[] idx;

        cards = 0;
        idx = new int[MAX_CARD + 1];
        for (i = 0; i <= MAX_CARD; i++) {
            idx[i] = INF;
        }
        pos = new int[MAX_CARD][][];
        map = new boolean[SIZE][SIZE];
        node = new int[SIZE][SIZE][2];
        for (i = 0; i < SIZE; i++) {
            for (j = 0; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    if (idx[board[i][j]] == INF) {
                        idx[board[i][j]] = cards++;
                        pos[idx[board[i][j]]] = new int[][] {{i, j}, null};
                    } else {
                        pos[idx[board[i][j]]][1] = new int[] {i, j};
                    }
                    map[i][j] = true;
                }
                node[i][j][0] = i;
                node[i][j][1] = j;
            }
        }
        del = new boolean[cards];
        dists = new int[SIZE][SIZE];
        visited = new int[SIZE][SIZE];
        q = new ArrayDeque<>();
        min = INF;
        dfs(r, c, 0, 0);
        return min;
    }
}
