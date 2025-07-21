import java.util.ArrayDeque;

class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private static final int FAIL = -1;

    private static final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private static int size;
    private static int[] level;
    private static Edge[] adj;

    private static void bfs(int end) {
        int i;
        int cur;
        Edge edge;
        ArrayDeque<Integer> q;

        level = new int[size + 1];
        for (i = 1; i <= size; i++) {
            level[i] = INF;
        }
        level[end] = 0;
        q = new ArrayDeque<>();
        q.addLast(end);
        while (!q.isEmpty()) {
            cur = q.pollFirst();
            for (edge = adj[cur]; edge != null; edge = edge.next) {
                if (level[edge.to] == INF) {
                    level[edge.to] = level[cur] + 1;
                    q.addLast(edge.to);
                }
            }
        }
    }

    private static int bfs01(int k, int[] gpsLog) {
        int t;
        int to;
        int cnt;
        int node;
        int[] cur;
        boolean[][] visited;
        Edge edge;
        ArrayDeque<int[]> dq;

        visited = new boolean[size + 1][k + 1];
        dq = new ArrayDeque<>();
        dq.addLast(new int[] {gpsLog[0], 1, 0});
        for (;;) {
            cur = dq.pollFirst();
            node = cur[0];
            t = cur[1];
            cnt = cur[2];
            if (t == k) {
                return cnt;
            }
            for (edge = adj[node]; edge != null; edge = edge.next) {
                to = edge.to;
                if (level[to] < k - t && !visited[to][t + 1]) {
                    if (to == gpsLog[t]) {
                        dq.addFirst(new int[] {to, t + 1, cnt});
                    } else {
                        dq.addLast(new int[] {to, t + 1, cnt + 1});
                    }
                    visited[to][t + 1] = true;
                }
            }
        }
    }

    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        int u;
        int v;
        int i;

        size = n;
        adj = new Edge[n + 1];
        for (i = 0; i < m; i++) {
            u = edge_list[i][0];
            v = edge_list[i][1];
            adj[u] = new Edge(v, adj[u]);
            adj[v] = new Edge(u, adj[v]);
        }
        bfs(gps_log[k - 1]);
        if (level[gps_log[0]] >= k) {
            return FAIL;
        }
        return bfs01(k, gps_log);
    }
}
