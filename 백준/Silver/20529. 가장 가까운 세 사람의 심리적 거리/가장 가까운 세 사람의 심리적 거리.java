import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] adj;
	
	private static int dist(String mbti1, String mbti2) {
		int val, i;
		
		val = 0;
		for (i = 0; i < 4; i++) {
			if (mbti1.charAt(i) != mbti2.charAt(i)) {
				val++;
			}
		}
		return val;
	}
	
	private static int dfs(int n, String[] mbti, int start, int d, int[] three) {
		int val, i;
		
		if (d == 3) {
			return adj[three[0]][three[1]] + adj[three[1]][three[2]] + adj[three[2]][three[0]];
		}
		val = 12;
		for (i = start; i < n; i++) {
			three[d] = i;
			val = Math.min(val, dfs(n, mbti, i + 1, d + 1, three));
		}
		return val;
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, i, j;
		String[] mbti;
		
		n = Integer.parseInt(br.readLine());
		mbti = new String[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			mbti[i] = st.nextToken();
		}
		if (n > 32) {
			return 0;
		}
		adj = new int[n][n];
		for (i = 0; i < n - 1; i++) {
			for (j = i + 1; j < n; j++) {
				adj[i][j] = dist(mbti[i], mbti[j]);
				adj[j][i] = adj[i][j];
			}
		}
		return dfs(n, mbti, 0, 0, new int[3]);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		t = Integer.parseInt(br.readLine());
		for (testCase = 0; testCase < t; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		System.out.println(sb);
	}
}