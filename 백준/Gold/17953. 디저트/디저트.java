import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int j;
		int k;
		int val;
		int max;
		int[] dp;
		int[] prev;
		int[] temp;
		int[][] vals;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine(), " ", false);
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		vals = new int[n][m];
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			for (j = 0; j < n; j++) {
				vals[j][i] = Integer.parseInt(st.nextToken());
			}
		}
		dp = new int[m];
		prev = vals[0];
		for (i = 1; i < n; i++) {
			for (j = 0; j < m; j++) {
				dp[j] = 0;
				val = vals[i][j];
				for (k = 0; k < m; k++) {
					if (j == k) {
						dp[j] = Math.max(dp[j], prev[k] + (val >> 1));
					} else {
						dp[j] = Math.max(dp[j], prev[k] + val);
					}
				}
			}
			temp = dp;
			dp = prev;
			prev = temp;
		}
		max = 0;
		for (i = 0; i < m; i++) {
			max = Math.max(max, prev[i]);
		}
		System.out.print(max);
	}
}
