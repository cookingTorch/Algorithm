import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = Integer.MAX_VALUE >> 1;

    public static void main(String[] args) throws IOException {
        int c;
        int n;
        int cost;
        int customer;
        int i;
        int[] dp;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        dp = new int[c + 1];
        for (i = 1; i <= c; i++) {
            dp[i] = INF;
        }
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine(), " ", false);
            cost = Integer.parseInt(st.nextToken());
            customer = Integer.parseInt(st.nextToken());
            for (i = Math.min(customer, c); i >= 0; i--) {
                dp[i] = Math.min(dp[i], cost);
            }
            for (i = customer + 1; i <= c; i++) {
                dp[i] = Math.min(dp[i], dp[i - customer] + cost);
            }
        }
        System.out.print(dp[c]);
    }
}
