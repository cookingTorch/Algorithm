import java.util.ArrayDeque;

class Solution {
	private static final int THR = 10;
	private static final int ZERO = '0';
	private static final int ALPH = 'A' - THR;

	public String solution(int n, int t, int m, int p) {
		int i;
		int j;
		int r;
		int idx;
		int turn;
		char[] ans;
		ArrayDeque<Character> stack;

		ans = new char[t];
		stack = new ArrayDeque<>();
		idx = 0;
		if (--p == 0) {
			ans[idx++] = ZERO;
		}
		turn = 1;
		for (i = 1;; i++) {
			for (j = i; j != 0; j /= n) {
				stack.addFirst((char) ((r = j % n) < THR ? ZERO + r : ALPH + r));
			}
			while (!stack.isEmpty()) {
				if (turn == p) {
					ans[idx++] = stack.pollFirst();
					if (idx == t) {
						return new String(ans);
					}
				} else {
					stack.pollFirst();
				}
				turn = (turn + 1) % m;
			}
		}
	}
}
