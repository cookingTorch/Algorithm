import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int[] color;
	private static int[][] dp;
	
	private static int solve(int left, int right) {
		int i;
		
		if (dp[left][right] != 200) {
			return dp[left][right];
		}
		dp[left][right] = 200;
		for (i = left; i < right; i++) {
			if (color[left] == color[i + 1]) {
				dp[left][right] = Math.min(dp[left][right], solve(left, i) + solve(i + 1, right));
			} else {
				dp[left][right] = Math.min(dp[left][right], solve(left, i) + solve(i + 1, right) + 1);
			}
		}
		return dp[left][right];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		st.nextToken();
		dp = new int[n][n];
		for (i = 0; i < n; i++) {
			Arrays.fill(dp[i], 200);
		}
		color = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			color[i] = Integer.parseInt(st.nextToken());
		}
		for (i = 0; i < n; i++) {
			dp[i][i] = 0;
		}
		for (i = 0; i < n - 1; i++) {
			j = i;
			while (++j < n && color[j] == color[j - 1]) {
				dp[i][j] = 0;
			}
		}
		sb.append(solve(0, n - 1));
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}