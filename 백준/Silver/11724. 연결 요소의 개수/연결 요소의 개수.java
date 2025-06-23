import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final String DELIM = " ";

    private static int[] roots;

    private static int find(int v) {
        if (roots[v] <= 0) {
            return v;
        }
        return roots[v] = find(roots[v]);
    }

    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) {
            return false;
        }
        if (roots[u] > roots[v]) {
            roots[u] = v;
        } else {
            if (roots[u] == roots[v]) {
                roots[u]--;
            }
            roots[v] = u;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), DELIM, false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        roots = new int[n + 1];
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine(), DELIM, false);
            if (union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
                n--;
            }
        }
        System.out.print(n);
    }
}
