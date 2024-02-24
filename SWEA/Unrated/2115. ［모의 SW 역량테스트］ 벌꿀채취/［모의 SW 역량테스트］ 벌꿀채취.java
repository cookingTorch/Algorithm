import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static final int MAX_N = 10;
	
	private static int n, m, c, max;
	private static int[] results;
	private static int[][] beehives;
	
	private static int knapsack(int start, int[] beehive) {
		int weight, value, i, j;
		int[] dp;
		
		dp = new int[c + 1];
		for (i = 1; i <= m; i++) {
			weight = beehive[i - 1 + start];
			value = weight * weight;
			for (j = c; j > 0; j--) {
				if (j >= weight) {
					dp[j] = Math.max(dp[j], dp[j - weight] + value);
				}
			}
		}
		return dp[c];
	}
	
	private static void getResults() {
		int i, j;
		
		for (i = 0; i < n; i++) {
			results[i] = 0;
			for (j = 0; j < n - m + 1; j++) {
				results[i] = Math.max(results[i], knapsack(j, beehives[i]));
			}
		}
	}
	
	private static int solution(BufferedReader br, StringTokenizer st) throws IOException {
		int i, j;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (j = 0; j < n; j++) {
				beehives[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		getResults();
		max = 0;
		for (i = 0; i < n; i++) {
			for (j = i + 1; j < n; j++) {
				max = Math.max(max, results[i] + results[j]);
			}
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int t, testCase;
		
		beehives = new int[MAX_N][MAX_N];
		results = new int[MAX_N];
		t = Integer.parseInt(br.readLine());
		for (testCase = 1; testCase <= t; testCase++) {
			sb.append('#').append(testCase).append(' ').append(solution(br, st)).append('\n');
		}
		System.out.print(sb);
	}
}
