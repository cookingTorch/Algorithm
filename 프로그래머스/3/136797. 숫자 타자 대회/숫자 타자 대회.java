class Solution {
	private static final int INF = Integer.MAX_VALUE >>> 1;
	private static final int DIFF = '0';
	private static final int SIZE = 10;

	private static int getWeight(int dx, int dy) {
		return (dx + dy << 1) - Math.min(dx, dy);
	}

	private static int[][] buildWeights() {
		int i;
		int j;
		int[][] pos;
		int[][] weights;

		pos = new int[][] {
						{3, 1},
				{0, 0}, {0, 1}, {0, 2},
				{1, 0}, {1, 1}, {1, 2},
				{2, 0}, {2, 1}, {2, 2},
		};
		weights = new int[SIZE][SIZE];
		for (i = 0; i < SIZE; i++) {
			for (j = 0; j < i; j++) {
				weights[i][j] = weights[j][i] = getWeight(Math.abs(pos[i][0] - pos[j][0]), Math.abs(pos[i][1] - pos[j][1]));
			}
			weights[i][i] = 1;
		}
		return weights;
	}

	public int solution(String numbers) {
		int i;
		int j;
		int len;
		int cur;
		int num;
		int ans;
		int weight;
		int[] dp;
		int[] tmp;
		int[] next;
		int[] init;
		int[][] weights;

		weights = buildWeights();
		init = new int[SIZE];
		for (i = 0; i < SIZE; i++) {
			init[i] = INF;
		}
		dp = new int[SIZE];
		next = new int[SIZE];
		System.arraycopy(init, 0, dp, 0, SIZE);
		dp[4] = 0;
		cur = 6;
		len = numbers.length();
		for (i = 0; i < len; i++) {
			num = numbers.charAt(i) - DIFF;
			if (num == cur) {
				for (j = 0; j < SIZE; j++) {
					dp[j]++;
				}
				continue;
			}
			System.arraycopy(init, 0, next, 0, SIZE);
			weight = weights[cur][num];
			for (j = 0; j < SIZE; j++) {
				next[cur] = Math.min(next[cur], dp[j] + weights[j][num]);
				next[j] = Math.min(next[j], dp[j] + weight);
			}
            next[num] = INF;
			tmp = dp;
			dp = next;
			next = tmp;
			cur = num;
		}
		ans = INF;
		for (i = 0; i < SIZE; i++) {
			ans = Math.min(ans, dp[i]);
		}
		return ans;
	}
}
