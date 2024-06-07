import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	private static final int[] dx = new int[] {-1, 0, 1, 0};
	private static final int[] dy = new int[] {0, 1, 0, -1};
	private static final char L = 'L';
	private static final char W = 'W';
	
	private static int n;
	private static int m;
	private static int[][] dist;
	private static char[][] map;
	private static boolean[][] visited;
	private static ArrayDeque<int[]> q;
	
	private static final int bfs(int startX, int startY) {
		int i;
		int x;
		int y;
		int nx;
		int ny;
		int max;
		int[] curr;
		
		max = 0;
		dist[startX][startY] = 0;
		q.addLast(new int[] {startX, startY});
		visited[startX][startY] = true;
		while (!q.isEmpty()) {
			curr = q.pollFirst();
			x = curr[0];
			y = curr[1];
			for (i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == W || visited[nx][ny]) {
					continue;
				}
				dist[nx][ny] = dist[x][y] + 1;
				max = dist[nx][ny];
				visited[nx][ny] = true;
				q.addLast(new int[] {nx, ny});
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int max;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][];
		for (i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}
		max = 0;
		q = new ArrayDeque<>();
		dist = new int[n][m];
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				if (map[i][j] == L) {
					visited = new boolean[n][m];
					max = Math.max(max, bfs(i, j));
				}
			}
		}
		System.out.print(max);
	}
}
