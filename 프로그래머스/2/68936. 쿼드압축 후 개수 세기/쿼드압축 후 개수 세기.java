class Solution {
	private static final int FAIL = -1;

	public int[] solution(int[][] arr) {
		int i;
		int j;
		int len;
		int[] ans;

		ans = new int[2];
		len = arr.length;
		for (i = 0; i < len; i++) {
			for (j = 0; j < len; j++) {
				ans[arr[i][j]]++;
			}
		}
		for (; len > 1; len >>>= 1) {
			for (i = 0; i < len; i += 2) {
				for (j = 0; j < len; j += 2) {
					if (arr[i][j] != FAIL && arr[i][j] == arr[i][j + 1] && arr[i][j] == arr[i + 1][j] && arr[i][j] == arr[i + 1][j + 1]) {
						arr[i >>> 1][j >>> 1] = arr[i][j];
						ans[arr[i][j]] -= 3;
					} else {
						arr[i >>> 1][j >>> 1] = FAIL;
					}
				}
			}
		}
		return ans;
	}
}
