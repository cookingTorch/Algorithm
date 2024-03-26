import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, weight, value, weightSum, valueSum, i, j;
		int[] dp, weights, values;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		weights = new int[n + 1];
		weightSum = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
			weightSum += weights[i];
		}
		values = new int[n + 1];
		valueSum = 0;
		st = new StringTokenizer(br.readLine());
		for (i = 1; i <= n; i++) {
			values[i] = Integer.parseInt(st.nextToken());
			valueSum += values[i];
		}
		m = weightSum - m;
		dp = new int[m + 1];
		for (i = 1; i <= n; i++) {
			weight = weights[i];
			value = values[i];
			for (j = m; j >= weight; j--) {
				dp[j] = Math.max(dp[j], dp[j - weight] + value);
			}
		}
		System.out.print(valueSum - dp[m]);
	}
}
