import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final char B = 'B';
	private static final char E = 'E';
	private static final char EMPTY = '0';
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	
	private static int n;
	private static int[][][] distance;
	private static char[][] map;
	
	private static int dijkstra(int sx, int sy, int sdir, int ex, int ey, int edir) {
		int x, y, dir, nx, ny, i, j, k;
		int[] curr;
		PriorityQueue<int[]> pq;
		
		distance = new int[n][n][2];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				for (k = 0; k < 2; k++) {
					distance[i][j][k] = INF;
				}
			}
		}
		distance[sx][sy][sdir] = 0;
		pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(distance[o1[0]][o1[1]][o1[2]], distance[o2[0]][o2[1]][o2[2]]);
			}
		});
		pq.offer(new int[] {sx, sy, sdir});
		while (!pq.isEmpty()) {
			curr = pq.poll();
			x = curr[0];
			y = curr[1];
			dir = curr[2];
			if (x == ex && y == ey && dir == edir) {
				break;
			}
			for (i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= n || map[nx][ny] != EMPTY) {
					continue;
				}
				if (dir == 0) {
					if (ny < 1 || ny >= n - 1) {
						continue;
					}
					if (map[nx][ny - 1] == EMPTY && map[nx][ny + 1] == EMPTY) {
						if (distance[x][y][dir] + 1 < distance[nx][ny][dir]) {
							distance[nx][ny][dir] = distance[x][y][dir] + 1;
							pq.offer(new int[] {nx, ny, dir});
						}
					}
				}
				if (dir == 1) {
					if (nx < 1 || nx >= n - 1) {
						continue;
					}
					if (map[nx - 1][ny] == EMPTY && map[nx + 1][ny] == EMPTY) {
						if (distance[x][y][dir] + 1 < distance[nx][ny][dir]) {
							distance[nx][ny][dir] = distance[x][y][dir] + 1;
							pq.offer(new int[] {nx, ny, dir});
						}
					}
				}
			}
			if (x > 0 && x < n - 1 && y > 0 && y < n - 1) {
				if (map[x - 1][y - 1] == EMPTY
						&& map[x - 1][y] == EMPTY
						&& map[x - 1][y + 1] == EMPTY
						&& map[x][y - 1] == EMPTY
						&& map[x][y] == EMPTY
						&& map[x][y + 1] == EMPTY
						&& map[x + 1][y - 1] == EMPTY
						&& map[x + 1][y] == EMPTY
						&& map[x + 1][y + 1] == EMPTY) {
					if (distance[x][y][dir] + 1 < distance[x][y][dir ^ 1]) {
						distance[x][y][dir ^ 1] = distance[x][y][dir] + 1;
						pq.offer(new int[] {x, y, dir ^ 1});
					}
				}
			}
		}
		return distance[ex][ey][edir] == INF ? 0 : distance[ex][ey][edir];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int sdir, edir, i, j, idxB, idxE;
		int[][] b, e;
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][];
		b = new int[3][];
		e = new int[3][];
		idxB = 0;
		idxE = 0;
		for (i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
			for (j = 0; j < n; j++) {
				if (map[i][j] == B) {
					b[idxB++] = new int[] {i, j};
					map[i][j] = EMPTY;
				} else if (map[i][j] == E) {
					e[idxE++] = new int[] {i, j};
					map[i][j] = EMPTY;
				}
			}
		}
		if (b[0][0] == b[1][0]) {
			sdir = 0;
		} else {
			sdir = 1;
		}
		if (e[0][0] == e[1][0]) {
			edir = 0;
		} else {
			edir = 1;
		}
		System.out.print(dijkstra(b[1][0], b[1][1], sdir, e[1][0], e[1][1], edir));
	}
}
