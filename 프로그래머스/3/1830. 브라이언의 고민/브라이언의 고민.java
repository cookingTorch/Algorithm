import java.util.ArrayDeque;

public class Solution {
    private static final int Z = 'Z';
    private static final int DIFF = 'a';
    private static final int ALPHA = 26;
    private static final char SPACE = ' ';
    private static final String INVALID = "invalid";

    private static int len;
    private static char[] str;
    private static boolean[] visited;
    private static ArrayDeque<String> dq;

    private static boolean isLower(int ch) {
        return ch > Z;
    }

    private static boolean validate(int idx) {
        if (isLower(str[idx])) {
            if (rule1(idx) || rule3(idx)) {
                return true;
            }
        } else {
            if (rule0(idx) || rule2(idx)) {
                return true;
            }
        }
        return false;
    }

    private static boolean rule0(int idx) {
        int i;

        for (i = idx; i < len && !isLower(str[i]); i++);
        if (i == len) {
            dq.addLast(new String(str, idx, len - idx));
            return true;
        }
        dq.addLast(new String(str, idx, i - idx));
        if (rule1(i) || rule3(i)) {
            return true;
        }
        dq.pollLast();
        if (i > idx + 1) {
            dq.addLast(new String(str, idx, i - 1 - idx));
            if (rule2(i - 1)) {
                return true;
            }
            dq.pollLast();
        }
        return false;
    }

    private static boolean rule1(int idx) {
        int i;
        int ch;

        ch = str[idx];
        if (visited[ch - DIFF] || idx > len - 3) {
            return false;
        }
        for (i = idx + 1; i < len && str[i] != ch; i++) {
            if (isLower(str[i])) {
                return false;
            }
        }
        if (i == len || i == idx + 1) {
            return false;
        }
        dq.addLast(new String(str, idx + 1, i - idx - 1));
        if (i == len - 1) {
            return true;
        }
        visited[ch - DIFF] = true;
        if (validate(i + 1)) {
            return true;
        }
        visited[ch - DIFF] = false;
        dq.pollLast();
        return false;
    }

    private static boolean rule2(int idx) {
        int i;
        int ch;
        StringBuilder sb;

        if (idx > len - 3) {
            return false;
        }
        ch = str[idx + 1];
        if (!isLower(ch) || visited[ch - DIFF]) {
            return false;
        }
        sb = new StringBuilder();
        sb.append(str[idx]);
        for (i = idx + 1; i < len && str[i] == ch; i++) {
            if (++i == len || isLower(str[i])) {
                return false;
            }
            sb.append(str[i]);
        }
        dq.addLast(sb.toString());
        if (i == len) {
            return true;
        }
        visited[ch - DIFF] = true;
        if (validate(i)) {
            return true;
        }
        visited[ch - DIFF] = false;
        dq.pollLast();
        return false;
    }

    private static boolean rule3(int idx) {
        int i;
        int ch1;
        int ch2;
        StringBuilder sb;

        if (idx > len - 5) {
            return false;
        }
        ch1 = str[idx];
        ch2 = str[idx + 2];
        if (visited[ch1 - DIFF] || isLower(str[idx + 1]) || !isLower(ch2) || visited[ch2 - DIFF]) {
            return false;
        }
        sb = new StringBuilder();
        sb.append(str[idx + 1]);
        for (i = idx + 2; i < len && str[i] == ch2; i++) {
            if (++i == len || isLower(str[i])) {
                return false;
            }
            sb.append(str[i]);
        }
        if (i == len || str[i] != ch1) {
            return false;
        }
        dq.addLast(sb.toString());
        if (i + 1 == len) {
            return true;
        }
        visited[ch1 - DIFF] = true;
        visited[ch2 - DIFF] = true;
        if (validate(i + 1)) {
            return true;
        }
        visited[ch1 - DIFF] = false;
        visited[ch2 - DIFF] = false;
        dq.pollLast();
        return false;
    }

    public String solution(String sentence) {
        StringBuilder sb;

        visited = new boolean[ALPHA];
        str = sentence.toCharArray();
        len = str.length;
        dq = new ArrayDeque<>();
        if (!validate(0)) {
            return INVALID;
        }
        sb = new StringBuilder();
        sb.append(dq.pollFirst());
        while (!dq.isEmpty()) {
            sb.append(SPACE).append(dq.pollFirst());
        }
        return sb.toString();
    }
}
