import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX = 20;
	private static final long EMPTY = -1L;

	private static int[] arr;
	private static long[][] dp;

	private static long getDp(int idx, int num) {
		if (num < 0 || num > MAX) {
			return 0;
		}
		if (dp[idx][num] != EMPTY) {
			return dp[idx][num];
		}
		if (idx == 1) {
			if (num == arr[1]) {
				return dp[idx][num] = 1L;
			}
			return dp[idx][num] = 0L;
		}
		return dp[idx][num] = getDp(idx - 1, num - arr[idx]) + getDp(idx - 1, num + arr[idx]);
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int j;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n - 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dp = new long[n][MAX + 1];
		for (i = 0; i < n; i++) {
			for (j = 0; j <= MAX; j++) {
				dp[i][j] = EMPTY;
			}
		}
		System.out.print(getDp(n - 1, Integer.parseInt(st.nextToken())));
	}
}
