import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char ZERO = '0';
    private static final int MOD = 1_000_000_007;
    private static final long MOD_L = 1_000_000_007L;
    private static final long MULTIPLIER = 3L;
    private static final String DELIM = " ";

    private static int[] roots;

    private static final int find(int v) {
        if (roots[v] <= 0) {
            return v;
        }
        return roots[v] = find(roots[v]);
    }

    private static final void union(int u, int v) {
        if (u == v) {
            return;
        }
        if (roots[v] < roots[u]) {
            roots[u] = v;
        } else {
            if (roots[u] == roots[v]) {
                roots[u]--;
            }
            roots[v] = u;
        }
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int ra;
        int rb;
        int r1;
        int rn;
        int[] a;
        int[] b;
        int[] thr;
        int ans;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if (m == 0) {
            System.out.print(ZERO);
            return;
        }
        roots = new int[n + 1];
        a = new int[m];
        b = new int[m];
        thr = new int[m];
        st = new StringTokenizer(br.readLine(), DELIM, false);
        a[0] = Integer.parseInt(st.nextToken()) + 1;
        b[0] = Integer.parseInt(st.nextToken()) + 1;
        thr[0] = 1;
        for (i = 1; i < m; i++) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            a[i] = Integer.parseInt(st.nextToken()) + 1;
            b[i] = Integer.parseInt(st.nextToken()) + 1;
            thr[i] = (int) ((thr[i - 1] * MULTIPLIER) % MOD_L);
        }
        r1 = 1;
        rn = n;
        ans = 0;
        for (i = m - 1; i >= 0; i--) {
            ra = find(a[i]);
            rb = find(b[i]);
            if ((r1 == ra && rn == rb) || (r1 == rb && rn == ra)) {
                ans = (ans + thr[i]) % MOD;
            } else {
                union(ra, rb);
                r1 = find(1);
                rn = find(n);
            }
        }
        System.out.print(ans);
    }
}
