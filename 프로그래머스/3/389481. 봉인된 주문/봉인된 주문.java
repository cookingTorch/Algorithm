class Solution {
	private static final int DIFF = 'a';
	private static final int RADIX = 26;

	private static long toNum(String str) {
		int i;
		int len;
		long res;

		res = 0L;
		len = str.length();
		for (i = 0; i < len; i++) {
			res = res * RADIX + str.charAt(i) - DIFF + 1;
		}
		return res;
	}

	private static String toStr(long num) {
		int i;
		int len;
		long tmp;
		char[] res;

		len = 0;
		for  (tmp = num; tmp > 0L; tmp = (tmp - 1L) / RADIX) {
			len++;
		}
		res = new char[len];
		for (i = len - 1; i >= 0; i--) {
			res[i] = (char) (--num % RADIX + DIFF);
			num /= RADIX;
		}
		return new String(res);
	}

	public String solution(long n, String[] bans) {
		int i;
		int len;
		int cnt;
		long num;
		long thr;
		boolean[] arr;

		len = bans.length;
		cnt = 0;
		thr = n + len;
		arr = new boolean[len + 1];
		for (i = 0; i < len; i++) {
			num = toNum(bans[i]);
			if (num <= n) {
				cnt++;
			} else if (num < thr) {
				arr[(int) (num - n)] = true;
			}
		}
		for (i = 1; i <= cnt; i++) {
			if (arr[i]) {
				cnt++;
			}
		}
		return toStr(n + cnt);
	}
}
