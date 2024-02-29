import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	private static final int EMPTY = 0;
	private static final int WALL = 1;
	private static final int VIRUS = 2;
	
	private static int n, m, cnt, max;
	private static int[][] map;
	private static boolean[][] visited;
	private static LinkedList<int[]> viruses;
	
	private static void dfs(int x, int y) {
		if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || map[x][y] == WALL) {
			return;
		}
		visited[x][y] = true;
		cnt++;
		dfs(x - 1, y);
		dfs(x + 1, y);
		dfs(x, y - 1);
		dfs(x, y + 1);
	}
	
	private static int infect() {
		cnt = 0;
		visited = new boolean[n][m];
		for (int[] virus : viruses) {
			if (!visited[virus[0]][virus[1]]) {
				dfs(virus[0], virus[1]);
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int sum, size, i, j, k;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sum = 0;
		map = new int[n][m];
		viruses = new LinkedList<>();
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == EMPTY) {
					sum++;
				} else if (map[i][j] == VIRUS) {
					sum++;
					viruses.add(new int[] {i, j});
				}
			}
		}
		size = n * m;
		max = 0;
		sum -= 3;
		for (i = 0; i < size; i++) {
			if (map[i / m][i % m] != EMPTY) {
				continue;
			}
			for (j = i + 1; j < size; j++) {
				if (map[j / m][j % m] != EMPTY) {
					continue;
				}
				for (k = j + 1; k < size; k++) {
					if (map[k / m][k % m] != EMPTY) {
						continue;
					}
					map[i / m][i % m] = WALL;
					map[j / m][j % m] = WALL;
					map[k / m][k % m] = WALL;
					max = Math.max(max, sum - infect());
					map[i / m][i % m] = EMPTY;
					map[j / m][j % m] = EMPTY;
					map[k / m][k % m] = EMPTY;
				}
			}
		}
		System.out.print(max);
	}
}
