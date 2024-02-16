import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int r, c, max;
	private static char[][] map;
	private static boolean[] visited;
	
	private static void dfs(int x, int y, int cnt) {
		max = Math.max(max, cnt);
		if (x > 0 && !visited[map[x - 1][y]]) {
			visited[map[x - 1][y]] = true;
			dfs(x - 1, y, cnt + 1);
			visited[map[x - 1][y]] = false;
		}
		if (y > 0 && !visited[map[x][y - 1]]) {
			visited[map[x][y - 1]] = true;
			dfs(x, y - 1, cnt + 1);
			visited[map[x][y - 1]] = false;
		}
		if (x < r - 1 && !visited[map[x + 1][y]]) {
			visited[map[x + 1][y]] = true;
			dfs(x + 1, y, cnt + 1);
			visited[map[x + 1][y]] = false;
		}
		if (y < c - 1 && !visited[map[x][y + 1]]) {
			visited[map[x][y + 1]] = true;
			dfs(x, y + 1, cnt + 1);
			visited[map[x][y + 1]] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][];
		for (i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		max = 0;
		visited = new boolean['Z' + 1];
		visited[map[0][0]] = true;
		dfs(0, 0, 1);
		System.out.print(max);
	}
}
