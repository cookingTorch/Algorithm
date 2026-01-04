class Solution {
	private static final char INF = Character.MAX_VALUE;

	public String solution(String number, int k) {
		int i;
		int len;
		int idx;
		int cnt;
		char ch;
		char[] ans;

		len = number.length();
		ans = new char[len + 1];
		ans[0] = INF;
		idx = 0;
		cnt = 0;
		for (i = 0; i < len; i++) {
			ch = number.charAt(i);
			while (cnt != k && ans[idx] < ch) {
				idx--;
				cnt++;
			}
			ans[++idx] = ch;
		}
		return new String(ans, 1, len - k);
	}
}
