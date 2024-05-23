import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int MOD = 1_000_000;
	private static final char ZERO = '0';
	private static final char TEN = '1';
	private static final char TWENTY = '2';
	private static final char THR = '6';
	
	private static final boolean isPair(char prev, char curr) {
		return prev == TEN || (prev == TWENTY && curr <= THR);
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		int len;
		int[] dp;
		char[] str;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		if (str[0] == ZERO) {
			System.out.print(ZERO);
			return;
		}
		len = str.length;
		dp = new int[len + 1];
		dp[0] = 1;
		if (len == 1) {
			System.out.print(dp[0]);
			return;
		}
		dp[1] = 1;
		if (str[1] == ZERO && !isPair(str[0], str[1])) {
			System.out.print(ZERO);
			return;
		}
		for (i = 2; i <= len; i++) {
			if (str[i - 1] == ZERO) {
				if (isPair(str[i - 2], str[i - 1])) {
					dp[i] = dp[i - 2];
				} else {
					System.out.print(ZERO);
					return;
				}
				continue;
			}
			dp[i] = dp[i - 1];
			if (isPair(str[i - 2], str[i - 1])) {
				dp[i] = (dp[i] + dp[i - 2]) % MOD;
			}
		}
		System.out.print(dp[len]);
	}
}
