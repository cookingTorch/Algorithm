class Solution {
	private static final int SIZE = 4;

	private static int len;
	private static int cnt;
	private static int maxPlus;
	private static int maxCost;
	private static int[] sum;
	private static int[] buyThr;
	private static int[] plusThr;
	private static int[][] costs;

	private static void dfs(int depth) {
		int i;
		int j;
		int plus;
		int cost;

		if (depth == cnt) {
			plus = 0;
			cost = 0;
			for (i = 0; i < len; i++) {
				if (sum[i] >= plusThr[i]) {
					plus++;
				} else {
					cost += sum[i];
				}
			}
			if (plus > maxPlus) {
				maxPlus = plus;
				maxCost = cost;
			} else if (plus == maxPlus && cost > maxCost) {
				maxCost = cost;
			}
			return;
		}
		for (i = 0; i < 4; i++) {
			cost = costs[depth][i];
			for (j = 0; j < len; j++) {
				if (i >= buyThr[j]) {
					sum[j] += cost;
				}
			}
			dfs(depth + 1);
			for (j = 0; j < len; j++) {
				if (i >= buyThr[j]) {
					sum[j] -= cost;
				}
			}
		}
	}

	public int[] solution(int[][] users, int[] emoticons) {
		int i;
		int j;

		len = users.length;
		buyThr = new int[len];
		plusThr = new int[len];
		for (i = 0; i < len; i++) {
			buyThr[i] = (users[i][0] + 9) / 10 - 1;
			plusThr[i] = users[i][1];
		}
		cnt = emoticons.length;
		costs = new int[cnt][SIZE];
		for (i = 0; i < cnt; i++) {
			emoticons[i] /= 10;
			for (j = 0; j < SIZE; j++) {
				costs[i][j] = emoticons[i] * (9 - j);
			}
		}
		maxPlus = 0;
		maxCost = 0;
		sum = new int[len];
		dfs(0);
		return new int[] {maxPlus, maxCost};
	}
}
