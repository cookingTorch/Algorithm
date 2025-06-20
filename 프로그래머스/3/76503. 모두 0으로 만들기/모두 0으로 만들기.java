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
        long weight;
        Edge edge;

        weight = weights[node];
        for (edge = adj[node]; edge != null; edge = edge.next) {
            if (edge.to != parent) {
                weight += dfs(node, edge.to);
            }
        }
        ans += Math.abs(weight);
        return weight;
    }

    public long solution(int[] a, int[][] edges) {
        adj = new Edge[a.length];
        for (int[] edge : edges) {
            adj[edge[0]] = new Edge(edge[1], adj[edge[0]]);
            adj[edge[1]] = new Edge(edge[0], adj[edge[1]]);
        }
        weights = a;
        return dfs(0, 0) == 0 ? ans : FAIL;
    }
}