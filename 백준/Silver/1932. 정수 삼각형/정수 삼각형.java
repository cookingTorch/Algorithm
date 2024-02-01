import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, i, j;
		int[][] arr, dp;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1][n + 1];
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= i; j++) {
				arr[i - j + 1][j] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[n + 1][n + 1];
		for (i = 1; i <= n; i++) {
			for (j = 1; j <= n; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
			}
		}
		System.out.print(dp[n][n]);
	}
}
