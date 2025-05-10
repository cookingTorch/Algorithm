import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char FLOOR = '.';
    private static final char LINE_FEED = '\n';
    private static final String DELIM = " ";

    private static final class Edge {
        int u;
        int v;
        Edge next;

        Edge(int u, int v, Edge next) {
            this.u = u;
            this.v = v;
            this.next = next;
        }
    }

    private static final class Pbs {
        int ux;
        int uy;
        int vx;
        int vy;
        int left;
        int right;
        int ans;
        Pbs next;

        Pbs(int ux, int uy, int vx, int vy, Pbs next) {
            this.ux = ux;
            this.uy = uy;
            this.vx = vx;
            this.vy = vy;
            left = 0;
            right = size;
            this.next = next;
        }

        final void validate(int mid) {
            int dux;
            int duy;
            int dvx;
            int dvy;

            arr[mid] = next;
            if ((dux = ux + mid) <= n && (duy = uy + mid) <= n && (dvx = vx + mid) <= n && (dvy = vy + mid) <= n && find((dux - 1) * n + duy) == find((dvx - 1) * n + dvy)) {
                left = mid + 1;
            } else {
                right = mid;
            }
            if (left >= right) {
                if (right == 0) {
                    ans = 0;
                } else {
                    ans = (right << 1) - 1;
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
    private static int cnt;
    private static int size;
    private static int[] initRoots;
    private static int[] roots;
    private static int[][] dp;
    private static Pbs[] arr;

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
        int q;
        int i;
        int j;
        int mid;
        int min;
        int max;
        char[][] map;
        Pbs[] ans;
        Edge edge;
        Edge[] edges;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n + 1][n + 1];
        for (i = 1; i <= n; i++) {
            br.read(map[i], 1, n);
            br.read();
        }
        dp = new int[n + 1][n + 1];
        for (i = 1; i <= n; i++) {
            for (j = 1; j <= n; j++) {
                if (map[i][j] == FLOOR) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        if ((n & 1) == 0) {
            max = n - 1;
        } else {
            max = n;
        }
        size = (max >>> 1) + 1;
        edges = new Edge[size];
        for (i = 1; i <= n; i++) {
            for (j = 1; j < n; j++) {
                min = Math.min(dp[i][j], dp[i][j + 1]);
                if (min == 0) {
                    continue;
                }
                min = min - 1 >>> 1;
                edges[min] = new Edge((i - 1) * n + j, (i - 1) * n + j + 1, edges[min]);
            }
        }
        for (i = 1; i < n; i++) {
            for (j = 1; j <= n; j++) {
                min = Math.min(dp[i][j], dp[i + 1][j]);
                if (min == 0) {
                    continue;
                }
                min = min - 1 >>> 1;
                edges[min] = new Edge((i - 1) * n + j, i * n + j, edges[min]);
            }
        }
        q = Integer.parseInt(br.readLine());
        arr = new Pbs[size];
        ans = new Pbs[q];
        mid = size >>> 1;
        for (i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            arr[mid] = new Pbs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), arr[mid]);
            ans[i] = arr[mid];
        }
        initRoots = new int[n * n + 1];
        roots = new int[n * n + 1];
        cnt = 0;
        while (cnt != q) {
            System.arraycopy(initRoots, 1, roots, 1, n * n);
            for (i = size - 1; i >= 0; i--) {
                for (edge = edges[i]; edge != null; edge = edge.next) {
                    union(edge.u, edge.v);
                }
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
