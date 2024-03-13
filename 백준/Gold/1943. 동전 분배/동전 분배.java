import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	private static final int T = 3;
	private static final int MAX_SIZE = 101;
	private static final int MAX_SUM = 50001;
	
	private static int solution(BufferedReader br, StringTokenizer st, int[] coins, int[] nums, int[] dp) throws IOException {
		int n, sum, coin, num, prev, i, j;
		
		n = Integer.parseInt(br.readLine());
		sum = 0;
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			coin = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			sum += coin * num;
			coins[i] = coin;
			nums[i] = num;
		}
		if ((sum & 1) == 1) {
			return 0;
		}
		sum >>= 1;
		for (i = 1; i <= sum; i++) {
			dp[i] = INF;
		}
		prev = 0;
		for (i = 1; i <= n; i++) {
			coin = coins[i];
			num = nums[i];
			for (j = coin; j <= sum; j++) {
				if (dp[j - coin] < num && dp[j] == INF) {
					dp[j] = dp[j - coin] + 1;
					dp[j - coin] = 0;
					prev = j;
				}
			}
			if (dp[sum] != INF) {
				return 1;
			}
			dp[prev] = 0;
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int testCase;
		int[] coins, nums, dp;

		coins = new int[MAX_SIZE];
		nums = new int[MAX_SIZE];
		dp = new int[MAX_SUM];
		for (testCase = 0; testCase < T; testCase++) {
			sb.append(solution(br, st, coins, nums, dp)).append('\n');
		}
		System.out.print(sb);
	}
}
