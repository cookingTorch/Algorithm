import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_N = 100_001;
    private static final char LINE_BREAK = '\n';

    private static int[] next;
    private static BufferedReader br;

    private static int solution() throws IOException {
        int n;
        int i;
        int j;
        int end;
        int cnt;
        int ans;
        int[] prev;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (i = 1; i <= n; i++) {
            next[i] = Integer.parseInt(st.nextToken());
        }
        prev = new int[n + 1];
        ans = n;
        for (i = 1; i <= n; i++) {
            if (prev[i] != 0) {
                continue;
            }
            prev[i] = -1;
            for (j = i; prev[next[j]] == 0; prev[next[j]] = j, j = next[j]);
            end = next[j];
            for (cnt = 1; j != end && j != -1; j = prev[j]) {
                cnt++;
            }
            if (j == end) {
                ans -= cnt;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        int t;
        StringBuilder sb;

        br = new BufferedReader(new InputStreamReader(System.in));
        next = new int[MAX_N];
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            sb.append(solution()).append(LINE_BREAK);
        }
        System.out.print(sb);
    }
}
