import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int HIGHT = 12;
	private static final int WIDTH = 6;
	private static final int[] dx = {-1, 0, 1, 0};
	private static final int[] dy = {0, 1, 0, -1};
	private static final char EMPTY = '.';
	
	private static char[][] map;
	private static boolean[][] visited;
	private static Queue<int[]> q;
	private static Deque<Character> dq;
	
	private static void dfs(int x, int y, char color) {
		int i;
		
		if (x < 0 || x >= HIGHT || y < 0 || y >= WIDTH || map[x][y] != color || visited[x][y]) {
			return;
		}
		visited[x][y] = true;
		q.add(new int[] {x, y});
		for (i = 0; i < 4; i++) {
			dfs(x + dx[i], y + dy[i], color);
		}
	}
	
	private static void delete() {
		int[] curr;
		
		while (!q.isEmpty()) {
			curr = q.poll();
			map[curr[0]][curr[1]] = EMPTY;
		}
	}
	
	private static boolean puyo() {
		int i, j;
		boolean flag;
		
		flag = false;
		visited = new boolean[HIGHT][WIDTH];
		for (i = 0; i < HIGHT; i++) {
			for (j = 0; j < WIDTH; j++) {
				if (map[i][j] != EMPTY) {
					dfs(i, j, map[i][j]);
					if (q.size() >= 4) {
						delete();
						flag = true;
					} else {
						q.clear();
					}
				}
			}
		}
		return flag;
	}
	
	private static void order() {
		int i, j;
		
		for (i = 0; i < WIDTH; i++) {
			for (j = 0; j < HIGHT; j++) {
				if (map[j][i] != EMPTY) {
					dq.addFirst(map[j][i]);
					map[j][i] = EMPTY;
				}
			}
			for (j = 0; !dq.isEmpty(); j++) {
				map[j][i] = dq.pollLast();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int i;
		
		map = new char[HIGHT][WIDTH];
		for (i = HIGHT - 1; i >= 0; i--) {
			map[i] = br.readLine().toCharArray();
		}
		q = new ArrayDeque<>();
		dq = new ArrayDeque<>();
		for (i = 0; puyo(); i++) {
			order();
		}
		System.out.print(i);
	}
}
