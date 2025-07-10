import java.util.PriorityQueue;

class Solution {
    private final int INF = Integer.MAX_VALUE;
    private final int DIFF = 'A';
    private final int ALPHA = 26;
    private final int SIZE = ALPHA << 1;
    private final int EMPTY = '.' - DIFF;
    private final int WALL = '*' - DIFF;
    private final String IMPOSSIBLE = "IMPOSSIBLE";

    private final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private int visited;
    private int[] degree;
    private int[][] map;
    private Edge[] adj;

    private boolean addEdge(int u, int v) {
        if (u == WALL) {
            degree[v] = INF;
            return false;
        } else if (u != EMPTY && (visited & 1 << u) == 0) {
            visited |= 1 << u;
            adj[u] = new Edge(v, adj[u]);
            degree[v]++;
        }
        return true;
    }

    private void addParent(int v, int x1, int y1, int x2, int y2) {
        int i;
        int min;
        int max;

        v <<= 1;
        if (x1 == x2) {
            degree[v] = INF;
        } else {
            visited = 1 << (v >>> 1);
            for (i = x1; i <= x2; i++) {
                if (!addEdge(map[i][y1], v)) {
                    break;
                }
            }
            min = Math.min(y1, y2);
            max = Math.max(y1, y2);
            if (i > x2) {
                for (i = min; i <= max; i++) {
                    if (!addEdge(map[x2][i], v)) {
                        break;
                    }
                }
            }
        }
        v |= 1;
        if (y1 == y2) {
            degree[v] = INF;
        } else {
            visited = 1 << (v >>> 1);
            min = Math.min(y1, y2);
            max = Math.max(y1, y2);
            for (i = min; i <= max; i++) {
                if (!addEdge(map[x1][i], v)) {
                    break;
                }
            }
            if (i > max) {
                for (i = x1; i <= x2; i++) {
                    if (!addEdge(map[i][y2], v)) {
                        break;
                    }
                }
            }
        }
    }

    private String topo(int cnt) {
        int i;
        int to;
        int cur;
        char[] res;
        boolean[] visited;
        Edge edge;
        PriorityQueue<Integer> pq;

        pq = new PriorityQueue<>();
        visited = new boolean[ALPHA];
        for (i = 0; i < SIZE; i++) {
            if (degree[i] == 0 && !visited[i >>> 1]) {
                pq.offer(i >>> 1);
                visited[i >>> 1] = true;
            }
        }
        res = new char[cnt];
        i = 0;
        while (!pq.isEmpty()) {
            cur = pq.poll();
            res[i++] = (char) (cur + DIFF);
            for (edge = adj[cur]; edge != null; edge = edge.next) {
                to = edge.to;
                if (visited[to >>> 1]) {
                    continue;
                }
                if (--degree[to] == 0) {
                    pq.offer(to >>> 1);
                    visited[to >>> 1] = true;
                }
            }
        }
        return i == cnt ? new String(res) : IMPOSSIBLE;
    }

    public String solution(int m, int n, String[] board) {
        int i;
        int j;
        int cnt;
        int tile;
        int[] xy;
        int[][] pos;

        cnt = 0;
        map = new int[m][n];
        pos = new int[ALPHA][];
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                map[i][j] = tile = board[i].charAt(j) - DIFF;
                if (tile == EMPTY || tile == WALL) {
                    continue;
                }
                if (pos[tile] == null) {
                    pos[tile] = new int[] {i, j, 0, 0};
                    cnt++;
                } else {
                    pos[tile][2] = i;
                    pos[tile][3] = j;
                }
            }
        }
        adj = new Edge[ALPHA];
        degree = new int[SIZE];
        for (i = 0; i < ALPHA; i++) {
            if ((xy = pos[i]) == null) {
                degree[i << 1] = INF;
                degree[i << 1 | 1] = INF;
            } else {
                addParent(i, xy[0], xy[1], xy[2], xy[3]);
            }
        }
        return topo(cnt);
    }
}
