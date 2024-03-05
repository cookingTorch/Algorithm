import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int n, sum, coin, num, size, i, j;
		int[] dp;
		ArrayList<Integer> coins;
		
		n = Integer.parseInt(br.readLine());
		sum = 0;
		coins = new ArrayList<>();
		coins.add(0);
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			coin = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			sum += coin * num;
			for (j = 1; j < num; num -= j, j *= 2) {
				coins.add(coin * j);
			}
			coins.add(coin * num);
		}
		if (sum % 2 != 0) {
			return 0;
		}
		sum /= 2;
		size = coins.size() - 1;
		dp = new int[sum + 1];
		dp[0] = 1;
		for (i = 1; i <= size; i++) {
			for (j = sum; j >= 1; j--) {
				if (j >= coins.get(i)) {
					dp[j] |= dp[j - coins.get(i)];
				}
			}
		}
		return dp[sum];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int testCase;
		
		for (testCase = 0; testCase < 3; testCase++) {
			sb.append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
