import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	private static final int MOD = 9901;

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int sum;
		int[] dp;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1];
		sum = dp[0] = 1;
		dp[1] = 2;
		for (i = 2; i <= n; i++) {
			dp[i] = (sum << 1) + dp[i - 1];
			sum = (sum + dp[i - 1]) % MOD;
		}
		System.out.print((sum + dp[n]) % MOD);
	}
}
