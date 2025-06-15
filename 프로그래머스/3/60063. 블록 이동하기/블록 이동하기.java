import java.util.PriorityQueue;

class Solution {
    private static final int H = 0;
    private static final int V = 1;
    private static final int INF = Integer.MAX_VALUE >>> 1;
    private static final int[][] DX = {{-1, -1, 0, 0, -1, 0, 1, 0}, {0, 0, 1, 1, -1, 0, 1, 0}};
    private static final int[][] DY = {{0, 1, 0, 1, 0, 1, 0, -1}, {-1, 0, -1, 0, 0, 1, 0, -1}};
    private static final int[][] RDX = {{-1, -1, 0, 0}, {0, 0, 0, 0}};
    private static final int[][] RDY = {{0, 0, 0, 0}, {-1, 0, -1, 0}};

    private static final class Node implements Comparable<Node> {
        int x;
        int y;
        int dir;
        int time;

        Node(int x, int y, int dir, int time) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return time - o.time;
        }
    }

    private static boolean[][] rotate;
    private static boolean[][][] empty;

    private static int dijkstra(int len) {
        int i;
        int j;
        int x;
        int y;
        int nx;
        int ny;
        int rx;
        int ry;
        int dir;
        int ndir;
        int time;
        int[] dx;
        int[] dy;
        int[] rdx;
        int[] rdy;
        int[][] ndist;
        int[][][] dist;
        boolean[][] nempty;
        boolean[][] nvisited;
        boolean[][][] visited;
        Node cur;
        PriorityQueue<Node> pq;

        time = 0;
        pq = new PriorityQueue<>();
        visited = new boolean[2][len + 2][len + 2];
        dist = new int[2][len + 2][len + 2];
        for (i = 1; i <= len; i++) {
            for (j = 1; j <= len; j++) {
                dist[H][i][j] = INF;
                dist[V][i][j] = INF;
            }
        }
        dist[H][1][1] = 0;
        pq.add(new Node(1, 1, H, 0));
        while (pq.size() != 0) {
            cur = pq.poll();
            x = cur.x;
            y = cur.y;
            dir = cur.dir;
            if (visited[dir][x][y]) {
                continue;
            }
            visited[dir][x][y] = true;
            time = cur.time;
            if ((dir == H && x == len && y == len - 1) || (dir == V && y == len && x == len - 1)) {
                break;
            }
            time++;
            dx = DX[dir];
            dy = DY[dir];
            rdx = RDX[dir];
            rdy = RDY[dir];
            ndir = dir ^ 1;
            ndist = dist[ndir];
            nvisited = visited[ndir];
            for (i = 0; i < 4; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                rx = x + rdx[i];
                ry = y + rdy[i];
                if (rotate[rx][ry] && !nvisited[nx][ny] && time < ndist[nx][ny]) {
                    ndist[nx][ny] = time;
                    pq.offer(new Node(nx, ny, ndir, time));
                }
            }
            ndist = dist[dir];
            nempty = empty[dir];
            nvisited = visited[dir];
            for (; i < 8; i++) {
                nx = x + dx[i];
                ny = y + dy[i];
                if (nempty[nx][ny] && !nvisited[nx][ny] && time < ndist[nx][ny]) {
                    ndist[nx][ny] = time;
                    pq.offer(new Node(nx, ny, dir, time));
                }
            }
        }
        return time;
    }

    public int solution(int[][] board) {
        int i;
        int j;
        int len;
        boolean[][] map;

        len = board.length;
        map = new boolean[len + 2][len + 2];
        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                if (board[i][j] == 0) {
                    map[i + 1][j + 1] = true;
                }
            }
        }
        empty = new boolean[2][len + 2][len + 2];
        rotate = new boolean[len + 2][len + 2];
        for (i = 1; i <= len; i++) {
            for (j = 1; j <= len; j++) {
                empty[H][i][j] = map[i][j] && map[i][j + 1];
                empty[V][i][j] = map[i][j] && map[i + 1][j];
                rotate[i][j] = empty[H][i][j] && empty[V][i][j] && map[i + 1][j + 1];
            }
        }
        return dijkstra(len);
    }
}
