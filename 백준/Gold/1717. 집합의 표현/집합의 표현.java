import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int UNION = '0';
    private static final String NO = "NO\n";
    private static final String YES = "YES\n";
    private static final String DELIM = " ";

    private static int[] roots;

    private static int find(int v) {
        if (roots[v] <= 0) {
            return v;
        }
        return roots[v] = find(roots[v]);
    }

    private static void union(int u, int v) {
        if ((u = find(u)) == (v = find(v))) {
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
        int n;
        int m;
        int u;
        int v;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        roots = new int[n + 2];
        sb = new StringBuilder();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            if (st.nextToken().charAt(0) == UNION) {
                u = (u = Integer.parseInt(st.nextToken())) == 0 ? n + 1 : u;
                v = (v = Integer.parseInt(st.nextToken())) == 0 ? n + 1 : v;
                union(u, v);
            } else {
                u = (u = Integer.parseInt(st.nextToken())) == 0 ? n + 1 : u;
                v = (v = Integer.parseInt(st.nextToken())) == 0 ? n + 1 : v;
                sb.append(find(u) == find(v) ? YES : NO);
            }
        }
        System.out.print(sb.toString());
    }
}
