import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int SIZE = 500;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, max, i, j;
		int[] arr, dp;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[SIZE + 1];
		for (i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
		}
		dp = new int[SIZE + 1];
		for (i = 1; i <= SIZE; i++) {
			if (arr[i] == 0) {
				continue;
			}
			dp[i] = 1;
			for (j = 1; j <= SIZE; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		max = 0;
		for (i = 1; i <= SIZE; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.print(n - max);
	}
}
