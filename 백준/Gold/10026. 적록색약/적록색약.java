import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static char[][] painting;
	private static boolean[][] visited;
	
	private static void dfs(int x, int y, char color) {
		if (x < 0 || y < 0 || x >= n || y >= n || painting[x][y] != color || visited[x][y]) {
			return;
		}
		visited[x][y] = true;
		if (painting[x][y] == 'R') {
			painting[x][y] = 'G';
		}
		dfs(x - 1, y, color);
		dfs(x, y - 1, color);
		dfs(x + 1, y, color);
		dfs(x, y + 1, color);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int cnt, i, j;
		
		n = Integer.parseInt(br.readLine());
		painting = new char[n][];
		for (i = 0; i < n; i++) {
			painting[i] = br.readLine().toCharArray();
		}
		visited = new boolean[n][n];
		cnt = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (!visited[i][j]) {
					dfs(i, j, painting[i][j]);
					cnt++;
				}
			}
		}
		sb.append(cnt).append(' ');
		visited = new boolean[n][n];
		cnt = 0;
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (!visited[i][j]) {
					dfs(i, j, painting[i][j]);
					cnt++;
				}
			}
		}
		sb.append(cnt);
		System.out.print(sb);
	}
}
