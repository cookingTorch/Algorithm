import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;

	private static int arr[][];
	private static int dp[][];

	private static int getDp(int start, int end) {
		int mid;
		int min;

		if (dp[start][end] != INF) {
			return dp[start][end];
		}
		min = INF;
		for (mid = start; mid < end; mid++) {
			min = Math.min(min, getDp(start, mid) + getDp(mid + 1, end) + arr[start][0] * arr[mid][1] * arr[end][1]);
		}
		return dp[start][end] = min;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int j;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n][2];
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		dp = new int[n][n];
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				dp[i][j] = INF;
			}
			dp[i][i] = 0;
		}
		System.out.print(getDp(0, n - 1));
	}
}
