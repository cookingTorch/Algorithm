import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i, j;
		int[][][] dp;
		boolean[][] wall;
		
		n = Integer.parseInt(br.readLine());
		wall = new boolean[n + 1][n + 1];
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			wall[i][0] = true;
			for (j = 1; j <= n; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					wall[i][j] = true;
				}
			}
		}
		Arrays.fill(wall[0], true);
		dp = new int[n + 1][n + 1][3];
		dp[1][2][0] = 1;
		for (i = 1; i <= n; i++) {
			for (j = 3; j <= n; j++) {
				if (!wall[i][j]) {
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][1];
					if (!wall[i - 1][j] && !wall[i][j - 1]) {
						dp[i][j][1] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
					}
					dp[i][j][2] = dp[i - 1][j][1] + dp[i - 1][j][2];
				}
			}
		}
		System.out.print(dp[n][n][0] + dp[n][n][1] + dp[n][n][2]);
	}
}
