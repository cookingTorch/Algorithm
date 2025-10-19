import java.util.StringTokenizer;

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
	private static final String DELIM = " ";

	private static int score;
	private static int[] idxs;
	private static int[][] cnt;

	private static int getIdx(int i, char key) {
		return switch (i << 7 | key) {
			case C0, B1, J2, C3 -> 0;
			case J0, F1, S2, P3 -> 1;
			case P0 -> 2;
			default -> WILD;
		};
	}

	private static int dfs(int idx, int depth) {
		if (depth == 4) {
			return cnt[score][idx];
		}
		idx <<= 1;
		if (idxs[depth] == WILD) {
			return dfs(idx, depth + 1) + dfs(idx | 1, depth + 1);
		} else {
			return dfs(idx | idxs[depth], depth + 1);
		}
	}

	public int[] solution(String[] info, String[] query) {
		int i;
		int j;
		int len;
		int idx;
		int[] ans;
		StringTokenizer st;

		cnt = new int[MAX + 1][SIZE];
		len = info.length;
		for (i = 0; i < len; i++) {
			st = new StringTokenizer(info[i], DELIM, false);
			idx = 0;
			for (j = 0; j < 4; j++) {
				idx = (idx << 1) | getIdx(j, st.nextToken().charAt(0));
			}
			cnt[Integer.parseInt(st.nextToken())][idx]++;
		}
		for (i = MAX - 1; i > 0; i--) {
			for (j = 0; j < SIZE; j++) {
				cnt[i][j] += cnt[i + 1][j];
			}
		}
		len = query.length;
		ans = new int[len];
		idxs = new int[4];
		for (i = 0; i < len; i++) {
			st = new StringTokenizer(query[i], DELIM, false);
			idxs[0] = getIdx(0, st.nextToken().charAt(0));
			for (j = 1; j < 4; j++) {
				st.nextToken();
				idxs[j] = getIdx(j, st.nextToken().charAt(0));
			}
			score = Integer.parseInt(st.nextToken());
			if (idxs[0] == WILD) {
				ans[i] = dfs(0, 1) + dfs(1, 1) + dfs(2, 1);
			} else {
				ans[i] = dfs(idxs[0], 1);
			}
		}
		return ans;
	}
}
