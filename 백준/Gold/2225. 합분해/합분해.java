import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MOD = 1_000_000_000;
	
	private static int[][] dp;
	
	private static final int getDp(int n, int k) {
		int i;
		
		if (n == 0 || k == 0) {
			return 1;
		}
		if (dp[n][k] != 0) {
			return dp[n][k];
		}
		for (i = 0; i <= k; i++) {
			dp[n][k] = (dp[n][k] + getDp(n - 1, i)) % MOD;
		}
		return dp[n][k];
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int k;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new int[n + 1][k];
		System.out.print(getDp(n, k - 1));
	}
}
