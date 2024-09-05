import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final char[] ONE = {'1', '\n'};
	private static final char[] ZERO = {'0', '\n'};

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int s;
		int i;
		int j;
		int[] arr;
		boolean[][] dp;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dp = new boolean[n + 1][];
		for (i = 1; i <= n; i++) {
			dp[i] = new boolean[n + 2 - i];
			dp[i][0] = true;
			dp[i][1] = true;
		}
		for (i = 2; i <= n; i++) {
			for (j = 1; j <= n + 1 - i; j++) {
				if (arr[j] == arr[j + i - 1]) {
					dp[j][i] = dp[j + 1][i - 2];
				}
			}
		}
		sb = new StringBuilder();
		m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			if (dp[s][Integer.parseInt(st.nextToken()) - s + 1]) {
				sb.append(ONE);
			} else {
				sb.append(ZERO);
			}
		}
		System.out.print(sb.toString());
	}
}
