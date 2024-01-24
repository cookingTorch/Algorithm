import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n, m, ans;
	private static char[][] school;
	private static boolean[][] visited;
	
	private static void dfs(int x, int y) {
		if (x < 0 || y < 0 || x >= n || y >= m || visited[x][y] || school[x][y] == 'X') {
			return;
		}
		visited[x][y] = true;
		if (school[x][y] == 'P') {
			ans++;
		}
		dfs(x - 1, y);
		dfs(x + 1, y);
		dfs(x, y - 1);
		dfs(x, y + 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String str;
		
		int x, y, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		school = new char[n][m];
		x = 0;
		y = 0;
		for (i = 0; i < n; i++) {
			str = br.readLine();
			for (j = 0; j < m; j++) {
				school[i][j] = str.charAt(j);
				if (school[i][j] == 'I') {
					x = i;
					y = j;
				}
			}
		}
		ans = 0;
		visited = new boolean[n][m];
		dfs(x, y);
		if (ans == 0) {
			System.out.println("TT");
		} else {
			System.out.println(ans);
		}
	}
}