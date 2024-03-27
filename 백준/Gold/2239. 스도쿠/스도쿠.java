import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int NUM = 9;
	
	private static int[][] map, ans;
	private static boolean[][] rowVisited, colVisited;
	private static boolean[][][] boxVisited;
	
	private static boolean dfs(int x, int y) {
		int i;
		
		if (y == NUM) {
			if (++x == NUM) {
				return true;
			}
			y = 0;
		}
		if (map[x][y] != 0) {
			ans[x][y] = map[x][y];
			return dfs(x, y + 1);
		}
		for (i = 1; i <= NUM; i++) {
			if (rowVisited[x][i] || colVisited[y][i] || boxVisited[x / 3][y / 3][i]) {
				continue;
			}
			ans[x][y] = i;
			rowVisited[x][i] = true;
			colVisited[y][i] = true;
			boxVisited[x / 3][y / 3][i] = true;
			if (dfs(x, y + 1)) {
				return true;
			}
			rowVisited[x][i] = false;
			colVisited[y][i] = false;
			boxVisited[x / 3][y / 3][i] = false;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int i, j;
		char[] str;
		
		map = new int[NUM][NUM];
		rowVisited = new boolean[NUM][NUM + 1];
		colVisited = new boolean[NUM][NUM + 1];
		boxVisited = new boolean[NUM / 3][NUM / 3][NUM + 1];
		for (i = 0; i < NUM; i++) {
			str = br.readLine().toCharArray();
			for (j = 0; j < NUM; j++) {
				map[i][j] = str[j] - '0';
				if (map[i][j] != 0) {
					rowVisited[i][map[i][j]] = true;
					colVisited[j][map[i][j]] = true;
					boxVisited[i / 3][j / 3][map[i][j]] = true;
				}
			}
		}
		ans = new int[NUM][NUM];
		dfs(0, 0);
		for (i = 0; i < NUM; i++) {
			for (j = 0; j < NUM; j++) {
				sb.append(ans[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
