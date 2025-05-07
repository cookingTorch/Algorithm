import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE >>> 1;
    private static final char LINE_BREAK = '\n';
    private static final String DELIM = " ";

    private static final class Edge implements Comparable<Edge> {
        int u;
        int v;
        int weight;
        Edge next;

        Edge(int v, int weight, Edge next) {
            this.v = v;
            this.weight = weight;
            this.next = next;
        }

        Edge(int u, int v, int weight, Edge next) {
            this.u = u;
            this.v = v;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Edge o) {
            return o.weight - weight;
        }
    }

    private static final class Node implements Comparable<Node> {
        int node;
        int weight;

        Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    private static final class Pbs {
        int u;
        int v;
        int left;
        int right;
        int ans;
        Pbs next;

        Pbs(int u, int v, Pbs next) {
            this.u = u;
            this.v = v;
            left = 0;
            right = m + 1;
            this.next = next;
        }

        void validate(int mid) {
            if (find(u) == find(v)) {
                right = mid;
            } else {
                left = mid + 1;
            }
            arr[mid] = next;
            if (left >= right) {
                if (right == 0) {
                    ans = Math.min(dist[u], dist[v]);
                } else {
                    ans = edges[right].weight;
                }
                cnt++;
                return;
            }
            mid = left + right >>> 1;
            next = arr[mid];
            arr[mid] = this;
        }
    }

    private static int n;
    private static int m;
    private static int k;
    private static int q;
    private static int cnt;
    private static int[] dist;
    private static int[] roots;
    private static int[] initRoots;
    private static Pbs[] arr;
    private static Edge[] edges;
    private static BufferedReader br;

    private static final void dijkstra() throws IOException {
        int u;
        int v;
        int w;
        int i;
        int node;
        int weight;
        boolean[] visited;
        Edge edge;
        Edge[] adj;
        Node curr;
        PriorityQueue<Node> pq;
        StringTokenizer st;

        adj = new Edge[n + 1];
        for (i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            adj[u] = new Edge(u, v, w, adj[u]);
            adj[v] = new Edge(u, w, adj[v]);
            edges[i] = adj[u];
        }
        dist = new int[n + 1];
        for (i = 1; i <= n; i++) {
            dist[i] = INF;
        }
        pq = new PriorityQueue<>();
        for (i = 0; i < k; i++) {
            node = Integer.parseInt(br.readLine());
            dist[node] = 0;
            pq.offer(new Node(node, 0));
        }
        visited = new boolean[n + 1];
        while (pq.size() != 0) {
            curr = pq.poll();
            node = curr.node;
            weight = curr.weight;
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            for (edge = adj[node]; edge != null; edge = edge.next) {
                if (!visited[edge.v] && weight + edge.weight < dist[edge.v]) {
                    dist[edge.v] = weight + edge.weight;
                    pq.offer(new Node(edge.v, dist[edge.v]));
                }
            }
        }
    }

    private static final int find(int v) {
        if (roots[v] <= 0) {
            return v;
        }
        return roots[v] = find(roots[v]);
    }

    private static final void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return;
        }
        if (roots[u] > roots[v]) {
            roots[u] = v;
        } else {
            if (roots[u] == roots[v]) {
                roots[u]--;
            }
            roots[v] = u;
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        int mid;
        Pbs[] ans;
        StringBuilder sb;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        edges = new Edge[m + 1];
        dijkstra();
        ans = new Pbs[q];
        arr = new Pbs[m + 1];
        mid = m + 1 >>> 1;
        for (i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            arr[mid] = new Pbs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), arr[mid]);
            ans[i] = arr[mid];
        }
        for (i = 1; i <= m; i++) {
            edges[i].weight = Math.min(dist[edges[i].u], dist[edges[i].v]);
        }
        Arrays.sort(edges, 1, m + 1);
        edges[0] = new Edge(1, 1, 0, null);
        initRoots = new int[n + 1];
        roots = new int[n + 1];
        cnt = 0;
        while (cnt != q) {
            System.arraycopy(initRoots, 1, roots, 1, n);
            for (i = 0; i <= m; i++) {
                union(edges[i].u, edges[i].v);
                while (arr[i] != null) {
                    arr[i].validate(i);
                }
            }
        }
        sb = new StringBuilder();
        for (i = 0; i < q; i++) {
            sb.append(ans[i].ans).append(LINE_BREAK);
        }
        System.out.println(sb.toString());
    }
}
