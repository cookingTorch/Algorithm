import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	private static int[][] dp;
	private static int[][] cost;
	
	private static int getDp(int x, int y) {
		int i;
		
		if (dp[x][y] != INF) {
			return dp[x][y];
		}
		for (i = 0; i < 3; i++) {
			if (i == y) {
				continue;
			}
			dp[x][y] = Math.min(dp[x][y], getDp(x - 1, i) + cost[x][y]);
		}
		return dp[x][y];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, ans, i;
		
		n = Integer.parseInt(br.readLine());
		cost = new int[n][3];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		dp = new int[n][3];
		for (i = 0; i < n; i++) {
			Arrays.fill(dp[i], INF);
		}
		dp[0] = cost[0];
		ans = Math.min(getDp(n - 1, 0), getDp(n - 1, 1));
		ans = Math.min(ans, getDp(n - 1, 2));
		System.out.print(ans);
	}
}
