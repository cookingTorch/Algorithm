class Solution {
    private static final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private int len;
    private int max;
    private boolean[] next;
    private boolean[] isSheep;
    private Edge[] adj;

    private void dfs(int node, int state, int cnt) {
        int i;
        Edge edge;

        if (isSheep[node]) {
            state++;
            max = Math.max(max, ++cnt);
        } else if (--state <= 0) {
            return;
        }
        for (edge = adj[node]; edge != null; edge = edge.next) {
            next[edge.to] = true;
        }
        next[node] = false;
        for (i = 0; i < len; i++) {
            if (next[i]) {
                dfs(i, state, cnt);
            }
        }
        next[node] = true;
        for (edge = adj[node]; edge != null; edge = edge.next) {
            next[edge.to] = false;
        }
    }

    public int solution(int[] info, int[][] edges) {
        int i;

        len = info.length;
        isSheep = new boolean[len];
        for (i = 0; i < len; i++) {
            isSheep[i] = info[i] == 0;
        }
        adj = new Edge[len];
        for (int[] edge : edges) {
            adj[edge[0]] = new Edge(edge[1], adj[edge[0]]);
        }
        max = 0;
        next = new boolean[len];
        next[0] = true;
        dfs(0, 0, 0);
        return max;
    }
}
