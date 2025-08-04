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
		Arrays.asList(files).sort((o1, o2) -> {
			int i;
			int j;
			int ch1;
			int ch2;
			int len;
			int num1;
			int num2;

			for (i = 0; !isNum(ch1 = o1.charAt(i)) & !isNum(ch2 = o2.charAt(i)); i++) {
				if ((ch1 = lower(ch1)) != (ch2 = lower(ch2))) {
					return ch1 - ch2;
				}
			}
			if (!isNum(ch1)) {
				return 1;
			} else if (!isNum(ch2)) {
				return -1;
			}
			len = o1.length();
			num1 = 0;
			for (j = i; j < len && isNum(ch1 = o1.charAt(j)); j++) {
				num1 = num1 * 10 + ch1 - ZERO;
			}
			len = o2.length();
			num2 = 0;
			for (j = i; j < len && isNum(ch2 = o2.charAt(j)); j++) {
				num2 = num2 * 10 + ch2 - ZERO;
			}
			return num1 - num2;
		});
		return files;
	}
}
