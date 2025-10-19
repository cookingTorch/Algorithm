import java.util.ArrayList;
import java.util.Collections;

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
	private static final int SIZE = 4 * 3 * 3 * 3;
	private static final int RADIX = 10;

	private static int score;
	private static int[] idxs;
	private static ArrayList<Integer>[] arr;

	private static int getIdx(int i, char key) {
		return switch (i << 7 | key) {
			case C0, B1, J2, C3 -> 1;
			case J0, F1, S2, P3 -> 2;
			case P0 -> 3;
			default -> 0;
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

	private static void dfs(int idx, int depth) {
		if (depth == 4) {
			arr[idx].add(score);
			return;
		}
		idx *= 3;
		dfs(idx, depth + 1);
		dfs(idx + idxs[depth], depth + 1);
	}

	private static int count(ArrayList<Integer> list, int score) {
		int l;
		int r;
		int mid;

		l = 0;
		r = list.size();
		while (l < r) {
			mid = l + r >>> 1;
			if (list.get(mid) < score) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		return list.size() - r;
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

		arr = new ArrayList[SIZE];
		for (i = 0; i < SIZE; i++) {
			arr[i] = new ArrayList<>();
		}
		len = info.length;
        idxs = new int[4];
		for (i = 0; i < len; i++) {
			str = info[i];
			cur = 0;
			for (j = 0; j < 4; j++) {
				ch = str.charAt(cur);
				idxs[j] = getIdx(j, ch);
				cur += next(j, ch);
			}
			score = Integer.parseInt(str, cur, str.length(), RADIX);
			dfs(0, 0);
		}
		for (i = 0; i < SIZE; i++) {
			Collections.sort(arr[i]);
		}
		len = query.length;
		ans = new int[len];
		for (i = 0; i < len; i++) {
			str = query[i];
			idx = 0;
			cur = 0;
			for (j = 0; j < 4; j++) {
				ch = str.charAt(cur);
				idx = idx * 3 + getIdx(j, ch);
				cur += next(j, ch) + 4;
			}
			ans[i] = count(arr[idx], Integer.parseInt(str, cur - 4, str.length(), RADIX));
		}
		return ans;
	}
}
