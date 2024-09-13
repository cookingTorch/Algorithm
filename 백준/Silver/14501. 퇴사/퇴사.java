import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int t;
        int i;
        int[] dp;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 2];
        for (i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i]);
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            if (i + t <= n + 1) {
                dp[i + t] = Math.max(dp[i + t], dp[i] + Integer.parseInt(st.nextToken()));
            }
        }
        System.out.print(Math.max(dp[n], dp[n + 1]));
    }
}
