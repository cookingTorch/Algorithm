import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int T = 3;
	private static final int MAX_SIZE = 500;
	
	private static byte solution(BufferedReader br, StringTokenizer st, int[] coins) throws IOException {
		int n, sum, coin, num, size, weight, i, j;
		byte[] dp;
		
		n = Integer.parseInt(br.readLine());
		sum = 0;
		size = 0;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			coin = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			sum += coin * num;
			for (j = 1; j < num; num -= j, j <<= 1) {
				coins[++size] = coin * j;
			}
			coins[++size] = coin * num;
		}
		if ((sum & 1) == 1) {
			return 0;
		}
		sum >>= 1;
		dp = new byte[sum + 1];
		dp[0] = 1;
		for (i = 1; i <= size; i++) {
			weight = coins[i];
			for (j = sum; j >= weight; j--) {
				dp[j] |= dp[j - weight];
			}
		}
		return dp[sum];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int testCase;
		int[] coins;

		coins = new int[MAX_SIZE];
		for (testCase = 0; testCase < T; testCase++) {
			sb.append(solution(br, st, coins)).append('\n');
		}
		System.out.print(sb);
	}
}
