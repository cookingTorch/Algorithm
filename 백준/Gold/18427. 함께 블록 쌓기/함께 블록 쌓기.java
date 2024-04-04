import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int P = 10007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, h, i, j;
		int[] dp;
		int[][] students;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		students = new int[n][m];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; st.hasMoreTokens(); j++) {
				students[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[h + 1];
		dp[0] = 1;
		for (int[] student : students) {
			for (i = h; i >= 0; i--) {
				for (int block : student) {
					if (block > 0 && i >= block) {
						dp[i] = (dp[i] + dp[i - block]) % P;
					}
				}
			}
		}
		System.out.print(dp[h]);
	}
}
