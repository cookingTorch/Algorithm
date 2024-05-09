import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE >> 1;
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	private static final String FAIL = "Fail";
	
	private static int n;
	private static int m;
	private static int t;
	private static int[][] map;
	
	private static final int bfs(int destX, int destY) {
		int i;
		int x;
		int y;
		int nx;
		int ny;
		int time;
		int[] curr;
		boolean[][] visited;
		ArrayDeque<int[]> q;
		
		visited = new boolean[n][m];
		q = new ArrayDeque<>();
		q.addLast(new int[] {0, 0, 0});
		visited[0][0] = true;
		while (!q.isEmpty()) {
			curr = q.pollFirst();
			x = curr[0];
			y = curr[1];
			time = curr[2];
			if (time >= t) {
				continue;
			}
			for (i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || map[nx][ny] == 1) {
					continue;
				}
				if (nx == destX && ny == destY && time < t) {
					return time + 1;
				}
				q.addLast(new int[] {nx, ny, time + 1});
				visited[nx][ny] = true;
			}
		}
		return INF;
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int distGram;
		int distPrincess;
		int[] gram;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		gram = null;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					gram = new int[] {i, j};
				}
			}
		}
		distPrincess = bfs(n - 1, m - 1);
		distGram = bfs(gram[0], gram[1]) + n - 1 - gram[0] + m - 1 - gram[1];
		if (distGram > t) {
			distGram = INF;
		}
		if (distPrincess != INF || distGram != INF) {
			System.out.print(Math.min(distPrincess, distGram));
		} else {
			System.out.print(FAIL);
		}
	}
}
