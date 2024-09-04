import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_N = 10_000;
    private static final int TWO = 2;
    private static final int LOG = (int) (Math.log(MAX_N) / Math.log(TWO));
    private static final int[] EMPTY = new int[MAX_N + 1];
    private static final char LINE_BREAK = '\n';

    private static int log;
    private static int[] depth;
    private static int[] parent;
    private static int[][] dp;
    private static BufferedReader br;
    private static ArrayList<Integer>[] adj;

    private static void dfs(int node, int level) {
        depth[node] = level;
        for (int child : adj[node]) {
            dfs(child, level + 1);
        }
    }

    private static int lca(int u, int v) {
        int i;
        int temp;
        int diff;

        if (depth[u] < depth[v]) {
            temp = u;
            u = v;
            v = temp;
        }
        for (diff = depth[u] - depth[v], i = 0; diff != 0; diff >>= 1, i++) {
            if ((diff & 1) == 1) {
                u = dp[i][u];
            }
        }
        if (u == v) {
            return u;
        }
        for (i = log; i >= 0; i--) {
            if (dp[i][u] != dp[i][v]) {
                u = dp[i][u];
                v = dp[i][v];
            }
        }
        return parent[u];
    }

    private static int solution() throws IOException {
        int n;
        int a;
        int b;
        int i;
        int j;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        log = (int) (Math.log(n) / Math.log(TWO));
        for (i = 1; i <= n; i++) {
            adj[i].clear();
        }
        System.arraycopy(EMPTY, 1, parent, 1, n);
        for (i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            parent[b] = a;
        }
        for (i = 1; parent[i] != 0; i++);
        dfs(i, 0);
        for (i = 1; i <= log; i++) {
            for (j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][dp[i - 1][j]];
            }
        }
        st = new StringTokenizer(br.readLine());
        return lca(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
    }

    public static void main(String[] args) throws IOException {
        int t;
        int i;
        StringBuilder sb;

        adj = new ArrayList[MAX_N + 1];
        for (i = 1; i <= MAX_N; i++) {
            adj[i] = new ArrayList<>();
        }
        dp = new int[LOG + 1][MAX_N + 1];
        parent = dp[0];
        depth = new int[MAX_N + 1];
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            sb.append(solution()).append(LINE_BREAK);
        }
        System.out.print(sb.toString());
    }
}
