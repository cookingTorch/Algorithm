import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int HALF_L = 2_500;
	private static final int MOD = 1_000_000_007;
	private static final char ZERO = '0';
	private static final char LINE_BREAK = '\n';
	
	public static void main(String[] args) throws IOException {
		int t;
		int i;
		int j;
		int num;
		long[] dp;
		StringBuilder sb;
		BufferedReader br;
		
		dp = new long[HALF_L + 1];
		dp[0] = 1L;
		for (i = 1; i <= HALF_L; i++) {
			for (j = 0; j < i; j++) {
				dp[i] = (dp[i] + (dp[j] * dp[i - 1 - j]) % MOD) % MOD;
			}
		}
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			num = Integer.parseInt(br.readLine());
			if ((num & 1) == 1) {
				sb.append(ZERO);
			} else {
				sb.append(dp[num >> 1]);
			}
			sb.append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
