import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static final int SIZE = 10;
	private static final int MAX = SIZE * SIZE;
	private static final int MAX_M = 101;
	private static final int MAX_A = 9;
	private static final int[] DIR = {0, -SIZE, 1, SIZE, -1};
	
	private static int m;
	private static int[] dirA, dirB, p;
	private static int[][] bc;
	
	private static int getMax() {
		int posA, posB, ans, i;
		int[] bcA, bcB;
		
		ans = 0;
		posA = 0;
		posB = MAX - 1;
		for (i = 0; i <= m; i++) {
			posA += dirA[i];
			posB += dirB[i];
			bcA = bc[posA];
			bcB = bc[posB];
			if (bcA[0] == bcB[0]) {
				ans += p[bcA[0]] + Math.max(p[bcA[1]], p[bcB[1]]);
			} else {
				ans += p[bcA[0]] + p[bcB[0]];
			}
		}
		return ans;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int a, loc, c, x, y, i, j, k;
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= m; i++) {
			dirA[i] = DIR[Integer.parseInt(st.nextToken())];
		}
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= m; i++) {
			dirB[i] = DIR[Integer.parseInt(st.nextToken())];
		}
		bc = new int[MAX][2];
		for (i = 1; i <= a; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken()) - 1;
			x = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken());
			p[i] = Integer.parseInt(st.nextToken());
			for (j = x - c; j <= x + c; j++) {
				for (k = y - c + Math.abs(j - x); k <= y + c - Math.abs(j - x); k++) {
					if (j < 0 || k < 0 || j >= SIZE || k >= SIZE) {
						continue;
					}
					loc = j * SIZE + k;
					if (p[i] > p[bc[loc][0]]) {
						bc[loc][1] = bc[loc][0];
						bc[loc][0] = i;
					} else if (p[i] > p[bc[loc][1]]){
						bc[loc][1] = i;
					}
				}
			}
		}
		return getMax();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		dirA = new int[MAX_M];
		dirB = new int[MAX_M];
		p = new int[MAX_A];
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
