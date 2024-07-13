import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int[] dp0;
		int[] dp1;
		int[] dp2;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp0 = new int[n];
		dp1 = new int[n];
		dp2 = new int[n];
		dp0[0] = 1;
		for (i = 1; i < n; i++) {
			dp0[i] = (dp2[i - 1] + dp1[i - 1]) % MOD;
			dp1[i] = (dp0[i - 1] + dp2[i - 1]) % MOD;
			dp2[i] = (dp1[i - 1] + dp0[i - 1]) % MOD;
		}
		System.out.print(dp1[n - 1]);
	}
}
