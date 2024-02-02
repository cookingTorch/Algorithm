import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, i, j;
		int[][] score, dp;
		
		n = Integer.parseInt(br.readLine());
		score = new int[n + 1][2];
		for (i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				score[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[n + 1][3];
		for (i = 1; i <= n; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][2]));
			dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + score[i][1];
			dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]) + score[i][0];
		}
		return Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2]));
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
		System.out.print(sb);
	}
}
