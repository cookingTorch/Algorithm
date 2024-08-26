import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE;

    private static int[] combies;
    private static int[][] dp;

    private static int getDp(int n, int s) {
        int i;

        if (dp[n][s] != INF) {
            return dp[n][s];
        }
        dp[n][s] = 0;
        for (i = 1; i <= n && combies[i] <= s; i++) {
            dp[n][s] |= getDp(n - i, s - combies[i]);
        }
        return dp[n][s];
    }

    public static void main(String[] args) throws IOException {
        int n;
        int s;
        int i;
        int j;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        if (n == 1) {
            if (s == 0) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
        combies = new int[n - 1];
        for (i = 1; i < n - 1; i++) {
            combies[i] = i * (i + 1) >> 1;
        }
        dp = new int[n + 1][s + 1];
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= s; j++) {
                dp[i][j] = INF;
            }
        }
        dp[0][0] = 1;
        System.out.print(getDp(n - 2, s));
    }
}
