import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Solution {
	private static final int MAX_W = 12;
	private static final int MAX_H = 15;
	private static final int EMPTY = 0;
	
	private static int n, w, h, min;
	private static int[][] matrix;
	private static Deque<Integer> stack;
	
	private static int[][] getClone(int[][] map) {
		int i, j;
		int[][] clone;
		
		clone = new int[h][w];
		for(i = 0; i < h; i++) {
			for (j = 0; j < w; j++) {
				clone[i][j] = map[i][j];
			}
		}
		return clone;
	}
	
	private static void explode(int[][] map, int x, int y) {
		int range, i;
		
		range = map[x][y];
		map[x][y] = EMPTY;
		for (i = 1; i < range && x - i >= 0; i++) {
			if (map[x - i][y] > 1) {
				explode(map, x - i, y);
			} else {
				map[x - i][y] = EMPTY;
			}
		}
		for (i = 1; i < range && y - i >= 0; i++) {
			if (map[x][y - i] > 1) {
				explode(map, x, y - i);
			} else {
				map[x][y - i] = EMPTY;
			}
		}
		for (i = 1; i < range && x + i < h; i++) {
			if (map[x + i][y] > 1) {
				explode(map, x + i, y);
			} else {
				map[x + i][y] = EMPTY;
			}
		}
		for (i = 1; i < range && y + i < w; i++) {
			if (map[x][y + i] > 1) {
				explode(map, x, y + i);
			} else {
				map[x][y + i] = EMPTY;
			}
		}
	}
	
	private static int count(int[][] map) {
		int cnt, size, i, j;
		
		cnt = 0;
		for (i = 0; i < w; i++) {
			size = 0;
			for (j = 0; j < h; j++) {
				if (map[j][i] != EMPTY) {
					stack.push(map[j][i]);
					map[j][i] = EMPTY;
					size++;
				}
			}
			for (j = 1; j <= size; j++) {
				map[h - j][i] = stack.pop();
				cnt++;
			}
		}
		return cnt;
	}
	
	private static boolean drop(int[][] map, int y, int cnt, int depth) {
		int i;
		
		for (i = h - 1;; i--) {
			if (i == -1 || map[i][y] == EMPTY) {
				if (map[++i][y] > 1) {
					explode(map, i, y);
					cnt = count(map);
				} else {
					map[i][y] = EMPTY;
					cnt--;
				}
				break;
			}
		}
		min = Math.min(min, cnt);
		if (min == 0) {
			return true;
		}
		if (depth == n) {
			return false;
		}
		for (i = 0; i < w; i++) {
			if (map[h - 1][i] == EMPTY) {
				continue;
			}
			if (drop(getClone(map), i, cnt, depth + 1)) {
				return true;
			}
		}
		return false;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int sum, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		sum = 0;
		for (i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < w; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if (matrix[i][j] != EMPTY) {
					sum++;
				}
			}
		}
		if (sum == 0) {
			return 0;
		}
		min = sum;
		for (i = 0; i < w; i++) {
			if (matrix[h - 1][i] == EMPTY) {
				continue;
			}
			if (drop(getClone(matrix), i, sum, 1)) {
				break;
			}
		}
		return min;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		matrix = new int[MAX_H][MAX_W];
		stack = new ArrayDeque<>();
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}