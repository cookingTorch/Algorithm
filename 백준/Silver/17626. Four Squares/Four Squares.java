import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n, i, j;
		int dp[];
		
		n = Integer.parseInt(br.readLine());
		dp = new int[n + 1];
		Arrays.fill(dp, 5);
		dp[0] = 0;
		for (i = 1; i * i <= n; i++) {
			dp[i * i] = 1;
		}
		for (i = 0; i <= n; i++) {
			if (dp[i] != 5) {
				continue;
			}
			for (j = 1; j * j < i; j++) {
				dp[i] = Math.min(dp[i], dp[i - (j * j)] + 1);
			}
		}
		System.out.println(dp[n]);
	}
}