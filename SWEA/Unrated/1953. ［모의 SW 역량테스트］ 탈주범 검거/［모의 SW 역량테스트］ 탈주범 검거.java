import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	private static final int INF = Integer.MAX_VALUE;
	private static final int SIZE = 52;
	private static final int UP = 8;
	private static final int DOWN = 4;
	private static final int LEFT = 2;
	private static final int RIGHT = 1;
	private static final int[] dir = {-SIZE, -1, SIZE, 1};
	private static final int[] way = {UP, LEFT, DOWN, RIGHT};
	private static final int[] type = {0, 15, 12, 3, 9, 5, 6, 10};
	
	private static int l, ans;
	private static int[] map, distance;
	private static Queue<Integer> q;
	
	private static void bfs(int start) {
		int curr, next, out, in, i;
		
		ans = distance[start] = 1;
		q.add(start);
		while (!q.isEmpty()) {
			curr = q.poll();
			if (distance[curr] < l) {
				for (i = 0; i < 4; i++) {
					next = curr + dir[i];
					out = way[i];
					in = way[(i + 2) % 4];
					if ((map[curr] & out) == out && (map[next] & in) == in) {
						if (distance[curr] + 1 < distance[next]) {
							if (distance[next] == INF) {
								ans++;
							}
							distance[next] = distance[curr] + 1;
							q.add(next);
						}
					}
				}
			}
		}
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, m, r, c, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken()) + 1;
		c = Integer.parseInt(st.nextToken()) + 1;
		l = Integer.parseInt(st.nextToken());
		for (i = 1; i <= n; i++) {
			map[i * SIZE + m + 1] = 0;
		}
		for (i = 1; i <= m; i++) {
			map[(n + 1) * SIZE + i] = 0;
		}
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= m; j++) {
				map[i * SIZE + j] = type[Integer.parseInt(st.nextToken())];
			}
		}
		ans = 0;
		for (i = 0; i <= n + 1; i++) {
			for (j = 0; j <= m + 1; j++) {
				distance[i * SIZE + j] = INF;
			}
		}
		bfs(r * SIZE + c);
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		map = new int[SIZE * SIZE];
		distance = new int[SIZE * SIZE];
		q = new ArrayDeque<>();
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
