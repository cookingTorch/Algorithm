class Solution {
	private static final int COL = 4;

	int solution(int[][] land) {
		int i;
		int j;
		int k;
		int n;
		int max;

		n = land.length;
		for (i = 1; i < n; i++) {
			for (j = 0; j < COL; j++) {
				max = 0;
				for (k = 0; k < COL; k++) {
					if (j == k) {
						continue;
					}
					max = Math.max(max, land[i - 1][k]);
				}
				land[i][j] += max;
			}
		}
		max = 0;
		for (i = 0; i < COL; i++) {
			max = Math.max(max, land[n - 1][i]);
		}
		return max;
	}
}
