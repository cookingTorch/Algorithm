import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int KEYS = 1 << 6;
	private static final int FIRST_DOOR = 'A';
	private static final int LAST_DOOR = 'F';
	private static final int FIRST_KEY = 'a';
	private static final int LAST_KEY = 'f';
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	private static final char START = '0';
	private static final char END = '1';
	private static final char WALL = '#';
	
	private static int n, m;
	private static int[][][] distance;
	private static char[][] map;
	private static ArrayList<int[]> ends;
	
	private static int bfs(int[] start) {
		int x, y, z, nx, ny, nz, ans, i, j, k;
		int[] curr;
		Queue<int[]> q;
		
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				for (k = 0; k < KEYS; k++) {
					distance[i][j][k] = INF;
				}
			}
		}
		distance[start[0]][start[1]][start[2]] = 0;
		q = new ArrayDeque<>();
		q.add(start);
		while (!q.isEmpty()) {
			curr = q.poll();
			x = curr[0];
			y = curr[1];
			z = curr[2];
			for (i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				nz = z;
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || map[nx][ny] == WALL) {
					continue;
				} else if (FIRST_DOOR <= map[nx][ny] && map[nx][ny] <= LAST_DOOR) {
					if ((z & (1 << (map[nx][ny] - FIRST_KEY))) == 0) {
						continue;
					}
				} else if (FIRST_KEY <= map[nx][ny] && map[nx][ny] <= LAST_KEY) {
					nz |= (1 << (map[nx][ny] - FIRST_KEY));
				}
				if (distance[x][y][z] + 1 < distance[nx][ny][nz]) {
					distance[nx][ny][nz] = distance[x][y][z] + 1;
					if (map[nx][ny] != END) {
						q.add(new int[] {nx, ny, nz});
					}
				}
			}
		}
		ans = INF;
		for (int[] end : ends) {
			for (i = 0; i < KEYS; i++) {
				ans = Math.min(ans, distance[end[0]][end[1]][i]);
			}
		}
		return ans == INF ? -1 : ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i, j;
		int[] start;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		start = null;
		map = new char[n][m];
		ends = new ArrayList<>();
		for (i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (j = 0; j < m; j++) {
				if (map[i][j] == START) {
					start = new int[] {i, j, 0};
				} else if (map[i][j] == END) {
					ends.add(new int[] {i, j});
				}
			}
		}
		distance = new int[n][m][KEYS];
		System.out.print(bfs(start));
	}
}
