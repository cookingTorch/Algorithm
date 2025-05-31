import java.util.ArrayDeque;

class Solution {
    private static final int INF = Integer.MAX_VALUE / 3;

    private static final class Edge {
        int to;
        int weight;
        Edge next;

        Edge(int to, int weight, Edge next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
    }

    private int[] spfa(int n, int start, Edge[] adj, ArrayDeque<Integer> q, boolean[] inQueue) {
        int i;
        int cur;
        int[] dist;
        Edge edge;

        dist = new int[n + 1];
        for (i = 1; i <= n; i++) {
            dist[i] = INF;
        }
        dist[start] = 0;
        q.offer(start);
        while (!q.isEmpty()) {
            cur = q.pollFirst();
            inQueue[cur] = false;
            for (edge = adj[cur]; edge != null; edge = edge.next) {
                if (dist[cur] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[cur] + edge.weight;
                    if (!inQueue[edge.to]) {
                        q.addLast(edge.to);
                        inQueue[edge.to] = true;
                    }
                }
            }
        }
        return dist;
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int u;
        int v;
        int w;
        int i;
        int min;
        int[] distS;
        int[] distA;
        int[] distB;
        boolean[] inQueue;
        Edge[] adj;
        ArrayDeque<Integer> q;

        adj = new Edge[n + 1];
        for (int[] fare : fares) {
            u = fare[0];
            v = fare[1];
            w = fare[2];
            adj[u] = new Edge(v, w, adj[u]);
            adj[v] = new Edge(u, w, adj[v]);
        }
        q = new ArrayDeque<>();
        inQueue = new boolean[n + 1];
        distS = spfa(n, s, adj, q, inQueue);
        distA = spfa(n, a, adj, q, inQueue);
        distB = spfa(n, b, adj, q, inQueue);
        min = INF;
        for (i = 1; i <= n; i++) {
            min = Math.min(min, distS[i] + distA[i] + distB[i]);
        }
        return min;
    }
}
