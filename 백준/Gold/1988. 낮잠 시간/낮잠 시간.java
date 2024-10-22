import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int b;
        int i;
        int j;
        int thr;
        int val;
        int[] dp;
        int[] max;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        dp = new int[b + 1];
        max = new int[b + 1];
        br.readLine();
        for (i = 2; i <= n; i++) {
            thr = Math.min(i, b);
            val = Integer.parseInt(br.readLine());
            for (j = thr; j >= 2; j--) {
                dp[j] = Math.max(dp[j - 1] + val, max[j - 1]);
                max[j] = Math.max(max[j], dp[j]);
            }
        }
        System.out.print(max[b]);
    }
}
