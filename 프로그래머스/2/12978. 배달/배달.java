import java.util.PriorityQueue;

class Solution {
    private static final class Edge implements Comparable<Edge> {
        int to;
        int weight;
        Edge next;

        Edge(int to, int weight, Edge next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    private static int dijkstra(int n, int k, Edge[] adj) {
        int v;
        int w;
        int cnt;
        int[] dist;
        Edge node;
        Edge edge;
        PriorityQueue<Edge> pq;

        pq = new PriorityQueue<>();
        dist = new int[n + 1];
        k++;
        for (v = 2; v <= n; v++) {
            dist[v] = k;
        }
        pq.offer(new Edge(1, 0, null));
        cnt = 0;
        while (!pq.isEmpty()) {
            node = pq.poll();
            v = node.to;
            w = node.weight;
            if (dist[v] < w) {
                continue;
            }
            cnt++;
            for (edge = adj[v]; edge != null; edge = edge.next) {
                if (w + edge.weight < dist[edge.to]) {
                    dist[edge.to] = w + edge.weight;
                    edge.weight += w;
                    pq.offer(edge);
                }
            }
        }
        return cnt;
    }

    public int solution(int N, int[][] road, int K) {
        int u;
        int v;
        int w;
        Edge[] adj;

        adj = new Edge[N + 1];
        for (int[] edge : road) {
            u = edge[0];
            v = edge[1];
            w = edge[2];
            adj[u] = new Edge(v, w, adj[u]);
            adj[v] = new Edge(u, w, adj[v]);
        }
        return dijkstra(N, K, adj);
    }
}
