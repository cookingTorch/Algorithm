import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static final int SIZE = 22;
	private static final int MAX = 101;
	
	private static int ldMax, rdMax, ruMax, luMax, ans;
	private static int[][] map;
	private static boolean[] visited;
	
	private static void ld(int x, int y, int depth, int cnt) {
		if (visited[map[x][y]]) {
			return;
		}
		visited[map[x][y]] = true;
		if (depth < ldMax) {
			ld(x + 1, y - 1, depth + 1, cnt + 1);
		}
		ruMax = depth;
		rd(x + 1, y + 1, 1, cnt + 1);
		visited[map[x][y]] = false;
	}
	
	private static void rd(int x, int y, int depth, int cnt) {
		if (visited[map[x][y]]) {
			return;
		}
		visited[map[x][y]] = true;
		if (depth < rdMax) {
			rd(x + 1, y + 1, depth + 1, cnt + 1);
		}
		luMax = depth;
		ru(x - 1, y + 1, 1, cnt + 1);
		visited[map[x][y]] = false;
	}

	private static void ru(int x, int y, int depth, int cnt) {
		if (visited[map[x][y]]) {
			return;
		}
		visited[map[x][y]] = true;
		if (depth == ruMax) {
			lu(x - 1, y - 1, 1, cnt + 1);
		} else {
			ru(x - 1, y + 1, depth + 1, cnt + 1);
		}
		visited[map[x][y]] = false;
	}

	private static void lu(int x, int y, int depth, int cnt) {
		if (depth == luMax) {
			ans = Math.max(ans, cnt);
			return;
		}
		if (visited[map[x][y]]) {
			return;
		}
		visited[map[x][y]] = true;
		lu(x - 1, y - 1, depth + 1, cnt + 1);
		visited[map[x][y]] = false;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, i, j;
		
		n = Integer.parseInt(br.readLine());
		for (i = 0; i <= n + 1; i++) {
			map[i][n + 1] = 0;
			map[n + 1][i] = 0;
		}
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = 0;
		for (i = 1; i < n - 1; i++) {
			for (j = 2; j < n; j++) {
				visited[map[i][j]] = true;
				ldMax = n - i - 1;
				rdMax = n - j;
				ld(i + 1, j - 1, 1, 1);
				visited[map[i][j]] = false;
			}
		}
		return ans > 1 ? ans : -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		map = new int[SIZE][SIZE];
		visited = new boolean[MAX];
		visited[0] = true;
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
