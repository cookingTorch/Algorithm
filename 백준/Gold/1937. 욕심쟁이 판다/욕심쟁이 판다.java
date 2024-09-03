import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int EMPTY = 0;
	private static final int[] dx = {0, 0, -1, 1};
	private static final int[] dy = {1, -1, 0, 0};

	private static int n;
	private static int[][] map;
	private static int[][] dp;

	private static int getDp(int x, int y) {
		int nx;
		int ny;
		int i;

		if (dp[x][y] != EMPTY) {
			return dp[x][y];
		}
		for (i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] >= map[x][y]) {
				continue;
			}
			dp[x][y] = Math.max(getDp(nx, ny), dp[x][y]);
		}
		return ++dp[x][y];
	}

	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int max;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[n][n];
		max = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				max = Math.max(max, getDp(i, j));
			}
		}
		System.out.print(max);
	}
}
