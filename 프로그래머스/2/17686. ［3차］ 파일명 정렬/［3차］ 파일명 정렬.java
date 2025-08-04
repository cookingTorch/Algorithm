import java.util.Arrays;

class Solution {
	private static final int Z = 'Z';
	private static final int ZERO = '0';
	private static final int NINE = '9';
	private static final int DIFF = 'a' - 'A';

	private static boolean isNum(int ch) {
		return ZERO <= ch && ch <= NINE;
	}

	private static int lower(int ch) {
		if (ch > Z) {
			return ch - DIFF;
		}
		return ch;
	}

	public String[] solution(String[] files) {
		Arrays.sort(files, 0, files.length, (o1, o2) -> {
			int i;
			int j;
			int ch1;
			int ch2;
			int len1;
			int len2;
			int num1;
			int num2;

			for (i = 0;; i++) {
				ch1 = o1.charAt(i);
				ch2 = o2.charAt(i);
				if (isNum(ch1) && isNum(ch2)) {
					break;
				}
				if (lower(ch1) != lower(ch2)) {
					return ch1 - ch2;
				}
			}
			len1 = o1.length();
			num1 = ch1 - ZERO;
			for (j = ++i; j < len1; j++) {
				ch1 = o1.charAt(j);
				if (!isNum(ch1)) {
					break;
				}
				num1 = num1 * 10 + ch1 - ZERO;
			}
			len2 = o2.length();
			num2 = ch2 - ZERO;
			for (; i < len2; i++) {
				ch2 = o2.charAt(i);
				if (!isNum(ch2)) {
					break;
				}
				num2 = num2 * 10 + ch2 - ZERO;
			}
			return num1 - num2;
		});
		return files;
	}
}
