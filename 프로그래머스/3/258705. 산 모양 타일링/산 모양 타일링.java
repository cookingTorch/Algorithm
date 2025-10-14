class Solution {
	private static final int MOD = 10_007;

	public int solution(int n, int[] tops) {
		int i;
		int cnt1;
		int cnt2;

		cnt1 = 1;
		cnt2 = 1;
		for (i = 0; i < n; i++) {
			cnt1 = (cnt1 + (cnt2 << tops[i])) % MOD;
			cnt2 = (cnt1 + cnt2) % MOD;
		}
		return cnt2;
	}
}