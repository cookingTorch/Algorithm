class Solution {
	private int n;
	private int xor;
	private int[] lps;

	private boolean kmp(int[] pat, int patLen, int[] txt, int txtLen) {
		int i;
		int j;

		for (i = 1, j = 0; i < patLen; ) {
			if (pat[i] == pat[j]) {
				lps[i++] = ++j;
			} else if (j != 0) {
				j = lps[j - 1];
			} else {
				lps[i++] = 0;
			}
		}
		for (i = 0, j = 0; i < txtLen; ) {
			if (txt[i] == pat[j]) {
				if (++j == patLen) {
					return true;
				}
				i++;
			} else if (j != 0) {
				j = lps[j - 1];
			} else {
				i++;
			}
		}
		return false;
	}

	private void buildPat(int[][] lock, int[] pat, int idx) {
		int i;
		int j;
		int row;

		switch (idx) {
			case 0:
				for (i = 0; i < n; i++) {
					row = 0;
					for (j = 0; j < n; j++) {
						row = row << 1 | lock[i][j];
					}
					pat[i] = row ^ xor;
				}
				break;
			case 1:
				for (i = 0; i < n; i++) {
					row = 0;
					for (j = 0; j < n; j++) {
						row = row << 1 | lock[j][n - 1 - i];
					}
					pat[i] = row ^ xor;
				}
				break;
			case 2:
				for (i = 0; i < n; i++) {
					row = 0;
					for (j = 0; j < n; j++) {
						row = row << 1 | lock[n - 1 - i][n - 1 - j];
					}
					pat[i] = row ^ xor;
				}
				break;
			case 3:
				for (i = 0; i < n; i++) {
					row = 0;
					for (j = 0; j < n; j++) {
						row = row << 1 | lock[n - 1 - j][i];
					}
					pat[i] = row ^ xor;
				}
				break;
		}
	}

	public boolean solution(int[][] key, int[][] lock) {
		int m;
		int i;
		int j;
		int idx;
		int len;
		int sum;
		int[] pat;
		int[] txt;
		long row;
		long[] keys;

		m = key.length;
		n = lock.length;
		len = m + (n << 1) - 1;
		keys = new long[len];
		for (i = 0; i < m; i++) {
			row = 0L;
			for (j = 0; j < m; j++) {
				row = row << 1 | key[i][j];
			}
			keys[i + n] = row << n;
		}
		pat = new int[n];
		txt = new int[len];
		lps = new int[len];
		sum = m + n;
		xor = (1 << n) - 1;
		for (idx = 0; idx < 4; idx++) {
			buildPat(lock, pat, idx);
			for (i = 0; i < sum; i++) {
				for (j = n; j < sum; j++) {
					txt[j] = (int) (keys[j] >>> i) & xor;
				}
				if (kmp(pat, n, txt, len)) {
					return true;
				}
			}
		}
		return false;
	}
}
