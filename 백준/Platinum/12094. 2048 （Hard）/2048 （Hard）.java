import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	private static final int LIMIT = 10;
	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int LEFT = 2;
	private static final int RIGHT = 3;
	private static final int EMPTY = 0;
	
	private static int n, ans;
	private static Deque<Integer> stack;
	
	private static int[][] getClone(int[][] map) {
		int i, j;
		int[][] clone;
		
		clone = new int[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				clone[i][j] = map[i][j];
			}
		}
		return clone;
	}
	
	private static int up(int[][] map) {
		int max, x, y;
		
		max = 0;
		for (y = 0; y < n; y++) {
			for (x = n - 1; x >= 0; x--) {
				if (map[x][y] != EMPTY) {
					stack.add(map[x][y]);
					map[x][y] = EMPTY;
				}
			}
			for (x = 0; !stack.isEmpty(); x++) {
				map[x][y] = stack.poll();
				if (!stack.isEmpty() && stack.peek() == map[x][y]) {
					map[x][y] += stack.poll();
				}
				max = Math.max(max, map[x][y]);
			}
		}
		return max;
	}
	
	private static int down(int[][] map) {
		int max, x, y;
		
		max = 0;
		for (y = 0; y < n; y++) {
			for (x = 0; x < n; x++) {
				if (map[x][y] != EMPTY) {
					stack.add(map[x][y]);
					map[x][y] = EMPTY;
				}
			}
			for (x = n - 1; !stack.isEmpty(); x--) {
				map[x][y] = stack.poll();
				if (!stack.isEmpty() && stack.peek() == map[x][y]) {
					map[x][y] += stack.poll();
				}
				max = Math.max(max, map[x][y]);
			}
		}
		return max;
	}
	
	private static int left(int[][] map) {
		int max, x, y;
		
		max = 0;
		for (x = 0; x < n; x++) {
			for (y = n - 1; y >= 0; y--) {
				if (map[x][y] != EMPTY) {
					stack.add(map[x][y]);
					map[x][y] = EMPTY;
				}
			}
			for (y = 0; !stack.isEmpty(); y++) {
				map[x][y] = stack.poll();
				if (!stack.isEmpty() && stack.peek() == map[x][y]) {
					map[x][y] += stack.poll();
				}
				max = Math.max(max, map[x][y]);
			}
		}
		return max;
	}
	
	private static int right(int[][] map) {
		int max, x, y;
		
		max = 0;
		for (x = 0; x < n; x++) {
			for (y = 0; y < n; y++) {
				if (map[x][y] != EMPTY) {
					stack.add(map[x][y]);
					map[x][y] = EMPTY;
				}
			}
			for (y = n - 1; !stack.isEmpty(); y--) {
				map[x][y] = stack.poll();
				if (!stack.isEmpty() && stack.peek() == map[x][y]) {
					map[x][y] += stack.poll();
				}
				max = Math.max(max, map[x][y]);
			}
		}
		return max;
	}
	
	private static void dfs(int[][] prev, int depth) {
		int dir, max, i, j;
		int[][] map;
		
		loop:
		for (dir = 0; dir < 4; dir++) {
			map = getClone(prev);
			max = 0;
			switch (dir) {
			case UP:
				max = up(map);
				break;
			case DOWN:
				max = down(map);
				break;
			case LEFT:
				max = left(map);
				break;
			case RIGHT:
				max = right(map);
			}
			ans = Math.max(ans, max);
			if (depth == LIMIT) {
				continue;
			}
			if (max * (int) Math.pow(2, LIMIT - depth) <= ans) {
				continue;
			}
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					if (map[i][j] != prev[i][j]) {
						dfs(getClone(map), depth + 1);
						continue loop;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i, j;
		int[][] map;
		
		n = Integer.parseInt(br.readLine());
		ans = 0;
		map = new int[n][n];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				ans = Math.max(ans, map[i][j]);
			}
		}
		stack = new ArrayDeque<>();
		dfs(map, 1);
		System.out.print(ans);
	}
}
