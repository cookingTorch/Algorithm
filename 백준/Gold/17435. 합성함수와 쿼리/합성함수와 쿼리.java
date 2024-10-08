import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int LOG = 19;
    private static final char LINE_BREAK = '\n';

    public static void main(String[] args) throws IOException {
        int m;
        int q;
        int n;
        int x;
        int i;
        int j;
        int[] curr;
        int[] prev;
        int[][] dp;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        m = Integer.parseInt(br.readLine());
        dp = new int[LOG][m + 1];
        st = new StringTokenizer(br.readLine());
        prev = dp[0];
        for(i = 1; i <= m; i++) {
            prev[i] = Integer.parseInt(st.nextToken());
        }
        for (i = 1; i < LOG; i++) {
            curr = dp[i];
            for (j = 1; j <= m; j++) {
                curr[j] = prev[prev[j]];
            }
            prev = curr;
        }
        sb = new StringBuilder();
        q = Integer.parseInt(br.readLine());
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            for (i = 0; n != 0; n >>= 1, i++) {
                if ((n & 1) != 0) {
                    x = dp[i][x];
                }
            }
            sb.append(x).append(LINE_BREAK);
        }
        System.out.print(sb);
    }
}
