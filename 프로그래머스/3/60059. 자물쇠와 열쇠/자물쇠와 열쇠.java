class Solution {
	private int n;
	private int len;
	private int xor;
	private int thr;
	private int[] lps;
	private int[] curKey;
	private int[] lockMap;
	private int[][] lock;
	private int[][] temp;
	private long[] keyMap;

	private boolean kmp() {
		int i;
		int j;

		for (i = 1, j = 0; i < n; ) {
			if (lockMap[i] == lockMap[j]) {
				lps[i++] = ++j;
			} else if (j != 0) {
				j = lps[j - 1];
			} else {
				lps[i++] = 0;
			}
		}
		for (i = 0, j = 0; i < len; ) {
			if (curKey[i] == lockMap[j]) {
				if (++j == n) {
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

	private boolean find() {
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
			if (kmp()) {
				return true;
			}
		}
		return false;
	}

	private void rotate() {
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
		long keyRow;

		this.lock = lock;
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
		if (find()) {
			return true;
		}
		for (i = 0; i < 3; i++) {
			rotate();
			if (find()) {
				return true;
			}
		}
		return false;
	}
}
