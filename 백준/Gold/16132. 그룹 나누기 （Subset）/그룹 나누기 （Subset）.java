import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int i;
        int j;
        int sum;
        long dp[];
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sum = n * (n + 1) >> 1;
        if ((sum & 1) == 1) {
            System.out.print('0');
            return;
        }
        sum >>= 1;
        dp = new long[sum + 1];
        dp[0] = 1L;
        for (i = 1; i <= n; i++) {
            for (j = sum; j >= i; j--) {
                dp[j] += dp[j - i];
            }
        }
        System.out.print(dp[sum] >> 1);
    }
}
