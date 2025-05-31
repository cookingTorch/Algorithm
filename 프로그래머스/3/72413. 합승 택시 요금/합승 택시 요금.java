import java.util.PriorityQueue;

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

    private static final class Node implements Comparable<Node> {
        int idx;
        int dist;

        Node(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return dist - o.dist;
        }
    }

    private int[] dijkstra(int n, int start, Edge[] adj, PriorityQueue<Node> pq) {
        int i;
        int cur;
        int[] dist;
        Node node;
        Edge edge;

        dist = new int[n + 1];
        for (i = 1; i <= n; i++) {
            dist[i] = INF;
        }
        pq.offer(new Node(start, dist[start] = 0));
        while (pq.size() != 0) {
            node = pq.poll();
            cur = node.idx;
            for (edge = adj[cur]; edge != null; edge = edge.next) {
                if (dist[cur] + edge.weight < dist[edge.to]) {
                    dist[edge.to] = dist[cur] + edge.weight;
                    pq.offer(new Node(edge.to, dist[edge.to]));
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
        int ans;
        int[] distS;
        int[] distA;
        int[] distB;
        Edge[] adj;
        PriorityQueue<Node> pq;

        adj = new Edge[n + 1];
        for (int[] fare : fares) {
            u = fare[0];
            v = fare[1];
            w = fare[2];
            adj[u] = new Edge(v, w, adj[u]);
            adj[v] = new Edge(u, w, adj[v]);
        }
        pq = new PriorityQueue<>();
        distS = dijkstra(n, s, adj, pq);
        distA = dijkstra(n, a, adj, pq);
        distB = dijkstra(n, b, adj, pq);
        ans = INF;
        for (i = 1; i <= n; i++) {
            ans = Math.min(ans, distS[i] + distA[i] + distB[i]);
        }
        return ans;
    }
}
