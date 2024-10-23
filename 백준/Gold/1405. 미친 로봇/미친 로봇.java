import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final double TOTAL = 100.0;

    private static int n;
    private static int col;
    private static double ans;
    private static double east;
    private static double west;
    private static double south;
    private static double north;
    private static boolean[] visited;

    private static void dfs(int pos, double probability, int depth) {
        if (visited[pos]) {
            return;
        }
        if (depth == n) {
            ans += probability;
            return;
        }
        depth++;
        visited[pos] = true;
        dfs(pos + 1, probability * east, depth);
        dfs(pos - 1, probability * west, depth);
        dfs(pos + col, probability * south, depth);
        dfs(pos - col, probability * north, depth);
        visited[pos] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        east = Integer.parseInt(st.nextToken()) / TOTAL;
        west = Integer.parseInt(st.nextToken()) / TOTAL;
        south = Integer.parseInt(st.nextToken()) / TOTAL;
        north = Integer.parseInt(st.nextToken()) / TOTAL;
        col = (n << 1) + 1;
        visited = new boolean[col * col];
        dfs(n * col + n, 1.0, 0);
        System.out.print(ans);
    }
}
