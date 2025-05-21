class Solution {
	public int solution(int[] stones, int k) {
		int i;
		int l;
		int r;
		int cnt;
		int max;
		int mid;
		int len;

		len = stones.length;
		l = 1;
		r = 1;
		for (i = 0; i < len; i++) {
			r = Math.max(r, stones[i]);
		}
		r++;
		while (l < r) {
			mid = l + r >>> 1;
			cnt = 0;
			max = 0;
			for (i = 0; i < len; i++) {
				if (stones[i] < mid) {
					max = Math.max(max, ++cnt);
				} else {
					cnt = 0;
				}
			}
			if (max < k) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		return r - 1;
	}
}
