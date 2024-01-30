import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i, j;
		int[][] dp, cost;
		
		n = Integer.parseInt(br.readLine());
		cost = new int[n][3];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		dp = new int[n][3];
		dp[0] = cost[0];
		for (i = 1; i < n; i++) {
			for (j = 0; j < 3; j++) {
				dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + cost[i][j];
			}
		}
		System.out.print(Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2])));
	}
}
