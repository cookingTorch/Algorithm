import java.util.ArrayList;

class Solution {
	private static final int LEN = 13;
	private static final int DIFF = '0';
	private static final int PLUS = '+';
	private static final int SPACE = ' ' - DIFF;
	private static final int ERASED = 'X' - DIFF;

	private static final class Exp {
		private static final char PLUS = '+';
		private static final char MINUS = '-';
		private static final char SPACE = ' ';
		private static final char EQUALS = '=';
		private static final char QUESTION_MARK = '?';

		private static int min;
		private static int radix;
		private static char[] res = new char[LEN];

		int a1;
		int b1;
		int a10;
		int b10;
		boolean plus;

		Exp(int a10, int a1, boolean plus, int b10, int b1) {
			this.a10 = a10;
			this.a1 = a1;
			this.plus = plus;
			this.b10 = b10;
			this.b1 = b1;
		}

		String calc() {
			int c1;
			int c10;
			int c100;
			int idx;

			idx = 0;
			if (a10 != 0) {
				res[idx++] = (char) (a10 + DIFF);
			}
			res[idx++] = (char) (a1 + DIFF);
			res[idx++] = SPACE;
			res[idx++] = plus ? PLUS : MINUS;
			res[idx++] = SPACE;
			if (b10 != 0) {
				res[idx++] = (char) (b10 + DIFF);
			}
			res[idx++] = (char) (b1 + DIFF);
			res[idx++] = SPACE;
			res[idx++] = EQUALS;
			res[idx++] = SPACE;
			c10 = 0;
			c100 = 0;
			if (plus) {
				c1 = a1 + b1;
				if (c1 >= min) {
					if (min != radix) {
						res[idx++] = QUESTION_MARK;
						return new String(res, 0, idx);
					}
					c1 -= min;
					c10++;
				}
				c10 += a10 + b10;
				if (c10 >= min) {
					if (min != radix) {
						res[idx++] = QUESTION_MARK;
						return new String(res, 0, idx);
					}
					c10 -= min;
					c100++;
				}
			} else {
				c1 = a1 - b1;
				if (c1 < 0) {
					if (min != radix) {
						res[idx++] = QUESTION_MARK;
						return new String(res, 0, idx);
					}
					c1 += min;
					a10--;
				}
				c10 = a10 - b10;
			}
			if (c100 != 0) {
				res[idx++] = (char) (c100 + DIFF);
				res[idx++] = (char) (c10 + DIFF);
			} else if (c10 != 0) {
				res[idx++] = (char) (c10 + DIFF);
			}
			res[idx++] = (char) (c1 + DIFF);
			return new String(res, 0, idx);
		}
	}

	public String[] solution(String[] expressions) {
		int i;
		int a1;
		int b1;
		int c1;
		int a10;
		int b10;
		int c10;
		int idx;
		int min;
		int size;
		int radix;
		boolean plus;
		String str;
		String[] ans;
		ArrayList<Exp> exps;

		min = 2;
		radix = 9;
		exps = new ArrayList<>();
		size = expressions.length;
		for (i = 0; i < size; i++) {
			str = expressions[i];
			idx = 0;
			a10 = str.charAt(idx++) - DIFF;
			a1 = str.charAt(idx++) - DIFF;
			if (a1 == SPACE) {
				a1 = a10;
				a10 = 0;
			} else {
				idx++;
			}
			plus = str.charAt(idx) == PLUS;
			idx += 2;
			b10 = str.charAt(idx++) - DIFF;
			b1 = str.charAt(idx) - DIFF;
			if (b1 == SPACE) {
				b1 = b10;
				b10 = 0;
			}
			if (min != radix) {
				min = Math.max(min, Math.max(a1, Math.max(a10, Math.max(b1, b10))) + 1);
			}
			c1 = str.charAt(str.length() - 1) - DIFF;
			if (c1 == ERASED) {
				exps.add(new Exp(a10, a1, plus, b10, b1));
			} else {
				c10 = str.charAt(str.length() - 2) - DIFF;
				if (c10 == SPACE) {
					c10 = 0;
				}
				if (min != radix) {
					min = Math.max(min, Math.max(c1, c10) + 1);
				}
				if (min != radix) {
					if (plus) {
						if (a1 + b1 != c1) {
							radix = min = a1 + b1 - c1;
						} else if (a10 + b10 != c10) {
							radix = min = a10 + b10 - c10;
						}
					} else {
						if (a1 - b1 != c1) {
							radix = min = c1 + b1 - a1;
						}
					}
				}
			}
		}
		Exp.min = min;
		Exp.radix = radix;
		size = exps.size();
		ans = new String[size];
		for (i = 0; i < size; i++) {
			ans[i] = exps.get(i).calc();
		}
		return ans;
	}
}
