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
        boolean[] visited;
        Edge node;
        Edge edge;
        PriorityQueue<Edge> pq;

        pq = new PriorityQueue<>();
        visited = new boolean[n + 1];
        pq.offer(new Edge(1, 0, null));
        cnt = 0;
        while (!pq.isEmpty()) {
            node = pq.poll();
            v = node.to;
            w = node.weight;
            if (visited[v]) {
                continue;
            }
            visited[v] = true;
            cnt++;
            for (edge = adj[v]; edge != null; edge = edge.next) {
                if (visited[edge.to] || w + edge.weight > k) {
                    continue;
                }
                edge.weight += w;
                pq.offer(edge);
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
