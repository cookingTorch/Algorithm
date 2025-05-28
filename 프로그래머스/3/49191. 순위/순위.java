class Solution {
    private static final class Edge {
        int to;
        Edge next;

        Edge(int to, Edge next) {
            this.to = to;
            this.next = next;
        }
    }

    private static final class Node {
        private long arr1;
        private long arr2;

        Node(int node) {
            if (node > 64) {
                arr2 |= (1L << node - 64);
            } else {
                arr1 |= (1L << node - 1);
            }
        }

        void merge(Node o) {
            arr1 |= o.arr1;
            arr2 |= o.arr2;
        }

        int getCnt() {
            return Long.bitCount(arr1) + Long.bitCount(arr2);
        }
    }

    Edge[] adj;
    Edge[] adjR;
    Node[] dp;
    Node[] dpR;

    private Node getDp(int node) {
        Node res;
        Edge edge;

        if (dp[node] != null) {
            return dp[node];
        }
        res = new Node(node);
        for (edge = adj[node]; edge != null; edge = edge.next) {
            res.merge(getDp(edge.to));
        }
        return dp[node] = res;
    }

    private Node getDpR(int node) {
        Node res;
        Edge edge;

        if (dpR[node] != null) {
            return dpR[node];
        }
        res = new Node(node);
        for (edge = adjR[node]; edge != null; edge = edge.next) {
            res.merge(getDpR(edge.to));
        }
        return dpR[node] = res;
    }

    public int solution(int n, int[][] results) {
        int u;
        int v;
        int i;
        int len;
        int ans;

        adj = new Edge[n + 1];
        adjR = new Edge[n + 1];
        len = results.length;
        for (i = 0; i < len; i++) {
            u = results[i][0];
            v = results[i][1];
            adj[u] = new Edge(v, adj[u]);
            adjR[v] = new Edge(u, adjR[v]);
        }
        dp = new Node[n + 1];
        dpR = new Node[n + 1];
        ans = 0;
        for (i = 1; i <= n; i++) {
            if (getDp(i).getCnt() + getDpR(i).getCnt() == n + 1) {
                ans++;
            }
        }
        return ans;
    }
}
