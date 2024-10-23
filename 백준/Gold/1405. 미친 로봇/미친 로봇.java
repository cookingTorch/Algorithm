import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final double ZERO = 0.0;
    private static final double FULL = 1.0;
    private static final double TOTAL = 100.0;

    private static int n;
    private static int[] d;
    private static double[] probability;
    private static boolean[] visited;

    private static double dfs(int pos, int depth) {
        int i;
        int npos;
        double val;

        if (depth == n) {
            return FULL;
        }
        depth++;
        val = ZERO;
        for (i = 0; i < 4; i++) {
            npos = pos + d[i];
            if (visited[npos]) {
                continue;
            }
            visited[npos] = true;
            val += probability[i] * dfs(npos, depth);
            visited[npos] = false;
        }
        return val;
    }

    public static void main(String[] args) throws IOException {
        int col;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        probability = new double[] {
                Integer.parseInt(st.nextToken()) / TOTAL,
                Integer.parseInt(st.nextToken()) / TOTAL,
                Integer.parseInt(st.nextToken()) / TOTAL,
                Integer.parseInt(st.nextToken()) / TOTAL
        };
        col = (n << 1) + 1;
        d = new int[] {1, -1, col, -col};
        visited = new boolean[col * col];
        visited[n * col + n] = true;
        System.out.print(dfs(n * col + n, 0));
    }
}
