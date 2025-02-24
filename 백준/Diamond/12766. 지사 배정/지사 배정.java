import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = Integer.MAX_VALUE >> 1;
    private static final long INF = Long.MAX_VALUE >> 1;
    private static final String DELIM = " ";

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

    private static int n;
    private static long[] distPrefix;
    private static long[][] dp;

    private static void dijkstra(int hq, Edge[] adj, int[] dist) {
        int i;
        int to;
        int curr;
        Edge edge;
        PriorityQueue<Integer> pq;

        pq = new PriorityQueue<>(Comparator.comparingInt(o -> dist[o]));
        for (i = 0; i <= n; i++) {
            dist[i] = MAX;
        }
        dist[hq] = 0;
        pq.offer(hq);
        while (!pq.isEmpty()) {
            curr = pq.poll();
            for (edge = adj[curr]; edge != null; edge = edge.next) {
                to = edge.to;
                if (dist[curr] + edge.weight < dist[to]) {
                    dist[to] = dist[curr] + edge.weight;
                    pq.offer(to);
                }
            }
        }
    }

    private static void dncOpt(int idx, int start, int end, int left, int right) {
        int i;
        int mid;
        int opt;
        int thr;
        long val;

        if (start > end) {
            return;
        }
        mid = start + end >> 1;
        dp[idx][mid] = INF;
        opt = left;
        thr = Math.min(right, mid - 1);
        for (i = left; i <= thr; i++) {
            val = dp[idx - 1][i] + (mid - i - 1) * (distPrefix[mid] - distPrefix[i]);
            if (val < dp[idx][mid]) {
                dp[idx][mid] = val;
                opt = i;
            }
        }
        dncOpt(idx, start, mid - 1, left, opt);
        dncOpt(idx, mid + 1, end, opt, right);
    }

    public static void main(String[] args) throws IOException {
        int b;
        int s;
        int r;
        int u;
        int v;
        int weight;
        int i;
        int[] distForward;
        int[] distBackward;
        Edge[] adjForward;
        Edge[] adjBackward;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        adjForward = new Edge[n + 1];
        adjBackward = new Edge[n + 1];
        while (r-- > 0) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            adjForward[u] = new Edge(v, weight, adjForward[u]);
            adjBackward[v] = new Edge(u, weight, adjBackward[v]);
        }
        distForward = new int[n + 1];
        distBackward = new int[n + 1];
        dijkstra(b + 1, adjForward, distForward);
        dijkstra(b + 1, adjBackward, distBackward);
        distPrefix = new long[b + 1];
        for (i = 1; i <= b; i++) {
            distPrefix[i] = distForward[i] + distBackward[i];
        }
        Arrays.sort(distPrefix, 1, b + 1);
        for (i = 1; i <= b; i++) {
            distPrefix[i] = distPrefix[i - 1] + distPrefix[i];
        }
        dp = new long[s + 1][b + 1];
        for (i = 1; i <= b; i++) {
            dp[1][i] = (i - 1) * distPrefix[i];
        }
        for (i = 2; i <= s; i++) {
            dncOpt(i, i, b, 0, b);
        }
        System.out.print(dp[s][b]);
    }
}
