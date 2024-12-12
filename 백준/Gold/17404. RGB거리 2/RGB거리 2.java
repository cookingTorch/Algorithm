import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE >> 1;

	private static int n;
	private static int[][] dp;
	private static int[][] cost;

	private static final int solution(int end) {
		int i;

		for (i = 1; i <= n; i++) {
			dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
			dp[i][1] = Math.min(dp[i - 1][2], dp[i - 1][0]) + cost[i][1];
			dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
		}
		dp[n][end] = INF;
		return Math.min(Math.min(dp[n][0], dp[n][1]), dp[n][2]);
	}

	public static void main(String[] args) throws IOException {
		int i;
		int min;
		int[] start;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		start = new int[3];
		st = new StringTokenizer(br.readLine(), " ", false);
		start[0] = Integer.parseInt(st.nextToken());
		start[1] = Integer.parseInt(st.nextToken());
		start[2] = Integer.parseInt(st.nextToken());
		cost = new int[n + 1][3];
		for (i = 2; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ", false);
			cost[i][0] = Integer.parseInt(st.nextToken());
			cost[i][1] = Integer.parseInt(st.nextToken());
			cost[i][2] = Integer.parseInt(st.nextToken());
		}
		dp = new int[n + 1][3];
		cost[1][0] = start[0];
		cost[1][1] = INF;
		cost[1][2] = INF;
		min = solution(0);
		cost[1][0] = INF;
		cost[1][1] = start[1];
		min = Math.min(min, solution(1));
		cost[1][1] = INF;
		cost[1][2] = start[2];
		min = Math.min(min, solution(2));
		System.out.print(min);
	}
}
