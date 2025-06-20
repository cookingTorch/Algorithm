class Solution {
    private static final long FAIL = -1L;

    private static final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private static int[] weights;
    private static long ans;
    private static Edge[] adj;

    private static long dfs(int parent, int node) {
        long child;
        long weight;
        Edge edge;

        weight = weights[node];
        for (edge = adj[node]; edge != null; edge = edge.next) {
            if (edge.to == parent) {
                continue;
            }
            child = dfs(node, edge.to);
            weight += child;
            ans += Math.abs(child);
        }
        return weight;
    }

    public long solution(int[] a, int[][] edges) {
        int u;
        int v;

        adj = new Edge[a.length];
        for (int[] edge : edges) {
            u = edge[0];
            v = edge[1];
            adj[u] = new Edge(v, adj[u]);
            adj[v] = new Edge(u, adj[v]);
        }
        weights = a;
        return dfs(0, 0) == 0 ? ans : FAIL;
    }
}
