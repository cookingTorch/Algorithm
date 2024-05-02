import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		long[] r;
		long[] dp;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		if (n == 1) {
			System.out.print("0");
			return;
		}
		r = new long[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			r[i] = Long.parseLong(st.nextToken());
		}
		if (n == 2) {
			System.out.print(Math.max(r[0], r[1]));
			return;
		}
		if (n == 3) {
			System.out.print(Math.max(r[0] + r[2], r[1]));
			return;
		}
		dp = new long[n];
		dp[0] = r[0];
		dp[1] = r[1];
		dp[2] = Math.max(r[1], dp[0]) + r[2];
		n--;
		for (i = 3; i < n; i++) {
			dp[i] = Math.max(dp[i - 3] + r[i - 1], dp[i - 2]) + r[i];
		}
		System.out.print(Math.max(dp[n - 2] + r[i], dp[n - 1]));
	}
}
