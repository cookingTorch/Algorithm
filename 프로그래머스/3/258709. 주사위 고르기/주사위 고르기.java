class Solution {
	private static final int MAX = 100;
	private static final int NUMS = 6;

	private static int max;
	private static int res;
	private static int full;
	private static int half;
	private static int range;
	private static int offset;
	private static int[] init;
	private static int[][] buf;
	private static int[][] dices;

	private static void dfs(int[] cur, int cnt, int bits, int depth) {
		int i;
		int j;
		int num;
		int[] next;
		int[] dice;

		if (depth == full) {
			num = 0;
			for (i = offset + 1; i < range; i++) {
				num += cur[i];
			}
			if (num > max) {
				res = bits;
				max = num;
			}
			num = 0;
			for (i = offset - 1; i >= 0; i--) {
				num += cur[i];
			}
			if (num > max) {
				res = ~bits;
				max = num;
			}
			return;
		}
		dice = dices[depth];
		if (cnt < half) {
			next = buf[depth];
			System.arraycopy(init, 0, next, 0, range);
			for (i = 0; i < NUMS; i++) {
				num = dice[i];
				for (j = num; j < range; j++) {
					next[j] += cur[j - num];
				}
			}
			dfs(next, cnt + 1, bits << 1 | 1, depth + 1);
		}
		if (depth - cnt < half) {
			next = buf[depth];
			System.arraycopy(init, 0, next, 0, range);
			for (i = 0; i < NUMS; i++) {
				num = dice[i];
				for (j = num; j < range; j++) {
					next[j - num] += cur[j];
				}
			}
			dfs(next, cnt, bits << 1, depth + 1);
		}
	}

	public int[] solution(int[][] dice) {
		int i;
		int j;
		int[] cur;
		int[] ans;

		dices = dice;
		max = -1;
		full = dice.length;
		half = full >> 1;
		offset = half * MAX - half;
		range = offset << 1 | 1;
		buf = new int[full][range];
		init = new int[range];
		cur = buf[0];
		for (i = 0; i < NUMS; i++) {
			cur[offset + dice[0][i]]++;
		}
		dfs(cur, 1, 1, 1);
		ans = new int[half];
		for (i = full, j = half - 1; i > 0; res >>= 1, i--) {
			if ((res & 1) != 0) {
				ans[j--] = i;
			}
		}
		return ans;
	}
}