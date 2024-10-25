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
        int[] prev;
        int[] curr;
        int[] invest;
        int[] profits;
        int[][] ans;
        int[][] companies;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        companies = new int[m][n + 1];
        curr = new int[n + 1];
        ans = new int[m][n + 1];
        invest = ans[0];
        for (i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            st.nextToken();
            for (j = m - 1; j > 0; j--) {
                companies[j][i] = Integer.parseInt(st.nextToken());
            }
            curr[i] = Integer.parseInt(st.nextToken());
            invest[i] = i;
        }
        prev = new int[n + 1];
        for (i = 1; i < m; i++) {
            System.arraycopy(curr, 1, prev, 1, n);
            profits = companies[i];
            invest = ans[i];
            for (j = 1; j <= n; j++) {
                profit = profits[j];
                for (k = j; k <= n; k++) {
                    val = prev[k - j] + profit;
                    if (val > curr[k]) {
                        invest[k] = j;
                        curr[k] = val;
                    }
                }
            }
        }
        sb = new StringBuilder();
        sb.append(curr[n]).append(LINE_BREAK);
        j = n;
        for (i = m - 1; i >= 0; i--) {
            sb.append(ans[i][j]).append(SPACE);
            j -= ans[i][j];
        }
        System.out.print(sb.toString());
    }
}
