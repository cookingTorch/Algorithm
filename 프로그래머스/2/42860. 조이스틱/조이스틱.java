class Solution {
	private static final int A = 'A';
	private static final int LEN = A + 26;

	public int solution(String name) {
		int i;
		int min;
		int len;
		int last;
		int move;
		char[] str;

		str = name.toCharArray();
		len = str.length;
		move = 0;
		last = 0;
		for (i = 0; i < len; i++) {
			if (str[i] != A) {
				move += Math.min(str[i] - A, LEN - str[i]);
				last = i;
				break;
			}
		}
		if (i == len) {
			return 0;
		}
		min = len - last;
		for (++i; i < len; i++) {
			if (str[i] != A) {
				move += Math.min(str[i] - A, LEN - str[i]);
				min = Math.min(min, Math.min((last << 1) + len - i, ((len - i) << 1) + last));
				last = i;
			}
		}
		min = Math.min(min, last);
		return move + min;
	}
}
