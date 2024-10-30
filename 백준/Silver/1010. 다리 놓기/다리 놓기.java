import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_M = 30;
    private static final char LINE_BREAK = '\n';

    private static final int combi(int n, int m, int[] dp) {
        int i;
        int j;

        if (n > m >> 1) {
            n = m - n;
        }
        dp[0] = 1;
        dp[1] = 1;
        for (i = 2; i <= m; i++) {
            if (i <= n) {
                dp[i] = 1;
            }
            for (j = Math.min(n, i - 1); j > 1; j--) {
                dp[j] = dp[j - 1] + dp[j];
            }
            dp[1] = i;
        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        int t;
        int[] dp;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        dp = new int[MAX_M + 1];
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine(), " ", false);
            sb.append(combi(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), dp)).append(LINE_BREAK);
        }
        System.out.println(sb.toString());
    }
}
