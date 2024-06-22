import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int k;
		int a;
		int b;
		int i;
		int j;
		int l;
		int max;
		int[][] dp;
		int[][] adj;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		adj = new int[n][n + 1];
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if (a < b) {
				adj[a][b] = Math.max(adj[a][b], Integer.parseInt(st.nextToken()));
			}
		}
		dp = new int[n + 1][m + 1];
		dp[1][1] = 1;
		for (i = 1; i < n; i++) {
			for (j = 1; j < m; j++) {
				if (dp[i][j] != 0) {
					for (l = i + 1; l <= n; l++) {
						if (adj[i][l] != 0) {
							dp[l][j + 1] = Math.max(dp[l][j + 1], dp[i][j] + adj[i][l]);
						}
					}
				}
			}
		}
		max = 1;
		for (i = 1; i <= m; i++) {
			max = Math.max(max, dp[n][i]);
		}
		System.out.print(max - 1);
	}
}
