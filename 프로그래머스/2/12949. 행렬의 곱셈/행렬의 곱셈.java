class Solution {
	public int[][] solution(int[][] arr1, int[][] arr2) {
		int r;
		int c;
		int i;
		int j;
		int k;
		int len;
		int[][] res;
		
		r = arr1.length;
		c = arr2[0].length;
		res = new int[r][c];
		len = arr2.length;
		for (i = 0; i < r; i++) {
			for (j = 0; j < c; j++) {
				for (k = 0; k < len; k++) {
					res[i][j] += arr1[i][k] * arr2[k][j];
				}
			}
		}
		return res;
	}
}
