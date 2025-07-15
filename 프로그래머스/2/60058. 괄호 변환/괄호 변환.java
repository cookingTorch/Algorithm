class Solution {
    private static final int L = '(';
    private static final int R = ')';

    private static char[] str;
    private static char[] tmp;

    private static void dnc(int start, int len) {
        int u;
        int v;
        int i;
        int cnt;
        int thr;
        boolean isCorrect;

        if (len == 0) {
            return;
        }
        if (str[start] == L) {
            cnt = 1;
            isCorrect = true;
        } else {
            cnt = -1;
            isCorrect = false;
        }
        for (i = start + 1; cnt != 0; i++) {
            if (str[i] == L) {
                cnt++;
            } else {
                if (--cnt < 0) {
                    isCorrect = false;
                }
            }
        }
        u = i - start;
        v = len - u;
        if (isCorrect) {
            dnc(start + u, v);
            return;
        }
        System.arraycopy(str, start + u, tmp, 0, v);
        System.arraycopy(str, start + 1, str, start + v + 2, u - 2);
        System.arraycopy(tmp, 0, str, start + 1, v);
        str[start] = L;
        dnc(start + 1, v);
        str[start + v + 1] = R;
        thr = start + len;
        for (i = start + v + 2; i < thr; i++) {
            str[i] = (char) (str[i] == L ? R : L);
        }
    }

    public String solution(String p) {
        str = p.toCharArray();
        tmp = new char[str.length];
        dnc(0, str.length);
        return new String(str);
    }
}
