import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char ROAD = '.';
	private static final char WALL = 'T';
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	
	private static int r, c, k, ans;
	private static char[][] map;
	
	private static void dfs(int x, int y, int depth) {
		int i, nx, ny;
		
		if (depth == k) {
			if (x == 1 && y == c) {
				ans++;
			}
			return;
		} else if (x == 1 && y == c) {
			return;
		}
		
		for (i = 0; i < 4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if (map[nx][ny] == ROAD) {
				map[nx][ny] = WALL;
				dfs(nx, ny, depth + 1);
				map[nx][ny] = ROAD;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i, j;
		char[] str;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new char[r + 2][c + 2];
		for (i = 1; i <= r; i++) {
			str = br.readLine().toCharArray();
			for (j = 1; j <= c; j++) {
				map[i][j] = str[j - 1];
			}
		}
		ans = 0;
		map[r][1] = WALL;
		dfs(r, 1, 1);
		System.out.print(ans);
	}
}
