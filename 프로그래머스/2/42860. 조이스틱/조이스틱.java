class Solution {
	private static final int A = 'A';
	private static final int LEN = 'A' + 26;

	public int solution(String name) {
		int i;
		int min;
		int len;
		int last;
		char[] str;
		
		str = name.toCharArray();
		len = str.length;
		last = 0;
		for (i = 0; i < len; i++) {
			if (str[i] != A) {
				last = i;
				break;
			}
		}
		if (i == len) {
			return 0;
		}
		min = len - last;
		for (i = ++i; i < len; i++) {
			if (str[i] != A) {
				min = Math.min(min, Math.min((last << 1) + len - i, ((len - i) << 1) + last));
				last = i;
			}
		}
		min = Math.min(min, last);
		for (i = 0; i < len; i++) {
			if (str[i] != A) {
				min += Math.min(str[i] - A, LEN - str[i]);
			}
		}
		return min;
	}
}
