import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_N = 100;
    private static final int INF = Integer.MAX_VALUE >> 1;
    private static final char LINE_BREAK = '\n';

    private static int[] infs;
    private static int[] friends;
    private static int[][] dist;
    private static BufferedReader br;

    private static int solution() throws IOException {
        int n;
        int m;
        int u;
        int v;
        int k;
        int weight;
        int sum;
        int min;
        int ans;
        int i;
        int j;
        int[] distI;
        int[] distU;
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (i = 1; i <= n; i++) {
            System.arraycopy(infs, 1, dist[i], 1, n);
        }
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            dist[u][v] = weight;
            dist[v][u] = weight;
        }
        for (i = 1; i <= n; i++) {
            dist[i][i] = 0;
        }
        for (i = 1; i <= n; i++) {
            distI = dist[i];
            for (u = 1; u <= n; u++) {
                distU = dist[u];
                for (v = 1; v <= n; v++) {
                    distU[v] = Math.min(distU[v], distU[i] + distI[v]);
                }
            }
        }
        k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < k; i++) {
            friends[i] = Integer.parseInt(st.nextToken());
        }
        ans = 0;
        min = INF;
        for (i = 1; i <= n; i++) {
            distI = dist[i];
            sum = 0;
            for (j = 0; j < k; j++) {
                sum += distI[friends[j]];
            }
            if (sum < min) {
                min = sum;
                ans = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        int t;
        int i;
        StringBuilder sb;

        infs = new int[MAX_N + 1];
        for (i = 1; i <= MAX_N; i++) {
            infs[i] = INF;
        }
        dist = new int[MAX_N + 1][MAX_N + 1];
        friends = new int[MAX_N];
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            sb.append(solution()).append(LINE_BREAK);
        }
        System.out.print(sb);
    }
}
