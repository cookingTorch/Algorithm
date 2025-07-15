class Solution {
    private static final char L = '(';
    private static final char R = ')';

    private static boolean[] str;
    private static boolean[] tmp;

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
        if (str[start]) {
            cnt = 1;
            isCorrect = true;
        } else {
            cnt = -1;
            isCorrect = false;
        }
        for (i = start + 1; cnt != 0; i++) {
            if (str[i]) {
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
        System.arraycopy(str, start + 1, tmp, start + v + 2, u - 2);
        System.arraycopy(str, start + u, str, start + 1, v);
        str[start] = true;
        dnc(start + 1, v);
        str[start + v + 1] = false;
        thr = start + len;
        for (i = start + v + 2; i < thr; i++) {
            str[i] = !tmp[i];
        }
    }

    public String solution(String p) {
        int i;
        int len;
        char[] ans;

        len = p.length();
        str = new boolean[len];
        for (i = 0; i < len; i++) {
            str[i] = p.charAt(i) == L;
        }
        tmp = new boolean[len];
        dnc(0, len);
        ans = new char[len];
        for (i = 0; i < len; i++) {
            ans[i] = str[i] ? L : R;
        }
        return new String(ans);
    }
}
