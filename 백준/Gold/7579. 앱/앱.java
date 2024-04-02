import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringTokenizer st2;
		
		int n, m, value, weight, weightSum, i, j;
		int[] dp, weights, values;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		weights = new int[n + 1];
		values = new int[n + 1];
		weightSum = 0;
		st = new StringTokenizer(br.readLine());
		st2 = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			values[i] = Integer.parseInt(st.nextToken());
			weights[i] = Integer.parseInt(st2.nextToken());
			weightSum += weights[i];
		}
		dp = new int[weightSum + 1];
		for (i = 1; i <= n; i++) {
			weight = weights[i];
			value = values[i];
			for (j = weightSum; j >= weight; j--) {
				dp[j] = Math.max(dp[j], dp[j - weight] + value);
			}
		}
		for (i = 0; i <= weightSum; i++) {
			if (dp[i] >= m) {
				System.out.print(i);
				return;
			}
		}
		System.out.print("-1");
	}
}
