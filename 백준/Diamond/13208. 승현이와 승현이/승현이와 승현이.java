import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final char LINE_FEED = '\n';
    private static final String DELIM = " ";

    private static final class Edge1 {
        int to;
        Edge1 next;

        Edge1(int to, Edge1 next) {
            this.to = to;
            this.next = next;
        }
    }

    private static final class Edge2 implements Comparable<Edge2> {
        int x;
        int y;
        int u;
        int v;
        int w;

        Edge2(int ux, int uy, int vx, int vy, int w) {
            x = vx;
            y = vy;
            u = (ux - 1) * n + uy;
            v = (vx - 1) * n + vy;
            this.w = w;
        }

        @Override
        public int compareTo(Edge2 o) {
            return w - o.w;
        }
    }

    private static final class Pbs {
        int u;
        int v;
        int left;
        int right;
        int ans;
        Pbs next;

        Pbs(int x, int y, Pbs next) {
            u = (x - 1) * n + y;
            v = (y - 1) * n + x;
            left = 0;
            right = size - 2;
            this.next = next;
        }

        final void validate(int mid) {
            arr[mid] = next;
            if (find(u) == find(v)) {
                right = mid;
            } else {
                left = mid + 1;
            }
            if (left >= right) {
                ans = edges[right].w;
                cnt++;
                return;
            }
            mid = left + right >>> 1;
            next = arr[mid];
            arr[mid] = this;
        }
    }

    private static int n;
    private static int cnt;
    private static int size;
    private static int[] c;
    private static int[] initRoots;
    private static int[] roots;
    private static Pbs[] arr;
    private static Edge1[] adj;
    private static Edge2[] edges;

    private static final void prim() {
        int x;
        int y;
        int idx;
        int cnt;
        boolean[] visited;
        Edge1 edge;
        Edge2 node;
        PriorityQueue<Edge2> pq;

        idx = 0;
        visited = new boolean[size + 1];
        pq = new PriorityQueue<>();
        for (edge = adj[1]; edge != null; edge = edge.next) {
            if (!visited[(edge.to - 1) * n + 1]) {
                pq.offer(new Edge2(1, 1, edge.to, 1, Math.max(c[1], c[edge.to]) * c[1]));
            }
        }
        for (edge = adj[1]; edge != null; edge = edge.next) {
            if (!visited[edge.to]) {
                pq.offer(new Edge2(1, 1, 1, edge.to, c[1] * Math.max(c[1], c[edge.to])));
            }
        }
        cnt = 1;
        while (cnt < size) {
            node = pq.poll();
            if (visited[node.v]) {
                continue;
            }
            visited[node.v] = true;
            edges[idx++] = node;
            cnt++;
            x = node.x;
            y = node.y;
            for (edge = adj[x]; edge != null; edge = edge.next) {
                if (!visited[(edge.to - 1) * n + y]) {
                    pq.offer(new Edge2(x, y, edge.to, y, Math.max(c[x], c[edge.to]) * c[y]));
                }
            }
            for (edge = adj[y]; edge != null; edge = edge.next) {
                if (!visited[(x - 1) * n + edge.to]) {
                    pq.offer(new Edge2(x, y, x, edge.to, c[x] * Math.max(c[y], c[edge.to])));
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
        int m;
        int q;
        int a;
        int b;
        int i;
        int mid;
        Pbs[] ans;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        c = new int[n + 1];
        st = new StringTokenizer(br.readLine(), DELIM, false);
        for (i = 1; i <= n; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }
        adj = new Edge1[n + 1];
        for (i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adj[a] = new Edge1(b, adj[a]);
            adj[b] = new Edge1(a, adj[b]);
        }
        size = n * n;
        edges = new Edge2[size - 1];
        prim();
        Arrays.sort(edges);
        q = Integer.parseInt(br.readLine());
        arr = new Pbs[size - 1];
        ans = new Pbs[q];
        mid = size - 1 >>> 1;
        for (i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            arr[mid] = new Pbs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), arr[mid]);
            ans[i] = arr[mid];
        }
        initRoots = new int[size + 1];
        roots = new int[size + 1];
        cnt = 0;
        while (cnt < q) {
            System.arraycopy(initRoots, 1, roots, 1, size);
            for (i = 0; i < size - 1; i++) {
                union(edges[i].u, edges[i].v);
                while (arr[i] != null) {
                    arr[i].validate(i);
                }
            }
        }
        sb = new StringBuilder();
        for (i = 0; i < q; i++) {
            sb.append(ans[i].ans).append(LINE_FEED);
        }
        System.out.print(sb.toString());
    }
}
