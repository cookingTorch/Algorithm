import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int[] weight, value;
	private static int[][] dp;
	
	private static int getDp(int i, int j) {
		if (i < 0 || j < 0) {
			return 0;
		}
		if (dp[i][j] != -1) {
			return dp[i][j];
		}
		if (j < weight[i]) {
			dp[i][j] = getDp(i - 1, j);
		} else {
			dp[i][j] = Math.max(getDp(i - 1, j), getDp(i - 1, j - weight[i]) + value[i]);
		}
		return dp[i][j];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, k, i;
		
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
			Arrays.fill(dp[i], -1);
			dp[i][0] = 0;
		}
		System.out.print(getDp(n, k));
	}
}
