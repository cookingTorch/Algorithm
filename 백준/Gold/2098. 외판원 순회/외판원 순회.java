import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE / 2;
	
	private static int n;
	private static int[][] weights, dp;
	
	private static int getDp(int node, int visited) {
		int next;
		
		if (dp[node][visited] != 0) {
			return dp[node][visited];
		}
		dp[node][visited] = INF;
		for (next = 1; next < n; next++) {
			if ((visited & (1 << next)) == 0 && weights[node][next] != 0) {
				dp[node][visited] = Math.min(dp[node][visited], getDp(next, visited | (1 << next)) + weights[node][next]);
			}
		}
		return dp[node][visited];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i, j;
		
		n = Integer.parseInt(br.readLine());
		weights = new int[n][n];
		dp = new int[n][1 << n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			weights[i][0] = Integer.parseInt(st.nextToken());
			dp[i][(1 << n) - 1] = weights[i][0] == 0 ? INF : weights[i][0];
			for (j = 1; j < n; j++) {
				weights[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.print(getDp(0, 1));
	}
}
