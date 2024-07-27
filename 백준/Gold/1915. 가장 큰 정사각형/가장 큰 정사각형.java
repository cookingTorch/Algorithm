import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char FILLED = '1';

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int j;
		int max;
		int[][] dp;
		int[][] up;
		int[][] left;
		char[][] map;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for (i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		max = 0;
		dp = new int[n][m];
		up = new int[n][m];
		left = new int[n][m];
		for (i = 0; i < m; i++) {
			if (map[0][i] == FILLED) {
				if ((dp[0][i] = 1) > max) {
					max = 1;
				};
				up[0][i] = 1;
				left[0][i] = 1;
			}
		}
		for (i = 1; i < n; i++) {
			if (map[i][0] == FILLED) {
				if ((dp[i][0] = 1) > max) {
					max = 1;
				};
				up[i][0] = 1;
				left[i][0] = 1;
			}
		}
		for (i = 1; i < n; i++) {
			for (j = 1; j < m; j++) {
				if (map[i][j] == FILLED) {
					up[i][j] = up[i - 1][j] + 1;
					left[i][j] = left[i][j - 1] + 1;
					if ((dp[i][j] = Math.min(Math.min(up[i][j], left[i][j]), dp[i - 1][j - 1] + 1)) > max) {
						max = dp[i][j];
					}
				}
			}
		}
		System.out.print(max * max);
	}
}
