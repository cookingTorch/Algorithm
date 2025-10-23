class Solution {
	private static int len;
	private static int[] diff;
	private static int[] retry;
	private static long lmt;
	private static long total;

	private static int lowerBound(int r) {
		int i;
		int l;
		int mid;
		long sum;

		l = 1;
		while (l < r) {
			mid = l + r >>> 1;
			sum = total;
			for (i = 0; i < len; i++) {
				if (diff[i] > mid && (sum += retry[i] * (diff[i] - mid)) > lmt) {
					break;
				}
			}
			if (i == len) {
				r = mid;
			} else {
				l = mid + 1;
			}
		}
		return r;
	}

	public int solution(int[] diffs, int[] times, long limit) {
		int i;
		int max;

		len = times.length;
		total = times[0];
		max = diffs[0];
		retry = new int[len];
		for (i = 1; i < len; i++) {
			total += times[i];
			max = Math.max(max, diffs[i]);
			retry[i] = times[i - 1] + times[i];
		}
		lmt = limit;
		diff = diffs;
		return lowerBound(max + 1);
	}
}
