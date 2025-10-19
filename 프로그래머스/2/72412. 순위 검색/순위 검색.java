class Solution {
	private static final int C0 = 'c';
	private static final int J0 = 'j';
	private static final int P0 = 'p';
	private static final int B1 = 1 << 7 | 'b';
	private static final int F1 = 1 << 7 | 'f';
	private static final int J2 = 2 << 7 | 'j';
	private static final int S2 = 2 << 7 | 's';
	private static final int C3 = 3 << 7 | 'c';
	private static final int P3 = 3 << 7 | 'p';
	private static final int MAX = 100_000;
	private static final int SIZE = 3 * 2 * 2 * 2;
	private static final int WILD = 3;
	private static final int RADIX = 10;

	private static int score;
	private static int[] idxs;
	private static int[][] arr;

	private static int getIdx(int i, char key) {
		return switch (i << 7 | key) {
			case C0, B1, J2, C3 -> 0;
			case J0, F1, S2, P3 -> 1;
			case P0 -> 2;
			default -> 3;
		};
	}

	private static int next(int i, char key) {
		return switch (i << 7 | key) {
			case C0 -> 4;
			case J0 -> 5;
			case P3 -> 6;
			case P0, J2, S2 -> 7;
			case B1, C3 -> 8;
			case F1 -> 9;
			default -> 2;
		};
	}

	private static int dfs(int idx, int depth) {
		if (depth == 4) {
			return arr[score][idx];
		}
		idx <<= 1;
		if (idxs[depth] == WILD) {
			return dfs(idx, depth + 1) + dfs(idx + 1, depth + 1);
		} else {
			return dfs(idx + idxs[depth], depth + 1);
		}
	}

	public int[] solution(String[] info, String[] query) {
		char ch;
		int i;
		int j;
		int len;
		int cur;
		int idx;
		int[] ans;
		String str;

		arr = new int[MAX + 1][SIZE];
		len = info.length;
		for (i = 0; i < len; i++) {
			str = info[i];
			idx = 0;
			cur = 0;
			for (j = 0; j < 4; j++) {
				ch = str.charAt(cur);
				idx = (idx << 1) + getIdx(j, ch);
				cur += next(j, ch);
			}
			arr[Integer.parseInt(str, cur, str.length(), RADIX)][idx]++;
		}
		for (i = MAX - 1; i > 0; i--) {
			for (j = 0; j < SIZE; j++) {
				arr[i][j] += arr[i + 1][j];
			}
		}
		len = query.length;
		ans = new int[len];
		idxs = new int[4];
		for (i = 0; i < len; i++) {
			str = query[i];
			cur = 0;
			for (j = 0; j < 4; j++) {
				ch = str.charAt(cur);
				idxs[j] = getIdx(j, ch);
				cur += next(j, ch) + 4;
			}
			score = Integer.parseInt(str, cur - 4, str.length(), RADIX);
			if (idxs[0] == WILD) {
				ans[i] = dfs(0, 1) + dfs(1, 1) + dfs(2, 1);
			} else {
				ans[i] = dfs(idxs[0], 1);
			}
		}
		return ans;
	}
}
