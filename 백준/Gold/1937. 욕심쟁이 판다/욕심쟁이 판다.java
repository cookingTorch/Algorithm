import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	
	private static int n;
	private static int[][] map, dp;
	
	private static int getDp(int x, int y) {
		int nx, ny, i;
		
		if (dp[x][y] != 0) {
			return dp[x][y];
		}
		dp[x][y] = 1;
		for (i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
				continue;
			}
			if (map[nx][ny] < map[x][y]) {
				dp[x][y] = Math.max(dp[x][y], getDp(nx, ny) + 1);
			}
		}
		return dp[x][y];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int max, i, j;
		
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
