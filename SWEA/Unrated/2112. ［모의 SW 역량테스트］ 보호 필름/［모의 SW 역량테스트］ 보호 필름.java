import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	private static final int B = 1;
	private static final int MAX_D = 13;
	private static final int MAX_W = 20;
	private static final int[] CHEM_A = new int[MAX_W];
	private static final int[] CHEM_B = new int[MAX_W];
	
	private static int d, w, k;
	private static int[][] films;
	
	private static boolean test() {
		int temp, i, j;
		
		loop:
		for (i = 0; i < w; i++) {
			temp = 1;
			for (j = 1; j < d; j++) {
				if (films[j][i] == films[j - 1][i]) {
					if (++temp == k) {
						continue loop;
					}
				} else {
					if (d - j < k) {
						return false;
					}
					temp = 1;
				}
			}
			return false;
		}
		return true;
	}
	
	private static boolean dfs(int num, int start, int cnt) {
		int i;
		int[] temp;
		
		if (cnt == num) {
			return test();
		}
		for (i = start; i < d; i++) {
			if (films[i] != CHEM_A && films[i] != CHEM_B) {
				temp = films[i];
				films[i] = CHEM_A;
				if (dfs(num, i + 1, cnt + 1)) {
					films[i] = temp;
					return true;
				}
				films[i] = temp;
			}
		}
		for (i = start; i < d; i++) {
			if (films[i] != CHEM_A && films[i] != CHEM_B) {
				temp = films[i];
				films[i] = CHEM_B;
				if (dfs(num, i + 1, cnt + 1)) {
					films[i] = temp;
					return true;
				}
				films[i] = temp;
			}
		}
		return false;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int left, right, mid, i, j;
		
		st = new StringTokenizer(br.readLine());
		d = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for (i = 0; i < d; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < w; j++) {
				films[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if (k == 1) {
			return 0;
		}
		left = 0;
		right = k;
		while (left < right) {
			mid = (left + right) / 2;
			if (!dfs(mid, 0, 0)) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		films = new int[MAX_D][MAX_W];
		Arrays.fill(CHEM_B, B);
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
