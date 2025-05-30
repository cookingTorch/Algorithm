class Solution {
	private int n;
	private int len;
	private int sum;
	private int xor;
	private int[] lps;

	private boolean kmp(int[] pat, int patLen, int[] txt, int txtLen) {
		int i;
		int j;

		for (i = 1, j = 0; i < patLen;) {
			if (pat[i] == pat[j]) {
				lps[i++] = ++j;
			} else if (j != 0) {
				j = lps[j - 1];
			} else {
				lps[i++] = 0;
			}
		}
		for (i = 0, j = 0; i < txtLen;) {
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

	private boolean find(long[] keys, int[][] lock, int[] k, int[] l) {
		int i;
		int j;
		int row;

		for (i = 0; i < n; i++) {
			row = 0;
			for (j = 0; j < n; j++) {
				row = row << 1 | lock[i][j];
			}
			l[i] = row ^ xor;
		}
		for (i = 0; i < sum; i++) {
			for (j = n; j < sum; j++) {
				k[j] = (int) (keys[j] >>> i) & xor;
			}
			if (kmp(l, n, k, len)) {
				return true;
			}
		}
		return false;
	}

	private void rotate(int[][] lock, int[][] temp) {
		int i;
		int j;

		for (i = 0; i < n; i++) {
			System.arraycopy(lock[i], 0, temp[i], 0, n);
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				lock[i][j] = temp[j][n - 1 - i];
			}
		}
	}

	public boolean solution(int[][] key, int[][] lock) {
		int m;
		int i;
		int j;
		int[] k;
		int[] l;
		int[][] temp;
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
		xor = (1 << n) - 1;
		l = new int[n];
		k = new int[len];
		lps = new int[len];
		sum = m + n;
		temp = new int[n][n];
		if (find(keys, lock, k, l)) {
			return true;
		}
		for (i = 0; i < 3; i++) {
			rotate(lock, temp);
			if (find(keys, lock, k, l)) {
				return true;
			}
		}
		return false;
	}
}
