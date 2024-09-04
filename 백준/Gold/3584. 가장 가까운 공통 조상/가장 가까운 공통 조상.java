import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_N = 10_000;
    private static final int[] EMPTY = new int[MAX_N + 1];
    private static final char LINE_BREAK = '\n';
    private static final boolean[] FALSE = new boolean[MAX_N + 1];

    private static int[] parent;
    private static boolean[] visited;
    private static BufferedReader br;

    private static int solution() throws IOException {
        int n;
        int v;
        int i;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        System.arraycopy(EMPTY, 1, parent, 1, n);
        for (i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            parent[Integer.parseInt(st.nextToken())] = v;
        }
        System.arraycopy(FALSE, 1, visited, 1, n);
        st = new StringTokenizer(br.readLine());
        for (v = Integer.parseInt(st.nextToken()); v != 0; v = parent[v]) {
            visited[v] = true;
        }
        for (v = Integer.parseInt(st.nextToken()); !visited[v]; v = parent[v]);
        return v;
    }

    public static void main(String[] args) throws IOException {
        int t;
        StringBuilder sb;

        parent = new int[MAX_N + 1];
        visited = new boolean[MAX_N + 1];
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            sb.append(solution()).append(LINE_BREAK);
        }
        System.out.print(sb.toString());
    }
}
