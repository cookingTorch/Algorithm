import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int n;
	private static int k;
	private static long[][] dp;
	private static long[][] score;
	
	public static void main(String[] args) throws IOException {
		int i;
		int j;
		long max1;
		long max2;
		long maxTemp1;
		long maxTemp2;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new long[n][k];
		score = new long[n][k];
		max1 = 0;
		max2 = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < k; i++) {
			dp[0][i] = Long.parseLong(st.nextToken());
			if (dp[0][i] > max1) {
				max2 = max1;
				max1 = dp[0][i];
			} else if (dp[0][i] == max1) {
				max2 = max1;
			} else if (dp[0][i] > max2) {
				max2 = dp[0][i];
			}
		}
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < k; j++) {
				score[i][j] = Long.parseLong(st.nextToken());
			}
		}
		for (i = 1; i < n; i++) {
			maxTemp1 = 0;
			maxTemp2 = 0;
			for (j = 0; j < k; j++) {
				if (dp[i - 1][j] == max1) {
					dp[i][j] = max2 + score[i][j];
				} else {
					dp[i][j] = max1 + score[i][j];
				}
				if (dp[i][j] > maxTemp1) {
					maxTemp2 = maxTemp1;
					maxTemp1 = dp[i][j];
				} else if (dp[i][j] == maxTemp1) {
					maxTemp2 = maxTemp1;
				} else if (dp[i][j] > maxTemp2) {
					maxTemp2 = dp[i][j];
				}
			}
			max1 = maxTemp1;
			max2 = maxTemp2;
		}
		System.out.print(max1);
	}
}
