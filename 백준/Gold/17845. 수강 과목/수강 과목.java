import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int k;
		int i;
		int j;
		int weight;
		long value;
		long[] dp;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new long[n + 1];
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			value = Long.parseLong(st.nextToken());
			weight = Integer.parseInt(st.nextToken());
			for (j = n; j >= weight; j--) {
				dp[j] = Math.max(dp[j], dp[j - weight] + value);
			}
		}
		System.out.print(dp[n]);
	}
}
