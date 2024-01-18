import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX = 2000000;
	
	private static int bfs(int[][] map, int n, int m) {
		int x, y, i, j, ans;
		int[] curr;
		int[][] dist, dist2;
		boolean[][] inQueue;
		Queue<int[]> q = new LinkedList<>();
		
		inQueue = new boolean[n][m];
		dist = new int[n][m];
		dist2 = new int[n][m];
		for (i = 0; i < n; i++) {
			Arrays.fill(dist[i], MAX);
			Arrays.fill(dist2[i], MAX);
		}
		dist[0][0] = 1;
		dist2[0][0] = 1;
		q.add(new int[] {0, 0});
		inQueue[0][0] = true;
		while (!q.isEmpty()) {
			curr = q.poll();
			x = curr[0];
			y = curr[1];
			inQueue[x][y] = false;
			if (y < m - 1) {
				i = x;
				j = y + 1;
				if (map[i][j] == 0) {
					if (dist[x][y] + 1 < dist[i][j]) {
						dist[i][j] = dist[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
					if (dist2[x][y] + 1 < dist2[i][j]) {
						dist2[i][j] = dist2[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
				} else {
					if (dist[x][y] + 1 < dist2[i][j]) {
						dist2[i][j] = dist[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
				}
			}
			if (x < n - 1) {
				i = x + 1;
				j = y;
				if (map[i][j] == 0) {
					if (dist[x][y] + 1 < dist[i][j]) {
						dist[i][j] = dist[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
					if (dist2[x][y] + 1 < dist2[i][j]) {
						dist2[i][j] = dist2[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
				} else {
					if (dist[x][y] + 1 < dist2[i][j]) {
						dist2[i][j] = dist[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
				}
			}
			if (x > 0) {
				i = x - 1;
				j = y;
				if (map[i][j] == 0) {
					if (dist[x][y] + 1 < dist[i][j]) {
						dist[i][j] = dist[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
					if (dist2[x][y] + 1 < dist2[i][j]) {
						dist2[i][j] = dist2[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
				} else {
					if (dist[x][y] + 1 < dist2[i][j]) {
						dist2[i][j] = dist[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
				}
			}
			if (y > 0) {
				i = x;
				j = y - 1;
				if (map[i][j] == 0) {
					if (dist[x][y] + 1 < dist[i][j]) {
						dist[i][j] = dist[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
					if (dist2[x][y] + 1 < dist2[i][j]) {
						dist2[i][j] = dist2[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
				} else {
					if (dist[x][y] + 1 < dist2[i][j]) {
						dist2[i][j] = dist[x][y] + 1;
						if (!inQueue[i][j]) {
							q.add(new int[] {i, j});
							inQueue[i][j] = true;
						}
					}
				}
			}
		}
		ans = Math.min(dist[n - 1][m - 1], dist2[n - 1][m - 1]);
		if (ans == MAX) {
			return -1;
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, i, j;
		int[][] map;
		String str;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (i = 0; i < n; i++) {
			str = br.readLine();
			for (j = 0; j < m; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		sb.append(bfs(map, n, m));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}