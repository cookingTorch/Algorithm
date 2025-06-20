class Solution {
	private static final int INF = Integer.MAX_VALUE;
	private static final int FAIL = -1;

	public int solution(int[][] beginning, int[][] target) {
		int r;
		int c;
		int i;
		int j;
		int thr;
		int row;
		int col;
		int min;
		int mask;
		int[] src;

		r = beginning.length;
		c = beginning[0].length;
		src = new int[r];
		for (i = 0; i < r; i++) {
			for (j = 0; j < c; j++) {
				if (beginning[i][j] != target[i][j]) {
					src[i] |= 1 << j;
				}
			}
		}
		min = INF;
		thr = 1 << r;
		mask = (1 << c) - 1;
		loop:
		for (i = 0; i < thr; i++) {
			col = (i & 1) == 0 ? src[0] : src[0] ^ mask;
			row = col ^ mask;
			for (j = 1; j < r; j++) {
				if ((src[j] ^ ((i & 1 << j) == 0 ? col : row)) != 0) {
					continue loop;
				}
			}
			min = Math.min(min, Integer.bitCount(i) + Integer.bitCount(col));
		}
		return min == INF ? FAIL : min;
	}
}
