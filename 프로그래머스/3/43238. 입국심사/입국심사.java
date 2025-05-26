class Solution {
	private static final long INF = 1_000_000_000_000_000_001L;
	
	public long solution(int n, int[] times) {
		int i;
		int len;
		long cnt;
		long mid;
		long left;
		long right;

		len = times.length;
		left = 0;
		right = INF;
		while (left < right) {
			mid = left + right >>> 1;
			cnt = n;
			for (i = 0; i < len; i++) {
				if ((cnt -= mid / times[i]) <= 0) {
					break;
				}
			}
			if (i == len) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
}
