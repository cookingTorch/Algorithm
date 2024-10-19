import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int MAX_K = 5002;
	private static final char LINE_BREAK = '\n';

	private static int[] prefix;
	private static int[][] dp;
	private static int[][] mids;
	private static BufferedReader br;

	private static final int solution() throws IOException {
		int i;
		int j;
		int k;
		int num;
		int len;
		int mid;
		int sum;
		int val;
		StringTokenizer st;

		k = Integer.parseInt(br.readLine()) + 1;
		st = new StringTokenizer(br.readLine(), " ", false);
		for (i = 1; i < k; i++) {
			num = Integer.parseInt(st.nextToken());
			prefix[i] = prefix[i - 1] + num;
		}
		for (i = 1; i < k - 1; i++) {
			mids[i][i + 2] = i + 1;
			dp[i][i + 2] = prefix[i + 1] - prefix[i - 1];
		}
		for (len = 3; len < k; len++) {
			for (i = 1; i <= k - len; i++) {
				j = i + len;
				dp[i][j] = INF;
				sum = prefix[j - 1] - prefix[i - 1];
				for (mid = mids[i][j - 1]; mid <= mids[i + 1][j]; mid++) {
					val = dp[i][mid] + dp[mid][j] + sum;
					if (val < dp[i][j]) {
						dp[i][j] = val;
						mids[i][j] = mid;
					}
				}
			}
		}
		return dp[1][k];
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		prefix = new int[MAX_K];
		dp = new int[MAX_K][MAX_K];
		mids = new int[MAX_K][MAX_K];
		br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
