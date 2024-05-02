import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		long r;
		long[] dp;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		if (n == 1) {
			System.out.print("0");
			return;
		}
		st = new StringTokenizer(br.readLine());
		dp = new long[n];
		dp[0] = Long.parseLong(st.nextToken());
		dp[1] = Long.parseLong(st.nextToken());
		if (n == 2) {
			System.out.print(Math.max(dp[0], dp[1]));
			return;
		}
		r = Long.parseLong(st.nextToken());
		if (n == 3) {
			System.out.print(Math.max(dp[0] + r, dp[1]));
			return;
		}
		dp[2] = Math.max(dp[0], dp[1]) + r;
		n--;
		for (i = 3; i < n; i++) {
			dp[i] = Math.max(dp[i - 3] + r, dp[i - 2]) + (r = Integer.parseInt(st.nextToken()));
		}
		System.out.print(Math.max(dp[n - 2] + Integer.parseInt(st.nextToken()), dp[n - 1]));
	}
}
