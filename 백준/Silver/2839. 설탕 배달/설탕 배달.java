import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, i;
		int[] dp;
		
		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1];
		dp[3] = 1;
		if (n >= 5) {
			dp[5] = 1;
		}
		for (i = 6; i <= n; i++) {
			if (dp[i - 3] > 0 && dp[i - 5] > 0) {
				dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
			} else if (dp[i - 3] > 0) {
				dp[i] = dp[i - 3] + 1;
			} else if (dp[i - 5] > 0) {
				dp[i] = dp[i - 5] + 1;
			}
		}
		if (dp[n] == 0) {
			System.out.print("-1");
		} else {
			System.out.print(dp[n]);
		}
	}
}
