import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int t;
		int p;
		int i;
		int max;
		int curr;
		int[] dp;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		max = 0;
		curr = 0;
		dp = new int[n + 1];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			t = i + Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			curr = Math.max(curr, dp[i]);
			if (t <= n) {
				max = Math.max(max, dp[t] = Math.max(dp[t], curr + p));
			}
		}
		System.out.print(max);
	}
}
