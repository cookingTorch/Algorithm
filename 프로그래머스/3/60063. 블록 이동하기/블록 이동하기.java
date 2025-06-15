import java.util.PriorityQueue;

class Solution {
    private static final int H = 0;
    private static final int V = 1;
    private static final int INF = Integer.MAX_VALUE >>> 1;
    private static final int[][] dx = {{-1, -1, 0, 0, -1, 0, 1, 0}, {0, 0, 1, 1, -1, 0, 1, 0}};
    private static final int[][] dy = {{0, 1, 0, 1, 0, 1, 0, -1}, {-1, 0, -1, 0, 0, 1, 0, -1}};
    private static final int[][] rdx = {{-1, -1, 0, 0}, {0, 0, 0, 0}};
    private static final int[][] rdy = {{0, 0, 0, 0}, {-1, 0, -1, 0}};

    private static final class Node implements Comparable<Node> {
        int x;
        int y;
        int dir;
        int time;

        Node(int dir, int x, int y, int time) {
            this.dir = dir;
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return time - o.time;
        }
    }

    private static boolean[][] rotate;
    private static boolean[][][] map;

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
        int[][][] dist;
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
        pq.add(new Node(H, 1, 1, 0));
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
            ndir = dir ^ 1;
            for (i = 0; i < 4; i++) {
                nx = x + dx[dir][i];
                ny = y + dy[dir][i];
                rx = x + rdx[dir][i];
                ry = y + rdy[dir][i];
                if (rotate[rx][ry] && !visited[ndir][nx][ny] && time < dist[ndir][nx][ny]) {
                    dist[ndir][nx][ny] = time;
                    pq.offer(new Node(ndir, nx, ny, time));
                }
            }
            for (; i < 8; i++) {
                nx = x + dx[dir][i];
                ny = y + dy[dir][i];
                if (map[dir][nx][ny] && !visited[dir][nx][ny] && time < dist[dir][nx][ny]) {
                    dist[dir][nx][ny] = time;
                    pq.offer(new Node(dir, nx, ny, time));
                }
            }
        }
        return time;
    }

    public int solution(int[][] board) {
        int i;
        int j;
        int len;
        boolean[][] isEmpty;

        len = board.length;
        isEmpty = new boolean[len + 2][len + 2];
        for (i = 0; i < len; i++) {
            for (j = 0; j < len; j++) {
                if (board[i][j] == 0) {
                    isEmpty[i + 1][j + 1] = true;
                }
            }
        }
        map = new boolean[2][len + 2][len + 2];
        rotate = new boolean[len + 2][len + 2];
        for (i = 1; i <= len; i++) {
            for (j = 1; j <= len; j++) {
                map[H][i][j] = isEmpty[i][j] && isEmpty[i][j + 1];
                map[V][i][j] = isEmpty[i][j] && isEmpty[i + 1][j];
                rotate[i][j] = map[H][i][j] && map[V][i][j] && isEmpty[i + 1][j + 1];
            }
        }
        return dijkstra(len);
    }
}
