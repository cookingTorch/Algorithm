import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE >> 1;
	
	public static void main(String[] args) throws IOException {
		int n;
		int k;
		int i;
		int j;
		int val;
		int[] dp;
		boolean[] visited;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		dp = new int[k + 1];
		for (i = 1; i <= k; i++) {
			dp[i] = INF;
		}
		visited = new boolean[k + 1];
		for (i = 0; i < n; i++) {
			val = Integer.parseInt(br.readLine());
			if (val > k || visited[val]) {
				continue;
			}
			visited[val] = true;
			for (j = val; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - val] + 1);
			}
		}
		System.out.print(dp[k] == INF ? "-1" : dp[k]);
	}
}
