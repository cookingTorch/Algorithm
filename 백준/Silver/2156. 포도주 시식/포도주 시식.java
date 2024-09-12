import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int[] dp;
		int[] arr;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		for (i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		if (n == 1) {
			System.out.print(arr[1]);
			return;
		} else if (n == 2) {
			System.out.print(arr[1] + arr[2]);
			return;
		}
		dp = new int[n + 2];
		dp[1] = 0;
		dp[2] = arr[1];
		dp[3] = arr[1] + arr[2];
		for (i = 4; i <= n + 1; i++) {
			dp[i] = Math.max(Math.max(
					dp[i - 3] + arr[i - 2] + arr[i - 1],
					dp[i - 2] + arr[i - 1]),
					dp[i - 1]);
		}
		System.out.print(dp[n + 1]);
	}
}
