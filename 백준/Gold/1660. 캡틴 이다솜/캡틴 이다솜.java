import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int n;
	private static int[] arr;

	private static void initArr(int depth, int val) {
		if (val > n) {
			arr = new int[depth + 1];
		} else {
			initArr(depth + 1, val * (depth + 3) / depth);
		}
		arr[depth] = val;
	}

	public static void main(String[] args) throws IOException {
		int i;
		int j;
		int start;
		int[] dp;
		BufferedReader br;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		initArr(1, 1);
		dp = new int[n + 1];
		start = 0;
		for (i = 1; i <= n; i++) {
			if (i == arr[start + 1]) {
				start++;
			}
			dp[i] = i;
			for (j = start; j > 0; j--) {
				dp[i] = Math.min(dp[i], dp[i - arr[j]] + 1);
			}
		}
		System.out.print(dp[n]);
	}
}
