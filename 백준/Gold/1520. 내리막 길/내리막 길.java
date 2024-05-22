import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int EMPTY = -1;
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	
	private static int m;
	private static int n;
	private static int[][] dp;
	private static int[][] map;
	
	private static int getDp(int x, int y) {
		int i;
		int nx;
		int ny;
		
		if (dp[x][y] != EMPTY) {
			return dp[x][y];
		}
		dp[x][y] = 0;
		for (i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (nx < 0 || nx >= m || ny < 0 || ny >= n || map[nx][ny] <= map[x][y]) {
				continue;
			}
			dp[x][y] += getDp(nx, ny);
		}
		return dp[x][y];
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		int j;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		dp = new int[m][n];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = EMPTY;
			}
		}
		dp[0][0] = 1;
		System.out.print(getDp(m - 1, n - 1));
	}
}
