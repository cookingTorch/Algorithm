import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_M = 30;
    private static final char LINE_BREAK = '\n';

    private static int[][] dp;

    private static final int getDp(int m, int n) {
        if (dp[m][n] != 0) {
            return dp[m][n];
        }
        if (m == n || n == 0) {
            return dp[m][n] = 1;
        }
        return dp[m][n] = getDp(m - 1, n - 1) + getDp(m - 1, n);
    }

    public static void main(String[] args) throws IOException {
        int t;
        int n;
        int m;
        int i;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        dp = new int[MAX_M + 1][MAX_M + 1];
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine(), " ", false);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            if (n > m >> 1) {
                n = m - n;
            }
            sb.append(getDp(m, n)).append(LINE_BREAK);
        }
        System.out.println(sb.toString());
    }
}
