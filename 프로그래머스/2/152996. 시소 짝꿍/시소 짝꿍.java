class Solution {
	private static final int MIN = 100;
	private static final int MAX = 1_000;

	public long solution(int[] weights) {
		int i;
		int len;
		long ans;
		long[] cnt;

		cnt = new long[MAX + 1];
		len = weights.length;
		for (i = 0; i < len; i++) {
			cnt[weights[i]]++;
		}
		ans = 0L;
		for (i = MIN; i <= MAX; i++) {
			ans += (cnt[i] * (cnt[i] - 1L) >>> 1)
					+ cnt[i]
					* (((i & 1) == 0 ? cnt[i >>> 1] : 0L)
					+ ((i & 3) == 0 ? cnt[(i >>> 2) * 3] : 0L)
					+ (i % 3 == 0 ? cnt[i / 3 << 1] : 0L));
		}
		return ans;
	}
}
