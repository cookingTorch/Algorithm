import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, diff, prevDiff, num, prevNum, i;
		int[] dp;
		
		n = Integer.parseInt(br.readLine());
		if (n == 1) {
			System.out.print("0");
			return;
		}
		dp = new int[n];
		st = new StringTokenizer(br.readLine());
		prevDiff = -Integer.parseInt(st.nextToken()) + (prevNum = Integer.parseInt(st.nextToken()));
		dp[1] = Math.abs(prevDiff);
		for (i = 2; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			diff = num - prevNum;
			if (prevDiff == 0) {
				dp[i] = dp[i - 2] + Math.abs(diff);
			} else if (diff == 0) {
				dp[i] = dp[i - 1];
			} else if (prevDiff * diff > 0) {
				dp[i] = Math.max(dp[i - 1] + Math.abs(diff), dp[i - 2] + Math.abs(diff));
			} else {
				dp[i] = Math.max(dp[i - 1], dp[i - 2] + Math.abs(diff));
			}
			prevDiff = diff;
			prevNum = num;
		}
		System.out.print(Math.max(dp[n - 1], dp[n - 2]));
	}
}
