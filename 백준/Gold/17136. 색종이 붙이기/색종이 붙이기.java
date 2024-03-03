import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int SIZE = 12;
	private static final int MAX = 10;
	private static final int NUM = 5;
	
	private static int ans;
	private static int[] papers;
	private static int[][] map;
	
	private static boolean check(int x, int y, int size) {
		int i, j;
		
		for (i = x; i < x + size; i++) {
			for (j = y; j < y + size; j++) {
				if (map[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void attach(int x, int y, int size, int num) {
		int i, j;
		
		for (i = x; i < x + size; i++) {
			for (j = y; j < y + size; j++) {
				map[i][j] = num;
			}
		}
	}
	
	private static void dfs(int x, int y, int depth) {
		int size;
		
		if (depth >= ans) {
			return;
		}
		if (x == MAX && y == MAX + 1) {
			ans = Math.min(ans, depth);
			return;
		}
		if (y == MAX + 1) {
			x++;
			y = 1;
		}
		if (map[x][y] == 0) {
			dfs(x, y + 1, depth);
			return;
		}
		for (size = NUM; size > 0; size--) {
			if (papers[size] > 0 && check(x, y, size)) {
				attach(x, y, size, 0);
				papers[size]--;
				dfs(x, y + 1, depth + 1);
				papers[size]++;
				attach(x, y, size, 1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i, j;
		
		map = new int[SIZE][SIZE];
		for (i = 1; i <= MAX; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= MAX; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		papers = new int[] {0, NUM, NUM, NUM, NUM, NUM};
		ans = INF;
		dfs(1, 1, 0);
		System.out.print(ans == INF ? "-1" : ans);
	}
}
