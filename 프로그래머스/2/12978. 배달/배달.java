import java.util.PriorityQueue;

class Solution {
    private static final int INF = Integer.MAX_VALUE;

    private static int[] to;
    private static int[] adj;
    private static int[] next;
    private static int[] weight;

    private static int dijkstra(int n, int k) {
        int u;
        int v;
        int cnt;
        int dist;
        int edge;
        int[] cur;
        int[] dists;
        PriorityQueue<int[]> pq;

        k++;
        dists = new int[n + 1];
        for (u = 2; u <= n; u++) {
            dists[u] = k;
        }
        cnt = 0;
        pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[] {1, 0});
        while (pq.size() != 0) {
            cur = pq.poll();
            if ((dists[u = cur[0]]) < (dist = cur[1])) {
                continue;
            }
            cnt++;
            for (edge = adj[u]; edge != 0; edge = next[edge]) {
                if (dist + weight[edge] < dists[v = to[edge]]) {
                    dists[v] = dist + weight[edge];
                    pq.offer(new int[] {v, dists[v]});
                }
            }
        }
        return cnt;
    }

    public int solution(int N, int[][] road, int K) {
        int u;
        int v;
        int w;
        int i;
        int len;
        int idx;

        len = road.length;
        adj = new int[N + 1];
        to = new int[len << 1 | 1];
        next = new int[len << 1 | 1];
        weight = new int[len << 1 | 1];
        for (i = 0, idx = 0; i < len; i++) {
            u = road[i][0];
            v = road[i][1];
            w = road[i][2];
            to[++idx] = v;
            weight[idx] = w;
            next[idx] = adj[u];
            adj[u] = idx;
            to[++idx] = u;
            weight[idx] = w;
            next[idx] = adj[v];
            adj[v] = idx;
        }
        return dijkstra(N, K);
    }
}
