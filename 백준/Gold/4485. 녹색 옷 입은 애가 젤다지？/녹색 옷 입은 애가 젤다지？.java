import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int MAX_N = 125;
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	
	private static int dijkstra(int n, int[][] map, int[][] distance, PriorityQueue<int[]> pq) {
		int x, y, nx, ny, i, j;
		int[] curr;
		
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				distance[i][j] = INF;
			}
			distance[i][n + 1] = 0;
			distance[n + 1][i] = 0;
		}
		distance[1][1] = map[1][1];
		pq.clear();
		pq.add(new int[] {1, 1});
		while (true) {
			curr = pq.poll();
			x = curr[0];
			y = curr[1];
			for (i = 0; i < 4; i++) {
				nx = x + dx[i];
				ny = y + dy[i];
				if (distance[x][y] + map[nx][ny] < distance[nx][ny]) {
					distance[nx][ny] = distance[x][y] + map[nx][ny];
					if (nx == n && ny == n) {
						return distance[n][n];
					}
					pq.add(new int[] {nx, ny});
				}
			}
		}
	}
	
	private static int solution(BufferedReader br, StringTokenizer st, int n, int[][] map, int[][] distance, PriorityQueue<int[]> pq) throws IOException {
		int i, j;
		
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		return dijkstra(n, map, distance, pq);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int n, testCase;
		int[][] map, distance;
		PriorityQueue<int[]> pq;
		
		map = new int[MAX_N + 2][MAX_N + 2];
		distance = new int[MAX_N + 2][MAX_N + 2];
		pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(distance[o1[0]][o1[1]], distance[o2[0]][o2[1]]);
			}
		});
		for (testCase = 1; (n = Integer.parseInt(br.readLine())) != 0; testCase++) {
			sb.append("Problem ").append(testCase).append(": ").append(solution(br, st, n, map, distance, pq)).append('\n');
		}
		System.out.print(sb);
	}
}
