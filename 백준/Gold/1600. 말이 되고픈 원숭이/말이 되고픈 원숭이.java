import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int[] dx = {-1, 0, 1, 0, 1, 2, 2, 1, -1, -2, -2, -1};
	private static final int[] dy = {0, 1, 0, -1, 2, 1, -1, -2, -2, -1, 1, 2};
	
	private static int k, w, h;
	private static int[][] map;
	private static int[][][] distance;
	private static boolean[][][] inQueue;
	
	private static int[] bfs(int startX, int startY) {
		int x, y, nx, ny, jump, i, j;
		int[] curr;
		Queue<int[]> q;
		
		for (i = 0; i < h; i++) {
			for (j = 0; j < w; j++) {
				Arrays.fill(distance[i][j], INF);
			}
		}
		q = new ArrayDeque<>();
		q.add(new int[] {startX, startY, 0});
		distance[startX][startY][0] = 0;
		while (!q.isEmpty()) {
			curr = q.poll();
			x = curr[0];
			y = curr[1];
			jump = curr[2];
			inQueue[x][y][jump] = false;
			for (i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
					continue;
				}
				if (map[nx][ny] != 1 && distance[x][y][jump] + 1 < distance[nx][ny][jump]) {
					distance[nx][ny][jump] = distance[x][y][jump] + 1;
					if (!inQueue[nx][ny][jump]) {
						q.add(new int[] {nx, ny, jump});
						inQueue[nx][ny][jump] = true;
					}
				}
			}
			if (jump < k) {
				for (i = 4; i < 12; i++) {
					nx = x + dx[i];
					ny = y + dy[i];
					if (nx < 0 || nx >= h || ny < 0 || ny >= w) {
						continue;
					}
					if (map[nx][ny] != 1 && distance[x][y][jump] + 1 < distance[nx][ny][jump + 1]) {
						distance[nx][ny][jump + 1] = distance[x][y][jump] + 1;
						if (!inQueue[nx][ny][jump + 1]) {
							q.add(new int[] {nx, ny, jump + 1});
							inQueue[nx][ny][jump + 1] = true;
						}
					}
				}
			}
		}
		return distance[h - 1][w - 1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int min, i, j;
		int[] dist;
		
		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][w];
		for (i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		distance = new int[h][w][k + 1];
		inQueue = new boolean[h][w][k + 1];
		dist = bfs(0, 0);
		min = INF;
		for (i = 0; i <= k; i++) {
			min = Math.min(min, dist[i]);
		}
		System.out.print(min < INF ? min : "-1");
	}
}
