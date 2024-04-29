import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int t;
		int k;
		int s;
		int i;
		int j;
		int[] dp;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		dp = new int[t + 1];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			for (j = t; j >= k; j--) {
				dp[j] = Math.max(dp[j], dp[j - k] + s);
			}
		}
		System.out.print(dp[t]);
	}
}
