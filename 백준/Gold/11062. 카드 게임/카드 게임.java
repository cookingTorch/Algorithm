import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int MAX_N = 1_000;
	private static final char LINE_BREAK = '\n';

	private static int[] cards;
	private static int[][] dp;

	private static BufferedReader br;

	private static final int getDp(int left, int right, int sum) {
		if (dp[left][right] != 0) {
			return dp[left][right];
		}
		return dp[left][right] = sum - Math.min(
				getDp(left + 1, right, sum - cards[left]),
				getDp(left, right - 1, sum - cards[right])
		);
	}

	private static final int solution() throws IOException {
		int n;
		int i;
		int sum;
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			dp[0][i] = 0;
		}
		for (i = 0; i < n; i++) {
			System.arraycopy(dp[0], 0, dp[i], 0, n);
		}
		sum = 0;
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 0; i < n; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
			dp[i][i] = cards[i];
			sum += cards[i];
		}
		return getDp(0, n - 1, sum);
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		cards = new int[MAX_N];
		dp = new int[MAX_N][MAX_N];
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
