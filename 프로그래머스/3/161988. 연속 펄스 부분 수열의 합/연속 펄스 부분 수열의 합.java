class Solution {
	public long solution(int[] sequence) {
		int i;
		int len;
		long max;
		long sum1;
		long sum2;
		
		len = sequence.length;
		max = Math.max(sum1 = sequence[0], sum2 = -sequence[0]);
		for (i = 1; i < len; i++) {
			if (sum1 <= 0L) {
				sum1 = -sequence[i];
			} else {
				sum1 -= sequence[i];
			}
			if (sum2 <= 0L) {
				sum2 = sequence[i];
			} else {
				sum2 += sequence[i];
			}
			max = Math.max(max, Math.max(sum1, sum2));
			if (++i == len) {
				break;
			}
			if (sum1 <= 0L) {
				sum1 = sequence[i];
			} else {
				sum1 += sequence[i];
			}
			if (sum2 <= 0L) {
				sum2 = -sequence[i];
			} else {
				sum2 -= sequence[i];
			}
			max = Math.max(max, Math.max(sum1, sum2));
		}
		return max;
	}
}
