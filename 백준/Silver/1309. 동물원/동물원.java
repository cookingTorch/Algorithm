import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int MOD = 9901;

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int dp;
		int sum;
		int prev;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		sum = 1;
		dp = prev = 2;
		for (i = 2; i <= n; i++) {
			dp = (sum << 1) + prev;
			sum = (sum + prev) % MOD;
			prev = dp;
		}
		System.out.print((sum + dp) % MOD);
	}
}
