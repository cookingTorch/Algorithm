import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final char LINE_BREAK = '\n';
    private static final String DELIM = " ";

    private static final class Edge implements Comparable<Edge> {
        int u;
        int v;
        int weight;

        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
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

        Pbs(int ans) {
            this.ans = ans;
        }

        Pbs(int u, int v, Pbs next) {
            this.u = u;
            this.v = v;
            left = 1;
            right = edgeCnt + 1;
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
                ans = edges[right].weight;
                cnt++;
                return;
            }
            mid = left + right >>> 1;
            next = arr[mid];
            arr[mid] = this;
        }
    }

    private static int cnt;
    private static int size;
    private static int edgeCnt;
    private static int[] roots;
    private static int[] initRoots;
    private static Pbs[] arr;
    private static Edge[] edges;

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
        int n;
        int q;
        int u;
        int v;
        int i;
        int j;
        int idx;
        int mid;
        int[] map;
        Pbs[] ans;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        size = m * n;
        map = new int[size + 1];
        edgeCnt = m * (n - 1) + n * (m - 1);
        edges = new Edge[edgeCnt + 1];
        for (i = 0; i < size; i += n) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            for (j = 1; j <= n; j++) {
                map[i + j] = Integer.parseInt(st.nextToken());
            }
        }
        idx = 1;
        for (i = 1; i <= size - n; i++) {
            edges[idx++] = new Edge(i, i + n, Math.max(map[i], map[i + n]));
        }
        for (i = 0; i < size; i += n) {
            for (j = 1; j < n; j++) {
                edges[idx++] = new Edge(i + j, i + j + 1, Math.max(map[i + j], map[i + j + 1]));
            }
        }
        Arrays.sort(edges, 1, edgeCnt + 1);
        arr = new Pbs[edgeCnt + 1];
        ans = new Pbs[q];
        cnt = 0;
        mid = edgeCnt + 2 >>> 1;
        for (i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            u = (Integer.parseInt(st.nextToken()) - 1) * n + Integer.parseInt(st.nextToken());
            v = (Integer.parseInt(st.nextToken()) - 1) * n + Integer.parseInt(st.nextToken());
            if (u == v) {
                ans[i] = new Pbs(map[u]);
                cnt++;
            } else {
                arr[mid] = new Pbs(u, v, arr[mid]);
                ans[i] = arr[mid];
            }
        }
        initRoots = new int[size + 1];
        roots = new int[size + 1];
        while (cnt != q) {
            System.arraycopy(initRoots, 1, roots, 1, size);
            for (i = 1; i <= edgeCnt; i++) {
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
        System.out.print(sb.toString());
    }
}
