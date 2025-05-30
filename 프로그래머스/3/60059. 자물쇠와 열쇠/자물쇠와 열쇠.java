class Solution {
	private boolean kmp(int[] pat, int patLen, int[] src, int srcLen, int[] lps) {
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
		for (i = 0, j = 0; i < srcLen; ) {
			if (src[i] == pat[j]) {
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

	private boolean find(int[][] lock, int xor, int thr, int[] curKey, long[] keyMap, int[] lps, int len, int[] lockMap, int n) {
		int i;
		int j;
		int lockRow;

		for (i = 0; i < n; i++) {
			lockRow = 0;
			for (j = 0; j < n; j++) {
				lockRow = lockRow << 1 | lock[i][j];
			}
			lockMap[i] = lockRow ^ xor;
		}
		for (i = 0; i < thr; i++) {
			for (j = n; j < thr; j++) {
				curKey[j] = (int) (keyMap[j] >>> i) & xor;
			}
			if (kmp(lockMap, n, curKey, len, lps)) {
				return true;
			}
		}
		return false;
	}

	private void rotate(int[][] lock, int[][] temp, int n) {
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
		int n;
		int i;
		int j;
		int len;
		int thr;
		int xor;
		int[] lps;
		int[] curKey;
		int[] lockMap;
		int[][] temp;
		long keyRow;
		long[] keyMap;

		m = key.length;
		n = lock.length;
		len = m + (n << 1) - 1;
		keyMap = new long[len];
		for (i = 0; i < m; i++) {
			keyRow = 0L;
			for (j = 0; j < m; j++) {
				keyRow = keyRow << 1 | key[i][j];
			}
			keyMap[i + n] = keyRow << n;
		}
		xor = (1 << n) - 1;
		lockMap = new int[n];
		curKey = new int[len];
		lps = new int[len];
		thr = m + n;
		temp = new int[n][n];
		if (find(lock, xor, thr, curKey, keyMap, lps, len, lockMap, n)) {
			return true;
		}
		for (i = 0; i < 3; i++) {
			rotate(lock, temp, n);
			if (find(lock, xor, thr, curKey, keyMap, lps, len, lockMap, n)) {
				return true;
			}
		}
		return false;
	}
}
