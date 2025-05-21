class Solution {
	public long solution(int[] sequence) {
		int i;
		int len;
		long max;
		long[] dp1;
		long[] dp2;

		len = sequence.length;
		dp1 = new long[len];
		dp2 = new long[len];
		max = Math.max(dp1[0] = sequence[0], dp2[0] = -sequence[0]);
		for (i = 1; i < len; i++) {
			if (dp1[i - 1] <= 0L) {
				max = Math.max(max, dp1[i] = -sequence[i]);
			} else {
				max = Math.max(max, dp1[i] = dp1[i - 1] - sequence[i]);
			}
			if (dp2[i - 1] <= 0L) {
				max = Math.max(max, dp2[i] = sequence[i]);
			} else {
				max = Math.max(max, dp2[i] = dp2[i - 1] + sequence[i]);
			}
			if (++i == len) {
				break;
			}
			if (dp1[i - 1] <= 0L) {
				max = Math.max(max, dp1[i] = sequence[i]);
			} else {
				max = Math.max(max, dp1[i] = dp1[i - 1] + sequence[i]);
			}
			if (dp2[i - 1] <= 0L) {
				max = Math.max(max, dp2[i] = -sequence[i]);
			} else {
				max = Math.max(max, dp2[i] = dp2[i - 1] - sequence[i]);
			}
		}
		return max;
	}
}
