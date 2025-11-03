import java.util.Arrays;

class Solution {
	public int solution(int n, int[][] data) {
		int r;
		int c;
		int i;
		int j;
		int x1;
		int y1;
		int x2;
		int y2;
		int cnt;
		int[][] dp;

		n--;
		Arrays.sort(data, 0, n + 1, (o1, o2) -> o1[0] - o2[0]);
		for (i = 0, r = 1; i < n; i++) {
			if (data[i][0] == data[i + 1][0]) {
				data[i][0] = r;
			} else {
				data[i][0] = r++;
			}
		}
		data[i][0] = r;
		Arrays.sort(data, 0, n + 1, (o1, o2) -> o1[1] - o2[1]);
		for (i = 0, c = 1; i < n; i++) {
			if (data[i][1] == data[i + 1][1]) {
				data[i][1] = c;
			} else {
				data[i][1] = c++;
			}
		}
		data[i][1] = c;
		dp = new int[r + 1][c + 1];
		for (i = 0; i <= n; i++) {
			dp[data[i][0]][data[i][1]]++;
		}
		for (i = 1; i <= r; i++) {
			for (j = 1; j <= c; j++) {
				dp[i][j] += dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
			}
		}
		cnt = 0;
		for (i = 1; i <= n; i++) {
			for (j = 0; j < i; j++) {
				if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) {
					continue;
				}
				x1 = Math.min(data[i][0], data[j][0]);
				y1 = Math.min(data[i][1], data[j][1]);
				x2 = Math.max(data[i][0], data[j][0]);
				y2 = Math.max(data[i][1], data[j][1]);
				if (dp[x2 - 1][y2 - 1] - dp[x1][y2 - 1] - dp[x2 - 1][y1] + dp[x1][y1] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
