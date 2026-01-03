class Solution {
	private static final char EMPTY = '\0';

	private static boolean find(char[] prevRow, char[] row, boolean[] prevDel, boolean[] del) {
		int i;
		boolean match;

		match = false;
		for (i = 1; row[i] != EMPTY; i++) {
			if (row[i] == prevRow[i] && row[i] == row[i - 1] && row[i] == prevRow[i - 1]) {
				prevDel[i - 1] = true;
				prevDel[i] = true;
				del[i - 1] = true;
				del[i] = true;
				match = true;
			}
		}
		return match;
	}

	private static int fall(char[] row, boolean[] del) {
		int l;
		int r;
		int cnt;

		for (r = 0; row[r] != EMPTY && !del[r]; r++);
		l = r;
		cnt = 0;
		for (; row[r] != EMPTY; r++) {
			if (del[r]) {
				cnt++;
				del[r] = false;
			} else {
				row[l++] = row[r];
			}
		}
		while (l < r) {
			row[l++] = EMPTY;
		}
		return cnt;
	}

	public int solution(int m, int n, String[] board) {
		int i;
		int j;
		int cnt;
		char[][] map;
		boolean end;
		boolean[][] del;

		map = new char[n][m + 1];
		for (i = 0; i < n; i++) {
			for (j = 0; j < m; j++) {
				map[i][j] = board[m - 1 - j].charAt(i);
			}
		}
		cnt = 0;
		del = new boolean[n][m];
		for (;;) {
			end = true;
			for (i = 1; i < n; i++) {
				if (find(map[i - 1], map[i], del[i - 1], del[i])) {
					end = false;
				}
			}
			if (end) {
				break;
			}
			for (i = 0; i < n; i++) {
				cnt += fall(map[i], del[i]);
			}
		}
		return cnt;
	}
}
