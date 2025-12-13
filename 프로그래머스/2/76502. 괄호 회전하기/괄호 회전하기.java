import java.util.ArrayDeque;

class Solution {
    private static ArrayDeque<int[]> dq;

    private static boolean match(int ch) {
        if (dq.isEmpty()) {
            return false;
        }
		return (ch == ')' && dq.peekLast()[1] == '(')
				|| (ch == ']' && dq.peekLast()[1] == '[')
				|| (ch == '}' && dq.peekLast()[1] == '{');
    }

    public int solution(String s) {
        int i;
        int ch;
        int len;
        int cnt;

        len = s.length();
        dq = new ArrayDeque<>(len);
        for (i = 0; i < len; i++) {
            ch = s.charAt(i);
            if (match(ch)) {
                dq.pollLast();
            } else {
                dq.addLast(new int[] {i, ch});
            }
        }
        cnt = 0;
        for (i = 0; i < len; i++) {
            ch = s.charAt(i);
            if (dq.isEmpty()) {
                dq.addLast(new int[]{i, ch});
            } else {
                if (dq.peekFirst()[0] == i) {
                    dq.pollFirst();
                }
                if (match(ch)) {
                    dq.pollLast();
                } else {
                    dq.addLast(new int[] {i, ch});
                }
                if (dq.isEmpty()) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
