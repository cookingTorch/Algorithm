class Solution {
	public int[] solution(int n, long left, long right) {
		int[] ans;
		long i;
		long size;
		
		size = n;
		ans = new int[(int) (right - left) + 1];
		for (i = left; i <= right; i++) {
			ans[(int) (i - left)] = Math.max((int) (i / size), (int) (i % size)) + 1;
		}
		return ans;
	}
}
