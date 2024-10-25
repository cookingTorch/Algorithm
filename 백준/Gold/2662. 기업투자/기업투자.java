import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char SPACE = ' ';
    private static final char LINE_BREAK = '\n';

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int j;
        int k;
        int val;
        int profit;
        int[] idxs;
        int[] prev;
        int[] curr;
        int[] profits;
        int[][] map;
        int[][] ans;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[m + 1][n + 1];
        for (i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            st.nextToken();
            for (j = m; j > 0; j--) {
                map[j][i] = Integer.parseInt(st.nextToken());
            }
        }
        prev = new int[n + 1];
        curr = new int[n + 1];
        ans = new int[m + 1][n + 1];
        for (i = 1; i <= m; i++) {
            System.arraycopy(curr, 1, prev, 1, n);
            profits = map[i];
            idxs = ans[i];
            for (j = 1; j <= n; j++) {
                profit = profits[j];
                for (k = j; k <= n; k++) {
                    val = prev[k - j] + profit;
                    if (val > curr[k]) {
                        idxs[k] = j;
                        curr[k] = val;
                    }
                }
            }
        }
        sb = new StringBuilder();
        sb.append(curr[n]).append(LINE_BREAK);
        j = n;
        for (i = m; i >= 1; i--) {
            sb.append(ans[i][j]).append(SPACE);
            j -= ans[i][j];
        }
        System.out.print(sb.toString());
    }
}
