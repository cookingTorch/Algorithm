import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int LOG = (int) (Math.log(500_000) / Math.log(2));
    private static final char LINE_BREAK = '\n';

    public static void main(String[] args) throws IOException {
        int m;
        int q;
        int n;
        int x;
        int i;
        int node;
        int child;
        int[][] dp;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine());
        dp = new int[m + 1][LOG + 1];
        st = new StringTokenizer(br.readLine());
        for(i = 1; i <= m; i++) {
            dp[i][0] = Integer.parseInt(st.nextToken());
        }
        for (i = 1; i <= LOG; i++) {
            for (node = 1; node <= m; node++) {
                child = dp[node][i - 1];
                if (child != 0) {
                    dp[node][i] = dp[child][i - 1];
                }
            }
        }
        sb = new StringBuilder();
        q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            for (i = 0; n != 0; n >>= 1, i++) {
                if ((n & 1) == 1) {
                    x = dp[x][i];
                }
            }
            sb.append(x).append(LINE_BREAK);
        }
        System.out.print(sb.toString());
    }
}
