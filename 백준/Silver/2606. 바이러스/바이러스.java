import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] roots;

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
        roots[u] += roots[v];
        roots[v] = u;
    }

    public static void main(String[] args) throws IOException {
        int networks;
        int computers;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        computers = Integer.parseInt(br.readLine()) + 1;
        networks = Integer.parseInt(br.readLine());
        roots = new int[computers];
        while (--computers > 0) {
            roots[computers] = -1;
        }
        while (networks-- > 0) {
            st = new StringTokenizer(br.readLine(), " ", false);
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.print(-roots[find(1)] - 1);
    }
}
