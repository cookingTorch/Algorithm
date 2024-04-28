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
		int cost;
		int[] dp;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new int[k + 1];
		dp[0] = 1;
		for (i = 0; i < n; i++) {
			cost = Integer.parseInt(br.readLine());
			for (j = cost; j <= k; j++) {
				dp[j] += dp[j - cost];
			}
		}
		System.out.print(dp[k]);
	}
}
