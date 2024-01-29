import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, k, i, j;
		int[] weight, value;
		int[][] dp;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		weight = new int[n + 1];
		value = new int[n + 1];
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		dp = new int[n + 1][k + 1];
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= k; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j >= weight[i]) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weight[i]] + value[i]);
				}
			}
		}
		System.out.print(dp[n][k]);
	}
}
