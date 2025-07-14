class Solution {
	private static final int L = '(';
	private static final int R = ')';
	private static final char[] NIL = new char[0];

	private static char[] str;

	private static char[] dnc(int l, int r) {
		int i;
		int cnt;
		int mid;
		char[] res;
		boolean isCorrect;

		if (l > r) {
			return NIL;
		}
		res = new char[r - l + 1];
		if (str[l] == L) {
			isCorrect = true;
			cnt = 1;
		} else {
			isCorrect = false;
			cnt = -1;
		}
		mid = 0;
		for (i = l + 1; i <= r; i++) {
			if (str[i] == L) {
				cnt++;
			} else {
				cnt--;
				if (mid == 0 && cnt < 0) {
					isCorrect = false;
				}
			}
			if (mid == 0 && cnt == 0) {
				mid = i + 1;
			}
		}
		if (isCorrect) {
			System.arraycopy(str, l, res, 0, mid - l);
			System.arraycopy(dnc(mid, r), 0, res, mid - l, r - mid + 1);
			return res;
		}
		res[0] = L;
		System.arraycopy(dnc(mid, r), 0, res, 1, r - mid + 1);
		res[r - mid + 2] = R;
		for (i = l + 1; i < mid - 1; i++) {
			if (str[i] == L) {
				res[r - mid + 2 + i - l] = R;
			} else {
				res[r - mid + 2 + i - l] = L;
			}
		}
		return res;
	}

	public String solution(String p) {
		int len;

		str = p.toCharArray();
		len = str.length;
		return new String(dnc(0, len - 1));
	}
}
