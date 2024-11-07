import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int sum;
		int max;
		int prev;
		int curr;
		int[] dp;
		int[] diffs;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		diffs = new int[m + 1];
		max = 1;
		prev = 0;
		for (i = 0; i < m; i++) {
			curr = Integer.parseInt(br.readLine());
			diffs[i] = curr - prev - 1;
			prev = curr;
			max = Math.max(max, diffs[i]);
		}
		diffs[m] = n - prev;
		max = Math.max(max, diffs[m]);
		dp = new int[max + 1];
		dp[0] = 1;
		dp[1] = 1;
		for (i = 2; i <= max; i++) {
			dp[i] = dp[i - 2] + dp[i - 1];
		}
		sum = 1;
		for (i = 0; i <= m; i++) {
			sum *= dp[diffs[i]];
		}
		System.out.print(sum);
	}
}
