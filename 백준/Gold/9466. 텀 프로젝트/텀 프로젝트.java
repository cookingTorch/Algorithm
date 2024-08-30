import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_N = 100_001;
    private static final char LINE_BREAK = '\n';

    private static int cnt;
    private static int ans;
    private static int[] selection;
    private static boolean[] visited;
    private static BufferedReader br;

    private static int dfs(int node) {
        int val;

        if (visited[node]) {
            return node;
        }
        visited[node] = true;
        val = dfs(selection[node]);
        cnt++;
        if (val == node) {
            ans -= cnt;
        }
        return val;
    }

    private static int solution() throws IOException {
        int n;
        int i;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (i = 1; i <= n; i++) {
            selection[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[n + 1];
        ans = n;
        for (i = 1; i <= n; i++) {
            cnt = 0;
            dfs(i);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        int t;
        StringBuilder sb;

        br = new BufferedReader(new InputStreamReader(System.in));
        selection = new int[MAX_N];
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            sb.append(solution()).append(LINE_BREAK);
        }
        System.out.print(sb);
    }
}
