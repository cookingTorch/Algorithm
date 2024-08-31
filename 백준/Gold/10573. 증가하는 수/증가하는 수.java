import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final int DIGIT = 10;
	private static final int DIFF = '0';
	private static final int MAX_LEN = 81;
	private static final char LINE_BREAK = '\n';
	private static final long FAIL = -1L;

	private static int len;
	private static char[] str;
	private static long[][][] dp;
	private static BufferedReader br;

	private static long getDp(int idx, int num, int flag) {
		int i;

		if (idx == len) {
			return dp[idx][num][flag] = 1;
		}
		if (dp[idx][num][flag] != FAIL) {
			return dp[idx][num][flag];
		}
		dp[idx][num][flag] = 0;
		if (flag == 1) {
			for (i = num; i < str[idx] - DIFF; i++) {
				dp[idx][num][flag] += getDp(idx + 1, i, 0);
			}
			dp[idx][num][flag] += getDp(idx + 1, i, 1);
		} else {
			for (i = num; i < DIGIT; i++) {
				dp[idx][num][flag] += getDp(idx + 1, i, 0);
			}
		}
		return dp[idx][num][flag];
	}

	private static long solution() throws IOException {
		int i;
		int j;
		int k;
		long ans;

		len = (str = br.readLine().toCharArray()).length;
		for (i = 1; i < len; i++) {
			if (str[i] < str[i - 1]) {
				return FAIL;
			}
		}
		for (i = 0; i < len; i++) {
			for (j = 0; j < DIGIT; j++) {
				for (k = 0; k < 2; k++) {
					dp[i][j][k] = FAIL;
				}
			}
		}
		ans = 0;
		for (i = 0; i < str[0] - DIFF; i++) {
			ans += getDp(1, i, 0);
		}
		ans += getDp(1, str[0] - DIFF, 1);
		return ans - 1;
	}

	public static void main(String[] args) throws IOException {
		int t;
		StringBuilder sb;

		dp = new long[MAX_LEN][DIGIT][2];
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			sb.append(solution()).append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
