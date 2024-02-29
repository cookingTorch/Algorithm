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
	private static int[] marble;
	private static int[][] matrix, map;
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
	
	private static void explode(int x, int y) {
		int range, i;
		
		range = map[x][y];
		map[x][y] = EMPTY;
		if (range <= 1) {
			return;
		}
		for (i = 1; i < range && x - i >= 0; i++) {
			explode(x - i, y);
		}
		for (i = 1; i < range && y - i >= 0; i++) {
			explode(x, y - i);
		}
		for (i = 1; i < range && x + i < h; i++) {
			explode(x + i, y);
		}
		for (i = 1; i < range && y + i < w; i++) {
			explode(x, y + i);
		}
	}
	
	private static int count() {
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
	
	private static void drop(int depth) {
		int i, j;
		
		if (depth == n) {
			map = getClone(matrix);
			for (i = 0; i < n; i++) {
				for (j = 0; j < h; j++) {
					if (map[j][marble[i]] != EMPTY) {
						explode(j, marble[i]);
						min = Math.min(min, count());
						break;
					}
				}
			}
			return;
		}
		for (i = 0; i < w; i++) {
			marble[depth] = i;
			drop(depth + 1);
		}
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		min = 0;
		for (i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < w; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
				if (matrix[i][j] != EMPTY) {
					min++;
				}
			}
		}
		marble = new int[n];
		drop(0);
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