class Solution {
    private static int num;
    private static int min;
    private static int[] to;
	private static int[] adj;
    private static int[] next;

    private static int dfs(int parent, int node) {
        int cnt;
        int edge;

        cnt = 1;
        for (edge = adj[node]; edge != 0; edge = next[edge]) {
            if (to[edge] != parent) {
                cnt += dfs(node, to[edge]);
            }
        }
        min = Math.min(min, Math.abs(num - (cnt << 1)));
        return cnt;
    }

    public int solution(int n, int[][] wires) {
        int u;
        int v;
        int i;
        int len;
        int idx;

        adj = new int[n + 1];
        len = n - 1;
        to = new int[len << 1 | 1];
        next = new int[len << 1 | 1];
        for (i = 0, idx = 0; i < len; i++) {
            u = wires[i][0];
            v = wires[i][1];
            to[++idx] = v;
            next[idx] = adj[u];
            adj[u] = idx;
            to[++idx] = u;
            next[idx] = adj[v];
            adj[v] = idx;
        }
        num = n;
        min = n;
        dfs(0, 1);
        return min;
    }
}
